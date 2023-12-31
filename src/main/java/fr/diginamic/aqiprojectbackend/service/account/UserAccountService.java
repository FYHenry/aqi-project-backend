package fr.diginamic.aqiprojectbackend.service.account;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.account.in.UserAccountDtoIn;

import fr.diginamic.aqiprojectbackend.dto.account.in.UserUpdPwdDtoIn;
import fr.diginamic.aqiprojectbackend.dto.account.in.UserRegistrationFormDtoIn;
import fr.diginamic.aqiprojectbackend.dto.account.in.UserUpdateProfileFormDtoIn;

import fr.diginamic.aqiprojectbackend.dto.account.out.ConnectedUser;
import fr.diginamic.aqiprojectbackend.dto.account.out.UserAccountDtoOut;
import fr.diginamic.aqiprojectbackend.entity.account.Address;
import fr.diginamic.aqiprojectbackend.entity.account.Role;
import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import fr.diginamic.aqiprojectbackend.entity.account.UserStatus;
import fr.diginamic.aqiprojectbackend.entity.forum.Message;
import fr.diginamic.aqiprojectbackend.entity.forum.Reaction;
import fr.diginamic.aqiprojectbackend.entity.forum.Thread;
import fr.diginamic.aqiprojectbackend.entity.forum.Topic;
import fr.diginamic.aqiprojectbackend.entity.map.Bookmark;
import fr.diginamic.aqiprojectbackend.entity.map.City;
import fr.diginamic.aqiprojectbackend.exception.BadRequestException;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.account.AddressRepository;
import fr.diginamic.aqiprojectbackend.repository.account.UserAccountRepository;
import fr.diginamic.aqiprojectbackend.repository.account.UserStatusRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.MessageRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.ReactionRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.ThreadRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.TopicRepository;
import fr.diginamic.aqiprojectbackend.repository.map.BookmarkRepository;
import fr.diginamic.aqiprojectbackend.repository.map.CityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.util.Dtos.buildHttpStatusResponse;

/** User account service */
@Service
@Validated
public class UserAccountService {
    /**
     * PasswordEncoder to crypt password before saving it inside the database
     */
    private PasswordEncoder passwordEncoder;

    /**
     * User account repository
     */
    private final UserAccountRepository userAccountRepository;
    /**
     * User status repository
     */
    private final UserStatusRepository userStatusRepository;

    /**
     * Address repository
     */
    private final AddressRepository addressRepository;

    /**
     * City repository
     */
    private final CityRepository cityRepository;

    /**
     * Bookmark repository
     */
    private final BookmarkRepository bookmarkRepository;
    /**
     * Topic repository
     */
    private final TopicRepository topicRepository;
    /**
     * Thread repository
     */
    private final ThreadRepository threadRepository;
    /**
     * Message repository
     */
    private final MessageRepository messageRepository;
    /**
     * Reaction repository
     */
    private final ReactionRepository reactionRepository;


    /**
     * Constructor with parameters.
     *
     * @param passwordEncoder       PasswordEncoder used to encrypt password before saving it inside the database
     * @param userAccountRepository User account repository
     * @param userStatusRepository  User status repository
     * @param addressRepository     Address repository
     * @param bookmarkRepository    Bookmark repository
     * @param topicRepository       Topic repository
     * @param threadRepository      Thread repository
     * @param messageRepository     Message repository
     * @param reactionRepository    Reaction repository
     */
    public UserAccountService(PasswordEncoder passwordEncoder,
                              UserAccountRepository userAccountRepository,
                              UserStatusRepository userStatusRepository,
                              AddressRepository addressRepository,
                              CityRepository cityRepository,
                              BookmarkRepository bookmarkRepository,
                              TopicRepository topicRepository,
                              ThreadRepository threadRepository,
                              MessageRepository messageRepository,
                              ReactionRepository reactionRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userAccountRepository = userAccountRepository;
        this.userStatusRepository = userStatusRepository;
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.bookmarkRepository = bookmarkRepository;
        this.topicRepository = topicRepository;
        this.threadRepository = threadRepository;
        this.messageRepository = messageRepository;
        this.reactionRepository = reactionRepository;
    }


    /**
     * Create user account
     *
     * @param body HTTP request body (user account)
     * @param path HTTP request path
     * @return HTTP response (status)
     */

