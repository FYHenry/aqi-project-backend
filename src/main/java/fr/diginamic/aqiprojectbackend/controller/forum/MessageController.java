package fr.diginamic.aqiprojectbackend.controller.forum;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.forum.in.MessageDtoIn;
import fr.diginamic.aqiprojectbackend.dto.forum.out.MessageDtoOut;
import fr.diginamic.aqiprojectbackend.service.forum.MessageService;
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

/** Message controller */
@RestController
public class MessageController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(MessageController.class);
    /** Message service */
    private final MessageService messageService;

    /**
     * Constructor with parameters.
     * @param messageService Message service
     */
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    /* Créateur POST */
    /** POST creator */
    @PostMapping(path = "/message")
    public ResponseEntity<HttpStatusDtoOut>
    createMessage(@RequestBody MessageDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/message.
                Body :
                 {}
                """,
                body);
        return this.messageService.createMessage(body, "/message");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/message/{id}")
    public ResponseEntity<MessageDtoOut>
    readMessage(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/message/{}.
                """,
                id);
        return this.messageService.readMessage(id);
    }

    /* Actualiseur PUT */
    /** PUT updater */
    @PutMapping(path = "/message/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateMessage(@PathVariable int id,
                      @RequestBody MessageDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/message/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.messageService.updateMessage(id,
                body,
                String.format("/message/%d", id));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @DeleteMapping(path = "/message/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deleteMessage(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/message/{}.
                """,
                id);
        return this.messageService.deleteMessage(id,
                String.format("/message/%d", id));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/messages")
    public ResponseEntity<List<MessageDtoOut>> listMessages(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/messages.
                """);
        return this.messageService.listMessages();
    }
}
