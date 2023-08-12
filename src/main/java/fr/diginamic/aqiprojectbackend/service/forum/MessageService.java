package fr.diginamic.aqiprojectbackend.service.forum;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.forum.in.MessageDtoIn;
import fr.diginamic.aqiprojectbackend.dto.forum.out.MessageDtoOut;
import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import fr.diginamic.aqiprojectbackend.entity.forum.Message;
import fr.diginamic.aqiprojectbackend.entity.forum.Reaction;
import fr.diginamic.aqiprojectbackend.entity.forum.Thread;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.account.UserAccountRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.MessageRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.ReactionRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.ThreadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.util.Dtos.buildHttpStatusResponse;

@Service
@Validated
public class MessageService {
    /** Message repository */
    private final MessageRepository messageRepository;
    /** Thread repository */
    private final ThreadRepository threadRepository;
    /** User account repository */
    private final UserAccountRepository userAccountRepository;
    /** Reaction repository */
    private final ReactionRepository reactionRepository;

    /**
     * Constructor with parameters.
     * @param messageRepository Message repository
     * @param threadRepository Thread repository
     * @param userAccountRepository User account repository
     * @param reactionRepository Reaction repository
     */
    public MessageService(MessageRepository messageRepository,
                          ThreadRepository threadRepository,
                          UserAccountRepository userAccountRepository,
                          ReactionRepository reactionRepository) {
        this.messageRepository = messageRepository;
        this.threadRepository = threadRepository;
        this.userAccountRepository = userAccountRepository;
        this.reactionRepository = reactionRepository;
    }

    /**
     * Create message
     * @param body HTTP request body (message)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createMessage(MessageDtoIn body, String path){
        HttpStatus httpStatus;
        try {
            messageRepository.save(buildMessageFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return buildHttpStatusResponse(httpStatus, path);
    }

    /**
     * Read message
     * @param id Message identifier
     * @return HTTP response (message)
     */
    public ResponseEntity<MessageDtoOut> readMessage(int id){
        final Message message =
                messageRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final MessageDtoOut messageDtoOut =
                buildMessageDtoOutFrom(message);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageDtoOut);
    }

    /**
     * Update message
     * @param id Message identifier
     * @param body HTTP request body (message)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateMessage(int id, MessageDtoIn body, String path){
        final Message message = messageRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        message.setText(body.text());
        final Thread thread = threadRepository
                .findById(body.threadId())
                .orElseThrow(EntityNotFoundException::new);
        message.setThread(thread);
        final UserAccount userAccount = userAccountRepository
                .findById(body.userAccountId())
                .orElseThrow(EntityNotFoundException::new);
        message.setUserAccount(userAccount);
        final List<Reaction> reactions =
                reactionRepository.findAllById(body.reactionIds());
        message.setReactions(reactions);
        messageRepository.save(message);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * Delete message
     * @param id Message identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    deleteMessage(int id, String path){
        final Message message = messageRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        messageRepository.delete(message);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * List messages
     * @return HTTP response (messages)
     */
    public ResponseEntity<List<MessageDtoOut>> listMessages(){
        final List<Message> messages =
                messageRepository.findAll();
        final List<MessageDtoOut> messageDtoOutList = messages
                .stream()
                .map(this::buildMessageDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageDtoOutList);
    }

    /**
     * Build message from HTTP request body (message)
     * @param body HTTP request body (message)
     * @return message
     */
    private Message buildMessageFrom(MessageDtoIn body) {
        final Thread thread = threadRepository
                .findById(body.threadId())
                .orElseThrow(EntityNotFoundException::new);
        final UserAccount userAccount = userAccountRepository
                .findById(body.userAccountId())
                .orElseThrow(EntityNotFoundException::new);
        final List<Reaction> reactions = 
                reactionRepository.findAllById(body.reactionIds());
        return new Message(body.text(), thread, userAccount, reactions);
    }

    /**
     * Build message as HTTP response from message
     * @param message Message
     * @return HTTP response (message)
     */
    private MessageDtoOut
    buildMessageDtoOutFrom(Message message){
        final List<Integer> reactionIds = message
                .getReactions()
                .stream()
                .map(Reaction::getId)
                .toList();
        return new MessageDtoOut(message.getId(),
                message.getText(), 
                message.getThread().getId(),
                message.getUserAccount().getId(),
                reactionIds);
    }
}
