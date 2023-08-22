package fr.diginamic.aqiprojectbackend.controller.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.RegionDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.RegionDtoOut;
import fr.diginamic.aqiprojectbackend.service.map.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/** Region controller */
@RestController
public class RegionController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(RegionController.class);
    /** Region service */
    private final RegionService regionService;

    /**
     * Constructor with parameters.
     * @param regionService Region service
     */
    public RegionController(RegionService regionService){
        this.regionService = regionService;
    }

    /* Créateur POST */
    /** POST creator */
    @Secured("ADMIN")
    @PostMapping(path = "/region")
    public ResponseEntity<HttpStatusDtoOut>
    createRegion(@RequestBody RegionDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/region.
                Body :
                 {}
                """,
                body);
        return this.regionService.createRegion(body, "/region");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/region/{id}")
    public ResponseEntity<RegionDtoOut>
    readRegion(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/region/{}.
                """,
                id);
        return this.regionService.readRegion(id);
    }

    /* Actualiseur PUT */
    /** PUT updater */
    @Secured("ADMIN")
    @PutMapping(path = "/region/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateRegion(@PathVariable int id,
                      @RequestBody RegionDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/region/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.regionService.updateRegion(id,
                body,
                String.format("/region/%d", id));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @Secured("ADMIN")
    @DeleteMapping(path = "/region/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deleteRegion(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/region/{}.
                """,
                id);
        return this.regionService.deleteRegion(id,
                String.format("/region/%d", id));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/regions")
    public ResponseEntity<List<RegionDtoOut>> listRegions(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/regions.
                """);
        return this.regionService.listRegions();
    }
}
