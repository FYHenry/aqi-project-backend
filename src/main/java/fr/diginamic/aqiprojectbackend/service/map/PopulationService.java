package fr.diginamic.aqiprojectbackend.service.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.PopulationDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.PopulationDtoOut;
import fr.diginamic.aqiprojectbackend.entity.map.City;
import fr.diginamic.aqiprojectbackend.entity.map.Population;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.map.CityRepository;
import fr.diginamic.aqiprojectbackend.repository.map.PopulationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.utils.Dtos.buildHttpStatusResponse;

/** Population service */
@Service
@Validated
public class PopulationService {
    /** Population repository */
    private final PopulationRepository populationRepository;
    /** City repository */
    private final CityRepository cityRepository;

    /**
     * Constructor with parameters.
     * @param populationRepository Population repository
     * @param cityRepository City repository
     */
    public PopulationService(PopulationRepository populationRepository,
                             CityRepository cityRepository) {
        this.populationRepository = populationRepository;
        this.cityRepository = cityRepository;
    }
    
    /**
     * Create population
     * @param body HTTP request body (population)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createPopulation(PopulationDtoIn body, String path){
        HttpStatus httpStatus;
        try {
            populationRepository.save(buildPopulationFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return buildHttpStatusResponse(httpStatus, path);
    }

    /**
     * Read population
     * @param id Population identifier
     * @return HTTP response (population)
     */
    public ResponseEntity<PopulationDtoOut> readPopulation(int id){
        final Population population =
                populationRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final PopulationDtoOut populationDtoOut =
                buildPopulationDtoOutFrom(population);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(populationDtoOut);
    }

    /**
     * Update population
     * @param id population identifier
     * @param body HTTP request body (population)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updatePopulation(int id, PopulationDtoIn body, String path){
        final Population population = populationRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        population.setDate(body.date());
        population.setLabel(body.label());
        population.setPopulationNumber(body.populationNumber());
        final City city = cityRepository
                .findById(body.cityId())
                .orElseThrow(EntityNotFoundException::new);
        population.setCity(city);
        populationRepository.save(population);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * Delete population
     * @param id Population identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    deletePopulation(int id, String path){
        final Population population = populationRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        populationRepository.delete(population);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * List populations
     * @return HTTP response (populations)
     */
    public ResponseEntity<List<PopulationDtoOut>> listPopulations(){
        final List<Population> populations =
                populationRepository.findAll();
        final List<PopulationDtoOut> populationDtoOutList = populations
                .stream()
                .map(this::buildPopulationDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(populationDtoOutList);
    }

    /**
     * Build population from HTTP request body (population)
     * @param body HTTP request body (population)
     * @return Population
     */
    private Population buildPopulationFrom(PopulationDtoIn body) {
        final City city = cityRepository
                .findById(body.cityId())
                .orElseThrow(EntityNotFoundException::new);
        return new Population(body.date(),
                body.label(),
                body.populationNumber(),
                city);
    }

    /**
     * Build population as HTTP response from population
     * @param population Population
     * @return HTTP response (population)
     */
    private PopulationDtoOut
    buildPopulationDtoOutFrom(Population population){
        return new PopulationDtoOut(population.getId(),
                population.getDate(),
                population.getLabel(),
                population.getPopulationNumber(),
                population.getCity().getInsee());
    }
}
