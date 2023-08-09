package fr.diginamic.aqiprojectbackend.service.account;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.account.in.UserAccountDtoIn;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
/** User account service */
@Service
@Validated
public class UserAccountService {
    /** User account repository */
    private final UserAccountRepository userAccountRepository;
    /** User status repository */
    private final UserStatusRepository userStatusRepository;
    /** Address repository */
    private final AddressRepository addressRepository;
    /** Bookmark repository */
    private final BookmarkRepository bookmarkRepository;
    /** Topic repository */
    private final TopicRepository topicRepository;
    /** Thread repository */
    private final ThreadRepository threadRepository;
    /** Message repository */
    private final MessageRepository messageRepository;
    /** Reaction repository */
    private final ReactionRepository reactionRepository;

    /**
     * Constructor with parameters.
     * @param userAccountRepository User account repository
     * @param userStatusRepository User status repository
     * @param addressRepository Address repository
     * @param bookmarkRepository Bookmark repository
     * @param topicRepository Topic repository
     * @param threadRepository Thread repository
     * @param messageRepository Message repository
     * @param reactionRepository Reaction repository
     */
    public UserAccountService(UserAccountRepository userAccountRepository,
                              UserStatusRepository userStatusRepository,
                              AddressRepository addressRepository,
                              BookmarkRepository bookmarkRepository,
                              TopicRepository topicRepository,
                              ThreadRepository threadRepository,
                              MessageRepository messageRepository,
                              ReactionRepository reactionRepository){
        this.userAccountRepository = userAccountRepository;
        this.userStatusRepository = userStatusRepository;
        this.addressRepository = addressRepository;
        this.bookmarkRepository = bookmarkRepository;
        this.topicRepository = topicRepository;
        this.threadRepository = threadRepository;
        this.messageRepository = messageRepository;
        this.reactionRepository = reactionRepository;
    }

    /**
     * Create user account
     * @param body HTTP request body (user account)
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createUserAccount(UserAccountDtoIn body){
        HttpStatus httpStatus;
        try {
            userAccountRepository.save(buildUserAccountFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpStatusDtoOut(httpStatus.value(),
                        httpStatus.getReasonPhrase()));
    }
    /**
     * Read user account
     * @param id User account identifier
     * @return HTTP response (user account)
     */
    public ResponseEntity<UserAccountDtoOut> readUserAccount(int id){
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
     * Update user account
     * @param id User account identifier
     * @param body HTTP request body (user account)
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateUserAccount(int id, UserAccountDtoIn body){
        final UserAccount userAccount = userAccountRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        userAccount.setFirstName(body.firstName());
        userAccount.setLastName(body.lastName());
        userAccount.setEmail(body.email());
        userAccount.setPassword(body.password());
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
        if(body.role().contentEquals("ADMIN")){
            userAccount.setRole(Role.ADMIN);
        } else if(body.role().contentEquals("USER")){
            userAccount.setRole(Role.ADMIN);
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
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpStatusDtoOut(HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase()));
    }
    /**
     * Delete user account
     * @param id User account identifier
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut> deleteUserAccount(int id){
        final UserAccount userAccount = userAccountRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        userAccountRepository.delete(userAccount);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpStatusDtoOut(HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase()));
    }
    /**
     * List user accounts
     * @return HTTP response (user accounts)
     */
    public ResponseEntity<List<UserAccountDtoOut>> listUserAccounts(){
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
     * @param body HTTP request body (user account)
     * @return User account
     */
    private UserAccount buildUserAccountFrom(UserAccountDtoIn body) {
        Role role;
        if(body.role().contentEquals("ADMIN")){
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
                body.password(),
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
     * Build user account as HTTP response from user account
     * @param userAccount User account
     * @return HTTP response (user account)
     */
    private UserAccountDtoOut
    buildUserAccountDtoOutFrom(UserAccount userAccount){
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
