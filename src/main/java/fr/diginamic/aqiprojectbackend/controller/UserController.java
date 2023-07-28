package fr.diginamic.aqiprojectbackend.controller;

import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import fr.diginamic.aqiprojectbackend.repository.account.AddressRepository;
//import fr.diginamic.aqiprojectbackend.repository.account.CredentialRepository;
import fr.diginamic.aqiprojectbackend.repository.account.UserAccountRepository;
import fr.diginamic.aqiprojectbackend.repository.account.UserStatusRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.MessageRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.ReactionRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.ThreadRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.TopicRepository;
import fr.diginamic.aqiprojectbackend.repository.map.BookmarkRepository;
import fr.diginamic.aqiprojectbackend.service.UserService;
import org.springframework.http.ResponseEntity;
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
