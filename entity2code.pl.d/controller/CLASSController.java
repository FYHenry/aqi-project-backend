package fr.diginamic.aqiprojectbackend.controller.SUBPKG;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.SUBPKG.in.CLASSDtoIn;
import fr.diginamic.aqiprojectbackend.dto.SUBPKG.out.CLASSDtoOut;
import fr.diginamic.aqiprojectbackend.service.SUBPKG.CLASSService;
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

/** UCCOMMENT controller */
@RestController
public class CLASSController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(CLASSController.class);
    /** UCCOMMENT service */
    private final CLASSService INSTANCEService;

    /**
     * Constructor with parameters.
     * @param INSTANCEService UCCOMMENT service
     */
    public CLASSController(CLASSService INSTANCEService){
        this.INSTANCEService = INSTANCEService;
    }

    /* Créateur POST */
    /** POST creator */
    @PostMapping(path = "/URLNAME")
    public ResponseEntity<HttpStatusDtoOut>
    createCLASS(@RequestBody CLASSDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/URLNAME.
                Body :
                 {}
                """,
                body);
        return this.INSTANCEService.createCLASS(body, "/URLNAME");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/URLNAME/{id}")
    public ResponseEntity<CLASSDtoOut>
    readCLASS(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/URLNAME/{}.
                """,
                id);
        return this.INSTANCEService.readCLASS(id);
    }

    /* Actualiseur PUT */
    /** PUT updater */
    @PutMapping(path = "/URLNAME/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateCLASS(@PathVariable int id,
                      @RequestBody CLASSDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/URLNAME/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.INSTANCEService.updateCLASS(id,
                body,
                String.format("/URLNAME/%d", id));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @DeleteMapping(path = "/URLNAME/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deleteCLASS(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/URLNAME/{}.
                """,
                id);
        return this.INSTANCEService.deleteCLASS(id,
                String.format("/URLNAME/%d", id));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/URLNAMEs")
    public ResponseEntity<List<CLASSDtoOut>> listCLASSs(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/URLNAMEs.
                """);
        return this.INSTANCEService.listCLASSs();
    }
}
