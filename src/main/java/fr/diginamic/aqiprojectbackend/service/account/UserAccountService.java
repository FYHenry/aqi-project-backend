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

@Service
@Validated
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final UserStatusRepository userStatusRepository;
    private final AddressRepository addressRepository;
    private final BookmarkRepository bookmarkRepository;
    private final TopicRepository topicRepository;
    private final ThreadRepository threadRepository;
    private final MessageRepository messageRepository;
    private final ReactionRepository reactionRepository;

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
    public ResponseEntity<HttpStatusDtoOut>
    createUserAccount(UserAccountDtoIn body){
        HttpStatus httpStatus;
        try {
            userAccountRepository.save(buildUserAccountFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (Exception ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpStatusDtoOut(httpStatus.value(),
                        httpStatus.getReasonPhrase()));
    }
    public ResponseEntity<UserAccountDtoOut> readUserAccount(int id){
        final UserAccount userAccount =
                userAccountRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        UserAccountDtoOut userAccountDtoOut =
                buildUserAccountDtoOutFrom(userAccount);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userAccountDtoOut);
    }
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
                userStatusRepository.findAllById(body.userStatusIdList());
        userAccount.setUserStatusList(userStatusList);
        final Address address = addressRepository
                .findById(body.addressId())
                .orElseThrow(EntityNotFoundException::new);
        userAccount.setAddress(address);
        final List<Bookmark> bookmarks =
                bookmarkRepository.findAllById(body.bookmarkIdList());
        userAccount.setBookmarks(bookmarks);
        if(body.role().contentEquals("ADMIN")){
            userAccount.setRole(Role.ADMIN);
        } else if(body.role().contentEquals("USER")){
            userAccount.setRole(Role.ADMIN);
        } else {
            throw new BadRequestException();
        }
        final List<Topic> topics =
                topicRepository.findAllById(body.topicIdList());
        userAccount.setTopics(topics);
        final List<Thread> threads =
                threadRepository.findAllById(body.threadIdList());
        userAccount.setThreads(threads);
        final List<Message> messages =
                messageRepository.findAllById(body.messageIdList());
        userAccount.setMessages(messages);
        final List<Reaction> reactions =
                reactionRepository.findAllById(body.reactionIdList());
        userAccount.setReactions(reactions);
        userAccountRepository.save(userAccount);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpStatusDtoOut(HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase()));
    }
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
    public ResponseEntity<List<UserAccountDtoOut>> listUserAccounts(){
        final List<UserAccount> userAccounts =
                userAccountRepository.findAll();
        final List<UserAccountDtoOut> userAccountDtoOutList = userAccounts
                .stream()
                .map((this::buildUserAccountDtoOutFrom))
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userAccountDtoOutList);
    }
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
                userStatusRepository.findAllById(body.userStatusIdList());
        final Address address =
                addressRepository
                        .findById(body.addressId())
                        .orElseThrow(EntityNotFoundException::new);
        final List<Bookmark> bookmarks =
                bookmarkRepository.findAllById(body.bookmarkIdList());
        final List<Topic> topics =
                topicRepository.findAllById(body.topicIdList());
        final List<Thread> threads =
                threadRepository.findAllById(body.threadIdList());
        final List<Message> messages =
                messageRepository.findAllById(body.messageIdList());
        final List<Reaction> reactions =
                reactionRepository.findAllById(body.reactionIdList());
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
    private UserAccountDtoOut
    buildUserAccountDtoOutFrom(UserAccount userAccount){
        final List<Integer> userStatusIdList = userAccount
                .getUserStatusList()
                .stream()
                .map(UserStatus::getId)
                .toList();
        final List<Integer> bookmarkIdList = userAccount
                .getBookmarks()
                .stream()
                .map(Bookmark::getId)
                .toList();
        final List<Integer> topicIdList = userAccount
                .getTopics()
                .stream()
                .map(Topic::getId)
                .toList();
        final List<Integer> threadIdList = userAccount
                .getThreads()
                .stream()
                .map(Thread::getId)
                .toList();
        final List<Integer> messageIdList = userAccount
                .getMessages()
                .stream()
                .map(Message::getId)
                .toList();
        final List<Integer> reactionIdList = userAccount
                .getReactions()
                .stream()
                .map(Reaction::getId)
                .toList();
        return new UserAccountDtoOut(userAccount.getId(),
                userAccount.getFirstName(),
                userAccount.getLastName(),
                userAccount.getEmail(),
                userAccount.getPassword(),
                userStatusIdList,
                userAccount.getAddress().getId(),
                bookmarkIdList,
                userAccount.getRole().name(),
                topicIdList,
                threadIdList,
                messageIdList,
                reactionIdList);
    }
}
