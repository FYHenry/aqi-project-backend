package fr.diginamic.aqiprojectbackend.repository.forum;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.forum.Message;
/** Message repository */
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
