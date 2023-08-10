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

import static fr.diginamic.aqiprojectbackend.utils.Dtos.buildHttpStatusDtoOut;

/** Address service */
@Service
@Validated
public class AddressService {
    /** Address repository */
    private final AddressRepository addressRepository;
    /** City repository */
    private final CityRepository cityRepository;

    /**
     * Constructor with parameters.
     * @param addressRepository Address repository
     * @param cityRepository City repository
     */
    public AddressService(AddressRepository addressRepository, CityRepository cityRepository) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
    }

    /**
     * Create address
     * @param body HTTP request body (address)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut> createAddress(AddressDtoIn body, String path){
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
                .body(buildHttpStatusDtoOut(httpStatus, path));
    }

    /**
     * Read address
     * @param id Address identifier
     * @return HTTP response (address)
     */
    public ResponseEntity<AddressDtoOut> readAddress(int id){
        final Address address =
                addressRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final AddressDtoOut addressDtoOut =
                buildAddressDtoOutFrom(address);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(addressDtoOut);
    }

    /**
     * Update address
     * @param id Address identifier
     * @param body HTTP request body (address)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateAddress(int id, AddressDtoIn body, String path){
        final Address address = addressRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        address.setAddressLine1(body.addressLine1());
        address.setAddressLine2(body.addressLine2());
        final City city = cityRepository
                .findById(body.cityId())
                .orElseThrow(EntityNotFoundException::new);
        address.setCity(city);
        final HttpStatus httpStatus = HttpStatus.OK;
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buildHttpStatusDtoOut(httpStatus, path));
    }

    /**
     * Delete address
     * @param id Address identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut> deleteAddress(int id, String path){
        final Address address = addressRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        addressRepository.delete(address);
        final HttpStatus httpStatus = HttpStatus.OK;
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buildHttpStatusDtoOut(httpStatus, path));
    }

    /**
     * List addresses
     * @return HTTP response (addresses)
     */
    public ResponseEntity<List<AddressDtoOut>> listAddresses(){
        final List<Address> addresses =
                addressRepository.findAll();
        final List<AddressDtoOut> addressDtoOutList = addresses
                .stream()
                .map(this::buildAddressDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(addressDtoOutList);
    }

    /**
     * Build address from HTTP request body (address)
     * @param body HTTP request body (address)
     * @return Address
     */
    private Address buildAddressFrom(AddressDtoIn body){
        City city = cityRepository
                .findById(body.cityId())
                .orElseThrow(EntityNotFoundException::new);
        return new Address(body.addressLine1(),
                body.addressLine2(),
                city);
    }

    /**
     * Build address as HTTP response from address
     * @param address Address
     * @return HTTP response (address)
     */
    private AddressDtoOut buildAddressDtoOutFrom(Address address){
        return new AddressDtoOut(address.getId(),
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity().getInsee());
    }
}
