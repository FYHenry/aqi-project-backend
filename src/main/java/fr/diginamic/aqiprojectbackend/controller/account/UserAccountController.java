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

/** User account controller */
@RestController
public class UserAccountController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(UserAccountController.class);
    /** User account service */
    private final UserAccountService userAccountService;

    /**
     * Constructor with parameters.
     * @param userAccountService User account service
     */
    public UserAccountController(UserAccountService userAccountService){
        this.userAccountService = userAccountService;
    }

    /* Créateur POST */
    /** POST creator */
    @PostMapping(path = "/user-account")
    public ResponseEntity<HttpStatusDtoOut>
    createUserAccount(@RequestBody UserAccountDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/user-account.
                Body :
                 {}
                """,
                body);
        return this.userAccountService.createUserAccount(body, "/user-account");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/user-account/{id}")
    public ResponseEntity<UserAccountDtoOut>
    readUserAccount(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/user-account/{}.
                """,
                id);
        return this.userAccountService.readUserAccount(id);
    }

    /* Actualiseur PUT */
    /** PUT updater */
    @PutMapping(path = "/user-account/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateUserAccount(@PathVariable int id,
                      @RequestBody UserAccountDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/user-account/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.userAccountService.updateUserAccount(id,
                body,
                String.format("/user-account/%d", id));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @DeleteMapping(path = "/user-account/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deleteUserAccount(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/user-account/{}.
                """,
                id);
        return this.userAccountService.deleteUserAccount(id,
                String.format("/user-account/%d", id));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/user-accounts")
    public ResponseEntity<List<UserAccountDtoOut>> listUserAccounts(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/user-accounts.
                """);
        return this.userAccountService.listUserAccounts();
    }
}
