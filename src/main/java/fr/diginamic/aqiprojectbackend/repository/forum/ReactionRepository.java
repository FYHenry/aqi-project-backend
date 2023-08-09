package fr.diginamic.aqiprojectbackend.repository.forum;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.forum.Reaction;
/** Reaction repository */
public interface ReactionRepository extends JpaRepository<Reaction, Integer> {

}
