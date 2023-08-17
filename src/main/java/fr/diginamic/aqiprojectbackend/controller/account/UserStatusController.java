package fr.diginamic.aqiprojectbackend.controller.account;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.account.in.UserStatusDtoIn;
import fr.diginamic.aqiprojectbackend.dto.account.out.UserStatusDtoOut;
import fr.diginamic.aqiprojectbackend.service.account.UserStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserStatusController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(UserStatusController.class);
    /** User status service */
    private final UserStatusService userStatusService;

    /**
     * Constructor with parameters.
     * @param userStatusService User status service
     */
    public UserStatusController(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }
    
    /* Créateur POST */
    /** POST creator */
    @PostMapping(path = "/user-status")
    public ResponseEntity<HttpStatusDtoOut>
    createUserStatus(@RequestBody UserStatusDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/user-status.
                Body :
                 {}
                """,
                body);
        return this.userStatusService.createUserStatus(body,
                "/user-status");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/user-status/{id}")
    public ResponseEntity<UserStatusDtoOut>
    readUserStatus(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/user-status/{}.
                """,
                id);
        return this.userStatusService.readUserStatus(id);
    }
    
    /* Actualiseur PUT */
    /** PUT updater */
    @PutMapping(path = "/user-status/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateUserStatus(@PathVariable int id, @RequestBody UserStatusDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/user-status/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.userStatusService.updateUserStatus(id,
                body,
                String.format("/user-status/%d", id));
    }
    
    /* Suppresseur DELETE */
    /** DELETE deleter */
    @DeleteMapping(path = "/user-status/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deleteUserStatus(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/user-status/{}.
                """,
                id);
        return this.userStatusService.deleteUserStatus(id,
                String.format("/user/%d", id));
    }
    
    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/user-statuses")
    public ResponseEntity<List<UserStatusDtoOut>> listUserStatuses(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/user-statuses.
                """);
        return this.userStatusService.listUserStatuses();
    }
}
