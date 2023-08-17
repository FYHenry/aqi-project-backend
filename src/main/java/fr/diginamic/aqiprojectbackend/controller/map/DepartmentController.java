package fr.diginamic.aqiprojectbackend.controller.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.DepartmentDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.DepartmentDtoOut;
import fr.diginamic.aqiprojectbackend.service.map.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Department controller */
@RestController
public class DepartmentController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(DepartmentController.class);
    /** Department service */
    private final DepartmentService departmentService;

    /**
     * Contructor with parameters.
     * @param departmentService Department service
     */
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    /* Créateur POST */
    /** POST creator */
    @PostMapping(path = "/department")
    public ResponseEntity<HttpStatusDtoOut>
    createDepartment(@RequestBody DepartmentDtoIn body) {
        logger.info("""
                POST creator called by : http://127.0.0.1:8080/department.
                Body :
                 {}
                """,
                body);
        return this.departmentService.createDepartment(body, "/department");
    }

    /* Lecteur GET */
    /** GET reader */
    @GetMapping(path = "/department/{id}")
    public ResponseEntity<DepartmentDtoOut>
    readDepartment(@PathVariable int id) {
        logger.info("""
                GET reader called by : http://127.0.0.1:8080/department/{}.
                """,
                id);
        return this.departmentService.readDepartment(id);
    }

    /* Actualiseur PUT */
    /** PUT updater */
    @PutMapping(path = "/department/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    updateDepartment(@PathVariable int id,
                      @RequestBody DepartmentDtoIn body) {
        logger.info("""
                Put updater called by : http://127.0.0.1:8080/department/{}.
                Body :
                 {}
                """,
                id,
                body);
        return this.departmentService.updateDepartment(id,
                body,
                String.format("/department/%d", id));
    }

    /* Suppresseur DELETE */
    /** DELETE deleter */
    @DeleteMapping(path = "/department/{id}")
    public ResponseEntity<HttpStatusDtoOut>
    deleteDepartment(@PathVariable int id) {
        logger.info("""
                DELETE deleter called by : http://127.0.0.1:8080/department/{}.
                """,
                id);
        return this.departmentService.deleteDepartment(id,
                String.format("/department/%d", id));
    }

    /* Listeur GET */
    /** GET lister */
    @GetMapping(path = "/departments")
    public ResponseEntity<List<DepartmentDtoOut>> listDepartments(){
        logger.info("""
                GET lister called by : http://127.0.0.1:8080/departments.
                """);
        return this.departmentService.listDepartments();
    }
}
