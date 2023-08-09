package fr.diginamic.aqiprojectbackend.repository.forum;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.forum.Thread;
/** Thread repository */
public interface ThreadRepository extends JpaRepository<Thread, Integer> {

}
