package fr.diginamic.aqiprojectbackend.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.account.UserStatus;

public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {

}
