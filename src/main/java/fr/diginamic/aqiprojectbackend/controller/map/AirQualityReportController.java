package fr.diginamic.aqiprojectbackend.controller.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.AirQualityReportDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.AirQualityReportDtoOut;
import fr.diginamic.aqiprojectbackend.service.map.AirQualityReportService;
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

/** Air quality report controller */
@RestController
public class AirQualityReportController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(AirQualityReportController.class);
    /** Air quality report service */
    private final AirQualityReportService airQualityReportService;

    /**
     * Constructor with parameters.
     * @param airQualityReportService Air quality report service
     */
    public AirQualityReportController(AirQualityReportService airQualityReportService){
        this.airQualityReportService = airQualityReportService;
    }

    /* Créateur POST */
    /** POST creator */
    @Secured("ADMIN")
    @PostMapping(path = "/air-quality-report")
    public ResponseEntity<HttpStatusDtoOut>
    createAirQualityReport(@RequestBody AirQualityReportDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/air-quality-report.
                Body :
                 {}
                """,
                body);
        return this.airQualityReportService.createAirQualityReport(body,
                "/air-quality-report");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/air-quality-report/{id}")
    public ResponseEntity<AirQualityReportDtoOut>
    readAirQualityReport(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/air-quality-report/{}.
                """,
                id);
        return this.airQualityReportService.readAirQualityReport(id);
    }

    /* Actualiseur PUT */
    /** PUT updater */
    @Secured("ADMIN")
    @PutMapping(path = "/air-quality-report/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateAirQualityReport(@PathVariable int id,
                      @RequestBody AirQualityReportDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/air-quality-report/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.airQualityReportService.updateAirQualityReport(id,
                body,
                String.format("/air-quality-report/%d", id));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @Secured("ADMIN")
    @DeleteMapping(path = "/air-quality-report/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deleteAirQualityReport(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/air-quality-report/{}.
                """,
                id);
        return this.airQualityReportService.deleteAirQualityReport(id,
                String.format("/air-quality-report/%d", id));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/air-quality-reports")
    public ResponseEntity<List<AirQualityReportDtoOut>> listAirQualityReports(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/air-quality-reports.
                """);
        return this.airQualityReportService.listAirQualityReports();
    }
}
