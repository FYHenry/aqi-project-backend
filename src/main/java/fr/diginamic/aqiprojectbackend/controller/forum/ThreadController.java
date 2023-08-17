package fr.diginamic.aqiprojectbackend.controller.forum;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.forum.in.ThreadDtoIn;
import fr.diginamic.aqiprojectbackend.dto.forum.out.ThreadDtoOut;
import fr.diginamic.aqiprojectbackend.service.forum.ThreadService;
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

/** Thread controller */
@RestController
public class ThreadController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(ThreadController.class);
    /** Thread service */
    private final ThreadService threadService;

    /**
     * Constructor with parameters.
     * @param threadService Thread service
     */
    public ThreadController(ThreadService threadService){
        this.threadService = threadService;
    }

    /* Créateur POST */
    /** POST creator */
    @PostMapping(path = "/thread")
    public ResponseEntity<HttpStatusDtoOut>
    createThread(@RequestBody ThreadDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/thread.
                Body :
                 {}
                """,
                body);
        return this.threadService.createThread(body, "/thread");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/thread/{id}")
    public ResponseEntity<ThreadDtoOut>
    readThread(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/thread/{}.
                """,
                id);
        return this.threadService.readThread(id);
    }

    /* Actualiseur PUT */
    /** PUT updater */
    @PutMapping(path = "/thread/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateThread(@PathVariable int id,
                      @RequestBody ThreadDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/thread/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.threadService.updateThread(id,
                body,
                String.format("/thread/%d", id));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @DeleteMapping(path = "/thread/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deleteThread(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/thread/{}.
                """,
                id);
        return this.threadService.deleteThread(id,
                String.format("/thread/%d", id));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/threads")
    public ResponseEntity<List<ThreadDtoOut>> listThreads(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/threads.
                """);
        return this.threadService.listThreads();
    }
}
