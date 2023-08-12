package fr.diginamic.aqiprojectbackend.controller.forum;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.forum.in.TopicDtoIn;
import fr.diginamic.aqiprojectbackend.dto.forum.out.TopicDtoOut;
import fr.diginamic.aqiprojectbackend.service.forum.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/** Topic controller */
@RestController
public class TopicController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(TopicController.class);
    /** Topic service */
    private final TopicService topicService;

    /**
     * Constructor with parameters.
     * @param topicService Topic service
     */
    public TopicController(TopicService topicService){
        this.topicService = topicService;
    }

    /* Créateur POST */
    /** POST creator */
    @PostMapping(path = "/topic")
    public ResponseEntity<HttpStatusDtoOut>
    createTopic(@RequestBody TopicDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/topic.
                Body :
                 {}
                """,
                body);
        return this.topicService.createTopic(body, "/topic");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/topic/{id}")
    public ResponseEntity<TopicDtoOut>
    readTopic(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/topic/{}.
                """,
                id);
        return this.topicService.readTopic(id);
    }

    /* Actualiseur PUT */
    /** PUT updater */
    @PutMapping(path = "/topic/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateTopic(@PathVariable int id,
                      @RequestBody TopicDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/topic/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.topicService.updateTopic(id,
                body,
                String.format("/topic/%d", id));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @DeleteMapping(path = "/topic/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deleteTopic(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/topic/{}.
                """,
                id);
        return this.topicService.deleteTopic(id,
                String.format("/topic/%d", id));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/topics")
    public ResponseEntity<List<TopicDtoOut>> listTopics(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/topics.
                """);
        return this.topicService.listTopics();
    }
}
