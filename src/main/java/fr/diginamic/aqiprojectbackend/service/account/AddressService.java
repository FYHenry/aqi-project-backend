package fr.diginamic.aqiprojectbackend.service.account;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.account.in.AddressDtoIn;
import fr.diginamic.aqiprojectbackend.repository.account.AddressRepository;
import fr.diginamic.aqiprojectbackend.repository.map.CityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class AddressService {
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;

    public AddressService(AddressRepository addressRepository, CityRepository cityRepository) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
    }
    public ResponseEntity<HttpStatusDtoOut> createAddress(AddressDtoIn body){
        final HttpStatus status = HttpStatus.NO_CONTENT;
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpStatusDtoOut(status.value(),
                        status.getReasonPhrase()));
    }
}
