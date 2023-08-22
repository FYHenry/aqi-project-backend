package fr.diginamic.aqiprojectbackend.service.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.DepartmentDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.DepartmentDtoOut;
import fr.diginamic.aqiprojectbackend.entity.map.Department;
import fr.diginamic.aqiprojectbackend.entity.map.Region;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.map.DepartmentRepository;
import fr.diginamic.aqiprojectbackend.repository.map.RegionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.util.Dtos.buildHttpStatusResponse;

/** department service */
@Service
@Validated
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final RegionRepository regionRepository;

    public DepartmentService(DepartmentRepository departmentRepository,
                             RegionRepository regionRepository) {
        this.departmentRepository = departmentRepository;
        this.regionRepository = regionRepository;
    }
    /**
     * Create department
     * @param body HTTP request body (department)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createDepartment(DepartmentDtoIn body, String path){
        HttpStatus httpStatus;
        try {
            departmentRepository.save(buildDepartmentFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return buildHttpStatusResponse(httpStatus, path);
    }
    /**
     * Read department
     * @param id department identifier
     * @return HTTP response (department)
     */
    public ResponseEntity<DepartmentDtoOut> readDepartment(int id){
        final Department department =
                departmentRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final DepartmentDtoOut departmentDtoOut =
                buildDepartmentDtoOutFrom(department);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(departmentDtoOut);
    }

    /**
     * Update department
     * @param id Department identifier
     * @param body HTTP request body (department)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateDepartment(int id, DepartmentDtoIn body, String path){
        final Department department = departmentRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        department.setName(body.name());
        final Region region = regionRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        department.setRegion(region);
        departmentRepository.save(department);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * Delete department
     * @param id Department identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut> deleteDepartment(int id, String path){
        final Department department = departmentRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        departmentRepository.delete(department);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * List departments
     * @return HTTP response (departments)
     */
    public ResponseEntity<List<DepartmentDtoOut>> listDepartments(){
        final List<Department> departments =
                departmentRepository.findAll();
        final List<DepartmentDtoOut> departmentDtoOutList = departments
                .stream()
                .map(this::buildDepartmentDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(departmentDtoOutList);
    }

    /**
     * Build department from HTTP request body (department)
     * @param body HTTP request body (department)
     * @return department
     */
    private Department buildDepartmentFrom(DepartmentDtoIn body) {
        final Region region = regionRepository
                .findById(body.regionId())
                .orElseThrow(EntityNotFoundException::new);
        return new Department(body.insee(), body.name(), region);
    }

    /**
     * Build department as HTTP response from department
     * @param department Department
     * @return HTTP response (department)
     */
    private DepartmentDtoOut
    buildDepartmentDtoOutFrom(Department department){
        return new DepartmentDtoOut(department.getInsee(),
                department.getName(),
                department.getRegion().getInsee());
    }
}