    public ResponseEntity<HttpStatusDtoOut>
    createUserAccount(UserAccountDtoIn body, String path) {
        HttpStatus httpStatus;
        try {
            userAccountRepository.save(buildUserAccountFromOLD(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return buildHttpStatusResponse(httpStatus, path);
    }

    public ResponseEntity<HttpStatusDtoOut>
    createUserAcc(UserRegistrationFormDtoIn body, String path) {
        HttpStatus httpStatus;
        try {
            userAccountRepository.save(buildUserAccountFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return buildHttpStatusResponse(httpStatus, path);
    }

    /**
     * Read user account
     *
     * @param id User account identifier
     * @return HTTP response (user account)
     */
    public ResponseEntity<UserAccountDtoOut> readUserAccount(int id) {
        final UserAccount userAccount =
                userAccountRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final UserAccountDtoOut userAccountDtoOut =
                buildUserAccountDtoOutFrom(userAccount);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userAccountDtoOut);
    }
    /**
     * Get Connected user info
     * @return http response (user account)
     */
    public ResponseEntity<ConnectedUser> connectedUser(int id) {
        final UserAccount userAccount =
                userAccountRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final ConnectedUser connectedUser =
                buildConnectedUserFrom(userAccount);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(connectedUser);
    }
    /**
     * Update user account
     *
     * @param id   User account identifier
     * @param body HTTP request body (user account)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateUserAccount(int id, UserAccountDtoIn body, String path) {
        final UserAccount userAccount = userAccountRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        userAccount.setFirstName(body.firstName());
        userAccount.setLastName(body.lastName());
        userAccount.setEmail(body.email());
        userAccount.setPassword(passwordEncoder.encode(body.password()));
        final List<UserStatus> userStatusList =
                userStatusRepository.findAllById(body.userStatusIds());
        userAccount.setUserStatusList(userStatusList);
        final Address address = addressRepository
                .findById(body.addressId())
                .orElseThrow(EntityNotFoundException::new);
        userAccount.setAddress(address);
        final List<Bookmark> bookmarks =
                bookmarkRepository.findAllById(body.bookmarkIds());
        userAccount.setBookmarks(bookmarks);
        if (body.role().contentEquals("ADMIN")) {
            userAccount.setRole(Role.ADMIN);
        } else if (body.role().contentEquals("USER")) {
            userAccount.setRole(Role.USER);
        } else {
            throw new BadRequestException();
        }
        final List<Topic> topics =
                topicRepository.findAllById(body.topicIds());
        userAccount.setTopics(topics);
        final List<Thread> threads =
                threadRepository.findAllById(body.threadIds());
        userAccount.setThreads(threads);
        final List<Message> messages =
                messageRepository.findAllById(body.messageIds());
        userAccount.setMessages(messages);
        final List<Reaction> reactions =
                reactionRepository.findAllById(body.reactionIds());
        userAccount.setReactions(reactions);
        userAccountRepository.save(userAccount);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }
    public ResponseEntity<HttpStatusDtoOut>
    updateUserProfile(int id, UserUpdateProfileFormDtoIn body, String path) {
        final UserAccount userAccount = userAccountRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        userAccount.setFirstName(body.firstName());
        userAccount.setLastName(body.lastName());
        userAccount.setEmail(body.email());

        final City city = cityRepository
                .findCityByInsee(body.cityInsee())
                .orElseThrow(EntityNotFoundException::new);
        System.out.println("CItyInsee:" + body.cityInsee() + " / CIty:" + city.getName());

        System.out.println("address L1&2: " + body.addressLine1() + " / " + body.addressLine2());
        Address address = addressRepository
                .findById(userAccount.getAddress().getId())
                .orElseThrow(EntityNotFoundException::new);
        System.out.println("user address id: " + userAccount.getAddress().getId());
        address.setAddressLine1(body.addressLine1());
        address.setAddressLine2(body.addressLine2());
        address.setCity(city);
        System.out.println("address: " + address.getAddressLine1() + " / " + address.getAddressLine2() + " / " + address.getCity());
        addressRepository.save(address);

        userAccount.setAddress(address);
        userAccountRepository.save(userAccount);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    public ResponseEntity<HttpStatusDtoOut>
    updateUserPwd(int id, UserUpdPwdDtoIn body, String path) {
        final UserAccount userAccount = userAccountRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        userAccount.setPassword(passwordEncoder.encode(body.newPassword()));
        userAccountRepository.save(userAccount);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * Delete user account
     *
     * @param id   User account identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    deleteUserAccount(int id, String path) {
        final UserAccount userAccount = userAccountRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        userAccountRepository.delete(userAccount);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * List user accounts
     *
     * @return HTTP response (user accounts)
     */
    public ResponseEntity<List<UserAccountDtoOut>> listUserAccounts() {
        final List<UserAccount> userAccounts =
                userAccountRepository.findAll();
        final List<UserAccountDtoOut> userAccountDtoOutList = userAccounts
                .stream()
                .map(this::buildUserAccountDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userAccountDtoOutList);
    }


    /**
     * Build user account from HTTP request body (user account)
     *
     * @param body HTTP request body (user account)
     * @return User account
     */
    private UserAccount buildUserAccountFrom(UserRegistrationFormDtoIn body) {
        String passwordEncoded = passwordEncoder.encode(body.password());
        System.out.println("passwordEncoded" + passwordEncoded);
        Role role = Role.USER;

        /* NEW MELINA 20230908*/
        final City city = cityRepository
                .findCityByInsee(body.cityInsee())
                .orElseThrow(EntityNotFoundException::new);

        System.out.println("CItyInsee:" + body.cityInsee() + " / CIty:" + city.getName());
        System.out.println("address L1&2: " + body.addressLine1() + " / " + body.addressLine2());

        Address address = new Address(body.addressLine1(), body.addressLine2(), city);
        System.out.println("address: " + address.getAddressLine1() + " / " + address.getAddressLine2() + " / " + address.getCity());
        addressRepository.save(address);
        /* EX SYNTAXE
        final UserAccount userAccount = userAccountRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        userAccountRepository.delete(userAccount);
        userAccountRepository.save(userAccount);
*/
        return new UserAccount(body.firstName(),
                body.lastName(),
                body.email(),
                passwordEncoded,
                //        userStatusList,
                role,
                address);
        /*        bookmarks,
                topics,
                threads,
                messages,
                reactions);*/
    }

    private UserAccount buildUserAccountFromOLD(UserAccountDtoIn body) {
        String passwordEncoded = passwordEncoder.encode(body.password());
        System.out.println("passwordEncoded" + passwordEncoded);
        Role role;
        if (body.role().contentEquals("ADMIN")) {
            role = Role.ADMIN;
        } else if (body.role().contentEquals("USER")) {
            role = Role.USER;
        } else {
            throw new BadRequestException();
        }
        final List<UserStatus> userStatusList =
                userStatusRepository.findAllById(body.userStatusIds());

        final Address address =
                addressRepository
                        .findById(body.addressId())
                        .orElseThrow(EntityNotFoundException::new);

        final List<Bookmark> bookmarks =
                bookmarkRepository.findAllById(body.bookmarkIds());
        final List<Topic> topics =
                topicRepository.findAllById(body.topicIds());
        final List<Thread> threads =
                threadRepository.findAllById(body.threadIds());
        final List<Message> messages =
                messageRepository.findAllById(body.messageIds());
        final List<Reaction> reactions =
                reactionRepository.findAllById(body.reactionIds());

        return new UserAccount(body.firstName(),
                body.lastName(),
                body.email(),
                passwordEncoded,
                userStatusList,
                role,
                address,
                bookmarks,
                topics,
                threads,
                messages,
                reactions);
    }

    /**
     * Build connected user from HTTP request body (user account)
     * @param userAccount User account
     * @return connected user
     */
    private ConnectedUser buildConnectedUserFrom(UserAccount userAccount) {
       return new ConnectedUser(userAccount.getId(),
               userAccount.getFirstName(),
               userAccount.getLastName(),
               userAccount.getEmail(),
               userAccount.getAddress().getCity().getName(),
               userAccount.getAddress().getCity().getLatitude(),
               userAccount.getAddress().getCity().getLongitude(),
               userAccount.getAddress().getAddressLine1(),
               userAccount.getAddress().getAddressLine2()
               );
    }

    /**
     * Build user account as HTTP response from user account
     *
     * @param userAccount User account
     * @return HTTP response (user account)
     */
    private UserAccountDtoOut
    buildUserAccountDtoOutFrom(UserAccount userAccount) {
        final List<Integer> userStatusIds = userAccount
                .getUserStatusList()
                .stream()
                .map(UserStatus::getId)
                .toList();
        final List<Integer> bookmarkIds = userAccount
                .getBookmarks()
                .stream()
                .map(Bookmark::getId)
                .toList();
        final List<Integer> topicIds = userAccount
                .getTopics()
                .stream()
                .map(Topic::getId)
                .toList();
        final List<Integer> threadIds = userAccount
                .getThreads()
                .stream()
                .map(Thread::getId)
                .toList();
        final List<Integer> messageIds = userAccount
                .getMessages()
                .stream()
                .map(Message::getId)
                .toList();
        final List<Integer> reactionIds = userAccount
                .getReactions()
                .stream()
                .map(Reaction::getId)
                .toList();
        return new UserAccountDtoOut(userAccount.getId(),
                userAccount.getFirstName(),
                userAccount.getLastName(),
                userAccount.getEmail(),
                userAccount.getPassword(),
                userStatusIds,
                userAccount.getAddress().getId(),
                bookmarkIds,
                userAccount.getRole().name(),
                topicIds,
                threadIds,
                messageIds,
                reactionIds);
    }

}
