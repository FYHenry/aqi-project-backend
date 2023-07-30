package fr.diginamic.aqiprojectbackend.controller;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.account.in.UserAccountDtoIn;
import fr.diginamic.aqiprojectbackend.dto.account.out.UserAccountDtoOut;
import fr.diginamic.aqiprojectbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** User account CRUD controller */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    /* Créateur POST */
    /** POST creator */
    @PostMapping(path = "/user/create")
    public ResponseEntity<HttpStatusDtoOut> createUserAccount(@RequestBody UserAccountDtoIn body) {
        return this.userService.createUserAccount(body);
    }
    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/user/read/{id}")
    public ResponseEntity<UserAccountDtoOut> readUserAccount(@PathVariable int id) {
        return this.userService.readUserAccount(id);
    }
    /* Actualiseur POST */
    /** POST updater */
    @PostMapping(path = "/user/update/{id}")
    public ResponseEntity<HttpStatusDtoOut> updateUserAccount(@PathVariable int id,
                                    @RequestBody UserAccountDtoIn body) {
        return this.userService.updateUserAccount(id, body);
    }
    /* Suppresseur POST */
    /** POST deleter */
    @PostMapping(path = "/user/delete/{id}")
    public ResponseEntity<HttpStatusDtoOut> deleteUserAccount(@PathVariable int id) {
        return this.userService.deleteUserAccount(id);
    }

}
