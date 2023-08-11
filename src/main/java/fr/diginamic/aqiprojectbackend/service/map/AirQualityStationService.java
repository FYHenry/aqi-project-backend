package fr.diginamic.aqiprojectbackend.service.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.AirQualityStationDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.AirQualityStationDtoOut;
import fr.diginamic.aqiprojectbackend.entity.map.City;
import fr.diginamic.aqiprojectbackend.entity.map.AirQualityStation;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.map.CityRepository;
import fr.diginamic.aqiprojectbackend.repository.map.AirQualityStationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.utils.Dtos.buildHttpStatusResponse;

/** Air quality station service */
@Service
@Validated
public class AirQualityStationService {
    /** Air quality station repository */
    private final AirQualityStationRepository airQualityStationRepository;
    /** City repository */
    private final CityRepository cityRepository;

    /**
     * Constructor with parameters.
     * @param airQualityStationRepository Air quality station repository
     * @param cityRepository City repository
     */
    public AirQualityStationService(AirQualityStationRepository airQualityStationRepository,
                                 CityRepository cityRepository) {
        this.airQualityStationRepository = airQualityStationRepository;
        this.cityRepository = cityRepository;
    }

    /**
     * Create air quality station
     * @param body HTTP request body (air quality station)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createAirQualityStation(AirQualityStationDtoIn body, String path){
        HttpStatus httpStatus;
        try {
            airQualityStationRepository.save(buildAirQualityStationFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return buildHttpStatusResponse(httpStatus, path);
    }

    /**
     * Read air quality station
     * @param id Air quality station identifier
     * @return HTTP response (air quality station)
     */
    public ResponseEntity<AirQualityStationDtoOut> readAirQualityStation(int id){
        final AirQualityStation airQualityStation =
                airQualityStationRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final AirQualityStationDtoOut airQualityStationDtoOut =
                buildAirQualityStationDtoOutFrom(airQualityStation);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(airQualityStationDtoOut);
    }

    /**
     * Update air quality station
     * @param id Air quality station identifier
     * @param body HTTP request body (air quality station)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateAirQualityStation(int id, AirQualityStationDtoIn body, String path){
        final AirQualityStation airQualityStation = airQualityStationRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        airQualityStation.setLatitude(body.latitude());
        airQualityStation.setLongitude(body.longitude());
        final City city = cityRepository
                .findById(body.cityId())
                .orElseThrow(EntityNotFoundException::new);
        airQualityStation.setCity(city);
        airQualityStationRepository.save(airQualityStation);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * Delete air quality station
     * @param id Air quality station identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    deleteAirQualityStation(int id, String path){
        final AirQualityStation airQualityStation = airQualityStationRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        airQualityStationRepository.delete(airQualityStation);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * List air quality stations
     * @return HTTP response (air quality stations)
     */
    public ResponseEntity<List<AirQualityStationDtoOut>> listAirQualityStations(){
        final List<AirQualityStation> airQualityStations =
                airQualityStationRepository.findAll();
        final List<AirQualityStationDtoOut> airQualityStationDtoOutList = airQualityStations
                .stream()
                .map(this::buildAirQualityStationDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(airQualityStationDtoOutList);
    }

    /**
     * Build air quality station from HTTP request body (air quality station)
     * @param body HTTP request body (air quality station)
     * @return Air quality station
     */
    private AirQualityStation buildAirQualityStationFrom(AirQualityStationDtoIn body) {
        final City city = cityRepository
                .findById(body.cityId())
                .orElseThrow(EntityNotFoundException::new);
        return new AirQualityStation(body.latitude(), body.longitude(), city);
    }

    /**
     * Build air quality station as HTTP response from air quality station
     * @param airQualityStation Air quality station
     * @return HTTP response (air quality station)
     */
    private AirQualityStationDtoOut
    buildAirQualityStationDtoOutFrom(AirQualityStation airQualityStation){
        return new AirQualityStationDtoOut(airQualityStation.getId(),
                airQualityStation.getLatitude(),
                airQualityStation.getLongitude(),
                airQualityStation.getCity().getInsee());
    }
}
