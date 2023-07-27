package fr.diginamic.aqiprojectbackend.repository.forum;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.forum.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
