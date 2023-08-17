package fr.diginamic.aqiprojectbackend.controller.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.ReportDateDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.ReportDateDtoOut;
import fr.diginamic.aqiprojectbackend.service.map.ReportDateService;
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

/** Report date controller */
@RestController
public class ReportDateController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(ReportDateController.class);
    /** Report date service */
    private final ReportDateService reportDateService;

    /**
     * Constructor with parameters.
     * @param reportDateService Report date service
     */
    public ReportDateController(ReportDateService reportDateService){
        this.reportDateService = reportDateService;
    }

    /* Créateur POST */
    /** POST creator */
    @PostMapping(path = "/report-date")
    public ResponseEntity<HttpStatusDtoOut>
    createReportDate(@RequestBody ReportDateDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/report-date.
                Body :
                 {}
                """,
                body);
        return this.reportDateService.createReportDate(body, "/report-date");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/report-date/{id}")
    public ResponseEntity<ReportDateDtoOut>
    readReportDate(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/report-date/{}.
                """,
                id);
        return this.reportDateService.readReportDate(id);
    }

    /* Actualiseur PUT */
    /** PUT updater */
    @PutMapping(path = "/report-date/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateReportDate(@PathVariable int id,
                      @RequestBody ReportDateDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/report-date/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.reportDateService.updateReportDate(id,
                body,
                String.format("/report-date/%d", id));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @DeleteMapping(path = "/report-date/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deleteReportDate(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/report-date/{}.
                """,
                id);
        return this.reportDateService.deleteReportDate(id,
                String.format("/report-date/%d", id));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/report-dates")
    public ResponseEntity<List<ReportDateDtoOut>> listReportDates(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/report-dates.
                """);
        return this.reportDateService.listReportDates();
    }
}
