package fr.diginamic.aqiprojectbackend.controller.account;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.account.in.UserAccountDtoIn;
import fr.diginamic.aqiprojectbackend.dto.account.out.UserAccountDtoOut;
import fr.diginamic.aqiprojectbackend.service.account.UserAccountService;
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

/** User account CRUD controller */
@RestController
public class UserAccountController {
    private static final Logger logger =
            LoggerFactory.getLogger(UserAccountController.class);

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService){
        this.userAccountService = userAccountService;
    }
    /* Créateur POST */
    /** POST creator */
    @PostMapping(path = "/user")
    public ResponseEntity<HttpStatusDtoOut> createUserAccount(@RequestBody UserAccountDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/user.
                Body :
                 {}
                """,
                body);
        return this.userAccountService.createUserAccount(body);
    }
    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<UserAccountDtoOut> readUserAccount(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/user/{}.
                """,
                id);
        return this.userAccountService.readUserAccount(id);
    }
    /* Actualiseur PUT */
    /** PUT updater */
    @PutMapping(path = "/user/{id}")
    public ResponseEntity<HttpStatusDtoOut> updateUserAccount(@PathVariable int id,
                                    @RequestBody UserAccountDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/user/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.userAccountService.updateUserAccount(id, body);
    }
    /* Suppresseur DELETE */
    /** DELETE deleter */
    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity<HttpStatusDtoOut> deleteUserAccount(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/user/{}.
                """,
                id);
        return this.userAccountService.deleteUserAccount(id);
    }
    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/users")
    public ResponseEntity<List<UserAccountDtoOut>> listUserAccounts(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/users.
                """);
        return this.userAccountService.listUserAccounts();
    }
}
