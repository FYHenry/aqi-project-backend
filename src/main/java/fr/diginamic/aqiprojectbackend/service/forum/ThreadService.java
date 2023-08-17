package fr.diginamic.aqiprojectbackend.service.forum;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.forum.in.ThreadDtoIn;
import fr.diginamic.aqiprojectbackend.dto.forum.out.ThreadDtoOut;
import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import fr.diginamic.aqiprojectbackend.entity.forum.Message;
import fr.diginamic.aqiprojectbackend.entity.forum.Thread;
import fr.diginamic.aqiprojectbackend.entity.forum.Topic;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.account.UserAccountRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.MessageRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.ThreadRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.TopicRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.util.Dtos.buildHttpStatusResponse;
/** Thread service */
@Service
@Validated
public class ThreadService {
    /** Thread repository */
    private final ThreadRepository threadRepository;
    /** Topic repository */
    private final TopicRepository topicRepository;
    /** Message repository */
    private final MessageRepository messageRepository;
    /** User account repository */
    private final UserAccountRepository userAccountRepository;

    /**
     * Constructor with parameters.
     * @param threadRepository Thread repository
     * @param topicRepository Topic repository
     * @param messageRepository Message repository
     * @param userAccountRepository User account repository
     */
    public ThreadService(ThreadRepository threadRepository,
                         TopicRepository topicRepository,
                         MessageRepository messageRepository,
                         UserAccountRepository userAccountRepository) {
        this.threadRepository = threadRepository;
        this.topicRepository = topicRepository;
        this.messageRepository = messageRepository;
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * Create thread
     * @param body HTTP request body (thread)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createThread(ThreadDtoIn body, String path){
        HttpStatus httpStatus;
        try {
            threadRepository.save(buildThreadFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return buildHttpStatusResponse(httpStatus, path);
    }

    /**
     * Read thread
     * @param id Thread identifier
     * @return HTTP response (thread)
     */
    public ResponseEntity<ThreadDtoOut> readThread(int id){
        final Thread thread =
                threadRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final ThreadDtoOut threadDtoOut =
                buildThreadDtoOutFrom(thread);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(threadDtoOut);
    }

    /**
     * Update thread
     * @param id Thread identifier
     * @param body HTTP request body (thread)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateThread(int id, ThreadDtoIn body, String path){
        final Thread thread = threadRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        thread.setTitle(body.title());
        final Topic topic = topicRepository
                .findById(body.topicId())
                .orElseThrow(EntityNotFoundException::new);
        thread.setTopic(topic);
        final List<Message> messages =
                messageRepository.findAllById(body.messageIds());
        thread.setMessages(messages);
        final UserAccount userAccount = userAccountRepository
                .findById(body.userAccountId())
                .orElseThrow(EntityNotFoundException::new);
        thread.setUserAccount(userAccount);
        threadRepository.save(thread);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * Delete thread
     * @param id Thread identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    deleteThread(int id, String path){
        final Thread thread = threadRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        threadRepository.delete(thread);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * List threads
     * @return HTTP response (threads)
     */
    public ResponseEntity<List<ThreadDtoOut>> listThreads(){
        final List<Thread> threads =
                threadRepository.findAll();
        final List<ThreadDtoOut> threadDtoOutList = threads
                .stream()
                .map(this::buildThreadDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(threadDtoOutList);
    }

    /**
     * Build thread from HTTP request body (thread)
     * @param body HTTP request body (thread)
     * @return Thread
     */
    private Thread buildThreadFrom(ThreadDtoIn body) {
        final Topic topic = topicRepository
                .findById(body.topicId())
                .orElseThrow(EntityNotFoundException::new);
        final List<Message> messages =
                messageRepository.findAllById(body.messageIds());
        final UserAccount userAccount = userAccountRepository
                .findById(body.userAccountId())
                .orElseThrow(EntityNotFoundException::new);
        return new Thread(body.title(), topic, messages, userAccount);
    }

    /**
     * Build thread as HTTP response from thread
     * @param thread Thread
     * @return HTTP response (thread)
     */
    private ThreadDtoOut
    buildThreadDtoOutFrom(Thread thread){
        final List<Integer> messageIds = thread
                .getMessages()
                .stream()
                .map(Message::getId)
                .toList();
        return new ThreadDtoOut(thread.getId(),
                thread.getTitle(),
                thread.getTopic().getId(),
                messageIds,
                thread.getUserAccount().getId());
    }
}
