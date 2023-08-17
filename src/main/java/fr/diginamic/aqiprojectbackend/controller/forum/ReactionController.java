package fr.diginamic.aqiprojectbackend.controller.forum;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.forum.in.ReactionDtoIn;
import fr.diginamic.aqiprojectbackend.dto.forum.out.ReactionDtoOut;
import fr.diginamic.aqiprojectbackend.service.forum.ReactionService;
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

/** Reaction controller */
@RestController
public class ReactionController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(ReactionController.class);
    /** Reaction service */
    private final ReactionService reactionService;

    /**
     * Constructor with parameters.
     * @param reactionService Reaction service
     */
    public ReactionController(ReactionService reactionService){
        this.reactionService = reactionService;
    }

    /* Créateur POST */
    /** POST creator */
    @PostMapping(path = "/reaction")
    public ResponseEntity<HttpStatusDtoOut>
    createReaction(@RequestBody ReactionDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/reaction.
                Body :
                 {}
                """,
                body);
        return this.reactionService.createReaction(body, "/reaction");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/reaction/{id}")
    public ResponseEntity<ReactionDtoOut>
    readReaction(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/reaction/{}.
                """,
                id);
        return this.reactionService.readReaction(id);
    }

    /* Actualiseur PUT */
    /** PUT updater */
    @PutMapping(path = "/reaction/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateReaction(@PathVariable int id,
                      @RequestBody ReactionDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/reaction/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.reactionService.updateReaction(id,
                body,
                String.format("/reaction/%d", id));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @DeleteMapping(path = "/reaction/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deleteReaction(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/reaction/{}.
                """,
                id);
        return this.reactionService.deleteReaction(id,
                String.format("/reaction/%d", id));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/reactions")
    public ResponseEntity<List<ReactionDtoOut>> listReactions(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/reactions.
                """);
        return this.reactionService.listReactions();
    }
}
