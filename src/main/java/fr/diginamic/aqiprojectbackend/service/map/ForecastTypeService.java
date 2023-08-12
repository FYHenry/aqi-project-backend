package fr.diginamic.aqiprojectbackend.service.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.ForecastTypeDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.ForecastTypeDtoOut;
import fr.diginamic.aqiprojectbackend.entity.map.ForecastType;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.map.ForecastTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.utils.Dtos.buildHttpStatusResponse;

/** Forecast type service */
@Service
@Validated
public class ForecastTypeService {
    /** Forecast type repository */
    private final ForecastTypeRepository forecastTypeRepository;

    /**
     * Constructor with parameters.
     * @param forecastTypeRepository Forecast type repository
     */
    public ForecastTypeService(ForecastTypeRepository forecastTypeRepository) {
        this.forecastTypeRepository = forecastTypeRepository;
    }

    /**
     * Create forecast type
     * @param body HTTP request body (forecast type)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createForecastType(ForecastTypeDtoIn body, String path){
        HttpStatus httpStatus;
        try {
            forecastTypeRepository.save(buildForecastTypeFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return buildHttpStatusResponse(httpStatus, path);
    }

    /**
     * Read forecast type
     * @param id Forecast type identifier
     * @return HTTP response (forecast type)
     */
    public ResponseEntity<ForecastTypeDtoOut> readForecastType(int id){
        final ForecastType forecastType =
                forecastTypeRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final ForecastTypeDtoOut forecastTypeDtoOut =
                buildForecastTypeDtoOutFrom(forecastType);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(forecastTypeDtoOut);
    }

    /**
     * Update forecast type
     * @param id Forecast type identifier
     * @param body HTTP request body (forecast type)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateForecastType(int id, ForecastTypeDtoIn body, String path){
        final ForecastType forecastType = forecastTypeRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        forecastType.setLabel(body.label());
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * Delete forecast type
     * @param id Forecast type identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    deleteForecastType(int id, String path){
        final ForecastType forecastType = forecastTypeRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        forecastTypeRepository.delete(forecastType);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * List forecast types
     * @return HTTP response (forecast types)
     */
    public ResponseEntity<List<ForecastTypeDtoOut>> listForecastTypes(){
        final List<ForecastType> forecastTypes =
                forecastTypeRepository.findAll();
        final List<ForecastTypeDtoOut> forecastTypeDtoOutList = forecastTypes
                .stream()
                .map(this::buildForecastTypeDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(forecastTypeDtoOutList);
    }
    
    /**
     * Build forecast type from HTTP request body (forecast type)
     * @param body HTTP request body (forecast type)
     * @return Forecast type
     */
    private ForecastType buildForecastTypeFrom(ForecastTypeDtoIn body) {
        return new ForecastType(body.label());
    }

    /**
     * Build forecast type as HTTP response from forecast type
     * @param forecastType Forecast type
     * @return HTTP response (forecast type)
     */
    private ForecastTypeDtoOut
    buildForecastTypeDtoOutFrom(ForecastType forecastType){
        return new ForecastTypeDtoOut(forecastType.getId(),
                forecastType.getLabel());
    }
}
