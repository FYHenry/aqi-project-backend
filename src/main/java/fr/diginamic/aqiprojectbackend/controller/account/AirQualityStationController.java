package fr.diginamic.aqiprojectbackend.controller.account;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.AirQualityStationDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.AirQualityStationDtoOut;
import fr.diginamic.aqiprojectbackend.service.map.AirQualityStationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/** Air quality station controller */
@RestController
public class AirQualityStationController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(AirQualityStationController.class);
    /** Air quality station service */
    private final AirQualityStationService airQualityStationService;

    /**
     * Constructor with parameters.
     * @param airQualityStationService Air quality station service
     */
    public AirQualityStationController(AirQualityStationService airQualityStationService){
        this.airQualityStationService = airQualityStationService;
    }

    /* Créateur POST */
    /** POST creator */
    @PostMapping(path = "/air-quality-station")
    public ResponseEntity<HttpStatusDtoOut>
    createAirQualityStation(@RequestBody AirQualityStationDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/air-quality-station.
                Body :
                 {}
                """,
                body);
        return this.airQualityStationService.createAirQualityStation(body, "/air-quality-station");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/air-quality-station/{id}")
    public ResponseEntity<AirQualityStationDtoOut>
    readAirQualityStation(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/air-quality-station/{}.
                """,
                id);
        return this.airQualityStationService.readAirQualityStation(id);
    }

    /* Actualiseur PUT */
    /** PUT updater */
    @PutMapping(path = "/air-quality-station/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateAirQualityStation(@PathVariable int id,
                         @RequestBody AirQualityStationDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/air-quality-station/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.airQualityStationService.updateAirQualityStation(id,
                body,
                String.format("/air-quality-station/%d", id));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @DeleteMapping(path = "/air-quality-station/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deleteAirQualityStation(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/air-quality-station/{}.
                """,
                id);
        return this.airQualityStationService.deleteAirQualityStation(id,
                String.format("/air-quality-station/%d", id));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/air-quality-stations")
    public ResponseEntity<List<AirQualityStationDtoOut>> listAirQualityStations(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/air-quality-stations.
                """);
        return this.airQualityStationService.listAirQualityStations();
    }
}
