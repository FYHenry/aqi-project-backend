package fr.diginamic.aqiprojectbackend.controller;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.account.in.UserAccountDtoIn;
import fr.diginamic.aqiprojectbackend.dto.account.out.UserAccountDtoOut;
import fr.diginamic.aqiprojectbackend.service.UserService;
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

/** User account CRUD controller */
@RestController
public class UserController {
    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    /* Créateur POST */
    /** POST creator */
    @PostMapping(path = "/user")
    public ResponseEntity<HttpStatusDtoOut> createUserAccount(@RequestBody UserAccountDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/user.
                Body :
                 {}""", body);
        return this.userService.createUserAccount(body);
    }
    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<UserAccountDtoOut> readUserAccount(@PathVariable int id) {
        logger.info("GET reader called by : http://127.0.0.1:8080/user/{}.\n",
                id);
        return this.userService.readUserAccount(id);
    }
    /* Actualiseur PUT */
    /** PUT updater */
    @PutMapping(path = "/user/{id}")
    public ResponseEntity<HttpStatusDtoOut> updateUserAccount(@PathVariable int id,
                                    @RequestBody UserAccountDtoIn body) {
        return this.userService.updateUserAccount(id, body);
    }
    /* Suppresseur DELETE */
    /** DELETE deleter */
    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity<HttpStatusDtoOut> deleteUserAccount(@PathVariable int id) {
        return this.userService.deleteUserAccount(id);
    }

}
