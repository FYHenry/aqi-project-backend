package fr.diginamic.aqiprojectbackend.controller.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.PopulationDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.PopulationDtoOut;
import fr.diginamic.aqiprojectbackend.service.map.PopulationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Population controller */
@RestController
public class PopulationController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(PopulationController.class);
    /** Population service */
    private final PopulationService populationService;

    /**
     * Constructor with parameters.
     * @param populationService Population service
     */
    public PopulationController(PopulationService populationService) {
        this.populationService = populationService;
    }
    /* Créateur POST */
    /** POST creator */
    @Secured("ADMIN")
    @PostMapping(path = "/population")
    public ResponseEntity<HttpStatusDtoOut>
    createPopulation(@RequestBody PopulationDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/population.
                Body :
                 {}
                """,
                body);
        return this.populationService.createPopulation(body, "/population");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/population/{id}")
    public ResponseEntity<PopulationDtoOut>
    readPopulation(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/population/{}.
                """,
                id);
        return this.populationService.readPopulation(id);
    }

    /* Actualiseur PUT */
    /** PUT updater */
    @Secured("ADMIN")
    @PutMapping(path = "/population/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updatePopulation(@PathVariable int id,
                      @RequestBody PopulationDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/population/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.populationService.updatePopulation(id,
                body,
                String.format("/population/%d", id));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @Secured("ADMIN")
    @DeleteMapping(path = "/population/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deletePopulation(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/population/{}.
                """,
                id);
        return this.populationService.deletePopulation(id,
                String.format("/population/%d", id));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/populations")
    public ResponseEntity<List<PopulationDtoOut>> listPopulations(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/populations.
                """);
        return this.populationService.listPopulations();
    }
}
