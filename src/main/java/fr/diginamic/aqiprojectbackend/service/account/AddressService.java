package fr.diginamic.aqiprojectbackend.service.account;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.account.in.AddressDtoIn;
import fr.diginamic.aqiprojectbackend.dto.account.out.AddressDtoOut;
import fr.diginamic.aqiprojectbackend.entity.account.Address;
import fr.diginamic.aqiprojectbackend.entity.map.City;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.account.AddressRepository;
import fr.diginamic.aqiprojectbackend.repository.map.CityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

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
        HttpStatus httpStatus;
        try {
            addressRepository.save(buildAddressFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpStatusDtoOut(httpStatus.value(),
                        httpStatus.getReasonPhrase()));
    }
    public ResponseEntity<AddressDtoOut> readAddress(int id){
        final Address address =
                addressRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        AddressDtoOut addressDtoOut =
                buildAddressDtoOutFrom(address);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(addressDtoOut);
    }
    public ResponseEntity<HttpStatusDtoOut>
    updateAddress(int id, AddressDtoIn body){
        final Address address = addressRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        address.setAddressLine1(body.addressLine1());
        address.setAddressLine2(body.addressLine2());
        final City city = cityRepository
                .findById(body.cityId())
                .orElseThrow(EntityNotFoundException::new);
        address.setCity(city);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpStatusDtoOut(HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase()));
    }
    public ResponseEntity<HttpStatusDtoOut> deleteAddress(int id){
        final Address address = addressRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        addressRepository.delete(address);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpStatusDtoOut(HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase()));
    }
    public ResponseEntity<List<AddressDtoOut>> listAddresses(){
        final List<Address> userAccounts =
                addressRepository.findAll();
        final List<AddressDtoOut> userAccountDtoOutList = userAccounts
                .stream()
                .map((this::buildAddressDtoOutFrom))
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userAccountDtoOutList);
    }
    private Address buildAddressFrom(AddressDtoIn body){
        City city = cityRepository
                .findById(body.cityId())
                .orElseThrow(EntityNotFoundException::new);
        return new Address(body.addressLine1(),
                body.addressLine2(),
                city);
    }
    private AddressDtoOut buildAddressDtoOutFrom(Address address){
        return new AddressDtoOut(address.getId(),
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity().getInsee());
    }
}
