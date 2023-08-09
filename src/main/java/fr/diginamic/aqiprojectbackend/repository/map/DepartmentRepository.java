package fr.diginamic.aqiprojectbackend.repository.map;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.map.Department;
/** Department repository */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
