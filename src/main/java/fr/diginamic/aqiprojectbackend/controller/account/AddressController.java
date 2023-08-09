package fr.diginamic.aqiprojectbackend.controller.account;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.account.in.AddressDtoIn;
import fr.diginamic.aqiprojectbackend.dto.account.out.AddressDtoOut;
import fr.diginamic.aqiprojectbackend.service.account.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/** Address controller */
@RestController
public class AddressController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(AddressController.class);
    /** Address service */
    private final AddressService addressService;

    /**
     * Constructor with parameters.
     * @param addressService Address service
     */
    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }
    /* Créateur POST */
    /** POST creator */
    @PostMapping(path = "/address")
    public ResponseEntity<HttpStatusDtoOut> createAddress(@RequestBody AddressDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/address.
                Body :
                 {}
                """,
                body);
        return this.addressService.createAddress(body);
    }
    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/address/{id}")
    public ResponseEntity<AddressDtoOut> readAddress(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/address/{}.
                """,
                id);
        return this.addressService.readAddress(id);
    }
    /* Actualiseur PUT */
    /** PUT updater */
    @PutMapping(path = "/address/{id}")
    public ResponseEntity<HttpStatusDtoOut> updateAddress(@PathVariable int id,
                                                              @RequestBody AddressDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/address/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.addressService.updateAddress(id, body);
    }
    /* Suppresseur DELETE */
    /** DELETE deleter */
    @DeleteMapping(path = "/address/{id}")
    public ResponseEntity<HttpStatusDtoOut> deleteAddress(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/address/{}.
                """,
                id);
        return this.addressService.deleteAddress(id);
    }
    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/addresses")
    public ResponseEntity<List<AddressDtoOut>> listAddresses(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/addresses.
                """);
        return this.addressService.listAddresses();
    }
}
