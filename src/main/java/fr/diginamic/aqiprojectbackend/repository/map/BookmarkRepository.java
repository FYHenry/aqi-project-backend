package fr.diginamic.aqiprojectbackend.repository.map;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.map.Bookmark;
/** Bookmark repository */
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
}
