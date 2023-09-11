package fr.diginamic.aqiprojectbackend.controller.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.account.out.CityForm;
import fr.diginamic.aqiprojectbackend.dto.map.in.CityDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.CityDtoOut;
import fr.diginamic.aqiprojectbackend.service.map.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** City controller */
@RestController
public class CityController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(CityController.class);
    /** City service */
    private final CityService cityService;

    /**
     * Constructor with parameters.
     * @param cityService City service
     */
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /* Créateur POST */
    /** POST creator */
    @Secured("ADMIN")
    @PostMapping(path = "/city")
    public ResponseEntity<HttpStatusDtoOut>
    createCity(@RequestBody CityDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/city.
                Body :
                 {}
                """,
                body);
        return this.cityService.createCity(body, "/city");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/city/{insee}")
    public ResponseEntity<CityDtoOut> readCity(@PathVariable String insee) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/city/{}.
                """,
                insee);
        return this.cityService.readCity(insee);
    }

      /* Actualiseur PUT */
    /** PUT updater */
    @Secured("ADMIN")
    @PutMapping(path = "/city/{insee}")
    public ResponseEntity<HttpStatusDtoOut>
    updateCity(@PathVariable String insee, @RequestBody CityDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/city/{}.
                Body :
                 {}
                """,
                insee,
                body);
        return this.cityService.updateCity(insee,
                body,
                String.format("/city/%d", insee));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @Secured("ADMIN")
    @DeleteMapping(path = "/city/{insee}")
    public ResponseEntity<HttpStatusDtoOut> deleteCity(@PathVariable String insee) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/city/{}.
                """,
                insee);
        return this.cityService.deleteCity(insee,
                String.format("/city/%d", insee));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/cities")
    public ResponseEntity<List<CityDtoOut>> listCities(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/cities.
                """);
        return this.cityService.listCities();
    }

    /* Listeur cityForm GET */
    /** GET lister cityForm */
    @GetMapping(path = "/cityForm/{name}")
    public ResponseEntity<List<CityForm>> listCitiesForm(@PathVariable String name){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/cityForm/{}.
                """);
        return this.cityService.cityForm(name);
    }
}
