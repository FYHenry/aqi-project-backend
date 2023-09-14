package fr.diginamic.aqiprojectbackend.service.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.account.out.CityForm;
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

import java.util.ArrayList;
import java.util.List;

import static fr.diginamic.aqiprojectbackend.util.Dtos.buildHttpStatusResponse;

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
        return buildHttpStatusResponse(httpStatus, path);
    }

    /**
     * Read city
     * @param insee City identifier
     * @return HTTP response (city)
     */
    public ResponseEntity<CityDtoOut> readCity(String insee){
        final City city = cityRepository
                .findCityByInsee(insee)
                .orElseThrow(EntityNotFoundException::new);
        final CityDtoOut cityDtoOut = buildCityDtoOutFrom(city);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cityDtoOut);
    }

    /**
     * Update city
     * @param insee City identifier
     * @param body HTTP request body (city)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateCity(String insee, CityDtoIn body, String path){
        final City city = cityRepository
                .findCityByInsee(insee)
                .orElseThrow(EntityNotFoundException::new);
        city.setName(body.name());
        city.setPostcodes(body.postcodes());
        city.setLatitude(body.latitude());
        city.setLongitude(body.longitude());
        final Department department = departmentRepository
                .findById(body.departmentId())
                .orElseThrow(EntityNotFoundException::new);
        city.setDepartement(department);
        cityRepository.save(city);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * Delete city
     * @param insee City identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut> deleteCity(String insee, String path){
        final City city = cityRepository
                .findCityByInsee(insee)
                .orElseThrow(EntityNotFoundException::new);
        cityRepository.delete(city);
        return buildHttpStatusResponse(HttpStatus.OK, path);
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
    public ResponseEntity<List<CityForm>> cityForm(String name) {
        final List<City> cities =
                cityRepository
                        .findByNameContaining(name)
                        .orElseThrow(EntityNotFoundException::new);
        final List<CityForm> citiesForm =
                buildCityFormFrom(cities);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(citiesForm);
    }

    private List<CityForm> buildCityFormFrom(List<City> cities) {
        List<CityForm> citiesForm = new ArrayList<>();
        for (City city : cities) {
            for (int postcode : city.getPostcodes()){
                citiesForm.add(new CityForm(city.getInsee(), city.getName(), postcode));
            }
        }
        return citiesForm;
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
                body.postcodes(),
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
                city.getPostcodes(),
                city.getLatitude(),
                city.getLongitude(),
                city.getDepartement().getInsee());
    }
}
