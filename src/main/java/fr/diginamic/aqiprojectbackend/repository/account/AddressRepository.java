package fr.diginamic.aqiprojectbackend.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.aqiprojectbackend.entity.account.Address;
/** Address repository */
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
