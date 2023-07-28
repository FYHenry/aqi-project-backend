package fr.diginamic.aqiprojectbackend.controller;

import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import fr.diginamic.aqiprojectbackend.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @RequestMapping(path = "/user/{id}")
    public UserAccount findUserById(@PathVariable Integer id) {
        return this.userService.findUserById(id);
    }
}
