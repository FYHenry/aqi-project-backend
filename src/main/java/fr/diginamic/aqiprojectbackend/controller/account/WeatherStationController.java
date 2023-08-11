package fr.diginamic.aqiprojectbackend.controller.account;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.WeatherStationDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.WeatherStationDtoOut;
import fr.diginamic.aqiprojectbackend.service.map.WeatherStationService;
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

/** Weather station controller */
@RestController
public class WeatherStationController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(WeatherStationController.class);
    /** Weather station service */
    private final WeatherStationService weatherStationService;

    /**
     * Constructor with parameters.
     * @param weatherStationService Weather station service
     */
    public WeatherStationController(WeatherStationService weatherStationService){
        this.weatherStationService = weatherStationService;
    }

    /* Créateur POST */
    /** POST creator */
    @PostMapping(path = "/weather-station")
    public ResponseEntity<HttpStatusDtoOut>
    createWeatherStation(@RequestBody WeatherStationDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/weather-station.
                Body :
                 {}
                """,
                body);
        return this.weatherStationService.createWeatherStation(body, "/weather-station");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/weather-station/{id}")
    public ResponseEntity<WeatherStationDtoOut>
    readWeatherStation(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/weather-station/{}.
                """,
                id);
        return this.weatherStationService.readWeatherStation(id);
    }

    /* Actualiseur PUT */
    /** PUT updater */
    @PutMapping(path = "/weather-station/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateWeatherStation(@PathVariable int id,
                      @RequestBody WeatherStationDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/weather-station/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.weatherStationService.updateWeatherStation(id,
                body,
                String.format("/weather-station/%d", id));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @DeleteMapping(path = "/weather-station/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deleteWeatherStation(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/weather-station/{}.
                """,
                id);
        return this.weatherStationService.deleteWeatherStation(id,
                String.format("/weather-station/%d", id));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/weather-stations")
    public ResponseEntity<List<WeatherStationDtoOut>> listWeatherStations(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/weather-stations.
                """);
        return this.weatherStationService.listWeatherStations();
    }
}
