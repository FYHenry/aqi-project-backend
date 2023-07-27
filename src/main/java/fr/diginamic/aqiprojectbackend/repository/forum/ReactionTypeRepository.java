package fr.diginamic.aqiprojectbackend.repository.forum;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.forum.ReactionType;

public interface ReactionTypeRepository extends JpaRepository<ReactionType, Integer> {

}
