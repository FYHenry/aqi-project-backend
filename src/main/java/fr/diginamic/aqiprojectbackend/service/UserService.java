package fr.diginamic.aqiprojectbackend.service;

import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import fr.diginamic.aqiprojectbackend.exception.IdUserNotFound;
import fr.diginamic.aqiprojectbackend.repository.account.UserAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
public class UserService {
    private UserAccountRepository userAccountRepository;

    public UserService(UserAccountRepository userAccountRepository){
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccount findUserById(Integer id){
        Optional<UserAccount> user = this.userAccountRepository.findById(id);

        return user.orElseThrow(() -> new IdUserNotFound("l'utilisateur n'existe pas"));
    }
}
