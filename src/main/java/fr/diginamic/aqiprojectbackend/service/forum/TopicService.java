package fr.diginamic.aqiprojectbackend.service.forum;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.forum.in.TopicDtoIn;
import fr.diginamic.aqiprojectbackend.dto.forum.out.TopicDtoOut;
import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import fr.diginamic.aqiprojectbackend.entity.forum.Thread;
import fr.diginamic.aqiprojectbackend.entity.forum.Topic;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.account.UserAccountRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.ThreadRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.TopicRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.util.Dtos.buildHttpStatusResponse;

@Service
@Validated
public class TopicService {
    /** Topic repository */
    private final TopicRepository topicRepository;
    /** Thread repository */
    private final ThreadRepository threadRepository;
    /** User account repository */
    private final UserAccountRepository userAccountRepository;

    /**
     * Constructor with parameters.
     * @param topicRepository Topic repository
     * @param threadRepository Thread repository
     * @param userAccountRepository User account repository
     */
    public TopicService(TopicRepository topicRepository,
                        ThreadRepository threadRepository,
                        UserAccountRepository userAccountRepository) {
        this.topicRepository = topicRepository;
        this.threadRepository = threadRepository;
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * Create topic
     * @param body HTTP request body (topic)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createTopic(TopicDtoIn body, String path){
        HttpStatus httpStatus;
        try {
            topicRepository.save(buildTopicFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return buildHttpStatusResponse(httpStatus, path);
    }

    /**
     * Read topic
     * @param id Topic identifier
     * @return HTTP response (topic)
     */
    public ResponseEntity<TopicDtoOut> readTopic(int id){
        final Topic topic =
                topicRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final TopicDtoOut topicDtoOut =
                buildTopicDtoOutFrom(topic);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(topicDtoOut);
    }

    /**
     * Update topic
     * @param id Topic identifier
     * @param body HTTP request body (topic)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateTopic(int id, TopicDtoIn body, String path){
        final Topic topic = topicRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        topic.setTitle(body.title());
        final List<Thread> threads = threadRepository
                .findAllById(body.threadIds());
        topic.setThreads(threads);
        final UserAccount userAccount = userAccountRepository
                .findById(body.userAccountId())
                .orElseThrow(EntityNotFoundException::new);
        topic.setUserAccount(userAccount);
        topicRepository.save(topic);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * Delete Topic
     * @param id Topic identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    deleteTopic(int id, String path){
        final Topic topic = topicRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        topicRepository.delete(topic);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * List topics
     * @return HTTP response (topics)
     */
    public ResponseEntity<List<TopicDtoOut>> listTopics(){
        final List<Topic> topics =
                topicRepository.findAll();
        final List<TopicDtoOut> topicDtoOutList = topics
                .stream()
                .map(this::buildTopicDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(topicDtoOutList);
    }

    /**
     * Build topic from HTTP request body (topic)
     * @param body HTTP request body (topic)
     * @return Topic
     */
    private Topic buildTopicFrom(TopicDtoIn body) {
        final List<Thread> threads =
                threadRepository.findAllById(body.threadIds());
        final UserAccount userAccount = userAccountRepository
                .findById(body.userAccountId())
                .orElseThrow(EntityNotFoundException::new);
        return new Topic(body.title(), threads, userAccount);
    }

    /**
     * Build topic as HTTP response from topic
     * @param topic Topic
     * @return HTTP response (topic)
     */
    private TopicDtoOut
    buildTopicDtoOutFrom(Topic topic){
        final List<Integer> threadIds = topic
                .getThreads()
                .stream()
                .map(Thread::getId)
                .toList();
        return new TopicDtoOut(topic.getId(),
                topic.getTitle(),
                threadIds,
                topic.getUserAccount().getId());
    }
}
