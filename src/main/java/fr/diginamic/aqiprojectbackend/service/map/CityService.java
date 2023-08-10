package fr.diginamic.aqiprojectbackend.service.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.CityDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.CityDtoOut;
import fr.diginamic.aqiprojectbackend.entity.map.City;
import fr.diginamic.aqiprojectbackend.entity.map.Department;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.map.CityRepository;
import fr.diginamic.aqiprojectbackend.repository.map.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.utils.Dtos.buildHttpStatusDtoOut;

/** City service */
@Service
@Validated
public class CityService {
    /** City repository */
    private final CityRepository cityRepository;
    /** Department repository */
    private final DepartmentRepository departmentRepository;

    /**
     * Constructor with parameters.
     * @param cityRepository City repository
     * @param departmentRepository Department repository
     */
    public CityService(CityRepository cityRepository,
                       DepartmentRepository departmentRepository) {
        this.cityRepository = cityRepository;
        this.departmentRepository = departmentRepository;
    }

    /**
     * Create city
     * @param body HTTP request body (city)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createCity(CityDtoIn body, String path){
        HttpStatus httpStatus;
        try {
            cityRepository.save(buildCityFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buildHttpStatusDtoOut(httpStatus, path));
    }

    /**
     * Read city
     * @param id City identifier
     * @return HTTP response (city)
     */
    public ResponseEntity<CityDtoOut> readCity(int id){
        final City city = cityRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        final CityDtoOut cityDtoOut = buildCityDtoOutFrom(city);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cityDtoOut);
    }

    /**
     * Update city
     * @param id City identifier
     * @param body HTTP request body (city)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateCity(int id, CityDtoIn body, String path){
        final City city = cityRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        city.setName(body.name());
        city.setPostcode(body.postcode());
        city.setLatitude(body.latitude());
        city.setLongitude(body.longitude());
        final Department department = departmentRepository
                .findById(body.departmentId())
                .orElseThrow(EntityNotFoundException::new);
        city.setDepartement(department);
        cityRepository.save(city);
        final HttpStatus httpStatus = HttpStatus.OK;
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buildHttpStatusDtoOut(httpStatus, path));
    }

    /**
     * Delete city
     * @param id City identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut> deleteCity(int id, String path){
        final City city = cityRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        cityRepository.delete(city);
        final HttpStatus httpStatus = HttpStatus.OK;
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buildHttpStatusDtoOut(httpStatus, path));
    }

    /**
     * List cities
     * @return HTTP response (cities)
     */
    public ResponseEntity<List<CityDtoOut>> listCities(){
        final List<City> cities =
                cityRepository.findAll();
        final List<CityDtoOut> cityDtoOutList = cities
                .stream()
                .map(this::buildCityDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cityDtoOutList);
    }
    /**
     * Build city from HTTP request body (city)
     * @param body HTTP request body (city)
     * @return City
     */
    private City buildCityFrom(CityDtoIn body) {
        final Department department = departmentRepository
                .findById(body.departmentId())
                .orElseThrow(EntityNotFoundException::new);
        return new City(body.insee(),
                body.name(),
                body.postcode(),
                body.latitude(),
                body.longitude(),
                department);
    }

    /**
     * Build city as HTTP response from city
     * @param city City
     * @return HTTP response (city)
     */
    private CityDtoOut
    buildCityDtoOutFrom(City city){
        return new CityDtoOut(city.getInsee(),
                city.getName(),
                city.getPostcode(),
                city.getLatitude(),
                city.getLatitude(),
                city.getDepartement().getCode());
    }
}
