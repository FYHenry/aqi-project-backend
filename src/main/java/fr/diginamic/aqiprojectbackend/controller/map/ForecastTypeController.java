package fr.diginamic.aqiprojectbackend.controller.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.ForecastTypeDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.ForecastTypeDtoOut;
import fr.diginamic.aqiprojectbackend.service.map.ForecastTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

/** Forecast type controller */
@RestController
public class ForecastTypeController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(ForecastTypeController.class);
    /** Forecast type service */
    private final ForecastTypeService forecastTypeService;

    /**
     * Constructor with parameters.
     * @param forecastTypeService Forecast type service
     */
    public ForecastTypeController(ForecastTypeService forecastTypeService){
        this.forecastTypeService = forecastTypeService;
    }

    /* Créateur POST */
    /** POST creator */
    @Secured("ADMIN")
    @PostMapping(path = "/forecast-type")
    public ResponseEntity<HttpStatusDtoOut>
    createForecastType(@RequestBody ForecastTypeDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/forecast-type.
                Body :
                 {}
                """,
                body);
        return this.forecastTypeService.createForecastType(body, "/forecast-type");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/forecast-type/{id}")
    public ResponseEntity<ForecastTypeDtoOut>
    readForecastType(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/forecast-type/{}.
                """,
                id);
        return this.forecastTypeService.readForecastType(id);
    }

    /* Actualiseur PUT */
    /** PUT updater */
    @Secured("ADMIN")
    @PutMapping(path = "/forecast-type/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateForecastType(@PathVariable int id,
                      @RequestBody ForecastTypeDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/forecast-type/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.forecastTypeService.updateForecastType(id,
                body,
                String.format("/forecast-type/%d", id));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @Secured("ADMIN")
    @DeleteMapping(path = "/forecast-type/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deleteForecastType(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/forecast-type/{}.
                """,
                id);
        return this.forecastTypeService.deleteForecastType(id,
                String.format("/forecast-type/%d", id));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/forecast-types")
    public ResponseEntity<List<ForecastTypeDtoOut>> listForecastTypes(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/forecast-types.
                """);
        return this.forecastTypeService.listForecastTypes();
    }
}
