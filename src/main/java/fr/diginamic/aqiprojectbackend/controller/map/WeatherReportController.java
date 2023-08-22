package fr.diginamic.aqiprojectbackend.controller.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.WeatherReportDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.WeatherReportDtoOut;
import fr.diginamic.aqiprojectbackend.service.map.WeatherReportService;
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

/** Weather report controller */
@RestController
public class WeatherReportController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(WeatherReportController.class);
    /** Weather report service */
    private final WeatherReportService weatherReportService;

    /**
     * Constructor with parameters.
     * @param weatherReportService Weather report service
     */
    public WeatherReportController(WeatherReportService weatherReportService){
        this.weatherReportService = weatherReportService;
    }

    /* Créateur POST */
    /** POST creator */
    @Secured("ADMIN")
    @PostMapping(path = "/weather-report")
    public ResponseEntity<HttpStatusDtoOut>
    createWeatherReport(@RequestBody WeatherReportDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/weather-report.
                Body :
                 {}
                """,
                body);
        return this.weatherReportService.createWeatherReport(body, "/weather-report");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/weather-report/{id}")
    public ResponseEntity<WeatherReportDtoOut>
    readWeatherReport(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/weather-report/{}.
                """,
                id);
        return this.weatherReportService.readWeatherReport(id);
    }

    /* Actualiseur PUT */
    /** PUT updater */
    @Secured("ADMIN")
    @PutMapping(path = "/weather-report/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateWeatherReport(@PathVariable int id,
                      @RequestBody WeatherReportDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/weather-report/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.weatherReportService.updateWeatherReport(id,
                body,
                String.format("/weather-report/%d", id));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @Secured("ADMIN")
    @DeleteMapping(path = "/weather-report/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deleteWeatherReport(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/weather-report/{}.
                """,
                id);
        return this.weatherReportService.deleteWeatherReport(id,
                String.format("/weather-report/%d", id));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/weather-reports")
    public ResponseEntity<List<WeatherReportDtoOut>> listWeatherReports(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/weather-reports.
                """);
        return this.weatherReportService.listWeatherReports();
    }
}
