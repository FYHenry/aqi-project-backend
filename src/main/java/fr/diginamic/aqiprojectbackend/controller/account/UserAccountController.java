package fr.diginamic.aqiprojectbackend.controller.account;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.account.in.UserAccountDtoIn;
import fr.diginamic.aqiprojectbackend.dto.account.in.UserUpdPwdDtoIn;
import fr.diginamic.aqiprojectbackend.dto.account.in.UserRegistrationFormDtoIn;
import fr.diginamic.aqiprojectbackend.dto.account.in.UserUpdateProfileFormDtoIn;
import fr.diginamic.aqiprojectbackend.dto.account.out.ConnectedUser;
import fr.diginamic.aqiprojectbackend.dto.account.out.UserAccountDtoOut;
import fr.diginamic.aqiprojectbackend.service.account.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

    /** POST creator */
    /* @PostMapping(path = "/user-account")
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
*/
    /** POST creator */
    @PostMapping(path = "/user-account")
    public ResponseEntity<HttpStatusDtoOut>
    createUserAcc(@RequestBody UserRegistrationFormDtoIn body) {
        System.out.println("UserAccountController - createUserAcc");
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/user-account.
                Body :
                 {}
                """,
                body);
        return this.userAccountService.createUserAcc(body, "/user-account");
    }

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

    /* Actualiseur PUT */
    /** PUT updater */
    @PutMapping(path = "/user-account/profile/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateUserProfile(@PathVariable int id,
                     @RequestBody UserUpdateProfileFormDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/user-account/profile/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.userAccountService.updateUserProfile(id,
                body,
                String.format("/user-account/profile/%d", id));
    }

    @PutMapping(path = "/user-account/pwd/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateUserPwd(@PathVariable int id,
                  @RequestBody UserUpdPwdDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/user-account/pwd/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.userAccountService.updateUserPwd(id,
                body,
                String.format("/user-account/pwd/%d", id));
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

    /** GET lister */
    // activation de la sécurisation des méthodes - via annotation '@EnableMethodSecurity(securedEnabled = true)' dans fichier WebSecurityConfig
    @Secured("ADMIN")
    @GetMapping(path = "/user-accounts")
    public ResponseEntity<List<UserAccountDtoOut>> listUserAccounts(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/user-accounts.
                """);
        return this.userAccountService.listUserAccounts();
    }

    /** GET connected user */
    @GetMapping(path = "/connectedUser/{id}")
    public ResponseEntity<ConnectedUser> connectedUser(@PathVariable int id) {
        logger.info("""
                GET connected user called by : http://127.0.0.1:8080/connectedUser/{}
                """);
        return this.userAccountService.connectedUser(id);
    }
}
