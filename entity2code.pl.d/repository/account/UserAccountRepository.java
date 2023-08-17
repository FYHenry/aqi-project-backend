package fr.diginamic.aqiprojectbackend.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
/** User account repository */
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

}
