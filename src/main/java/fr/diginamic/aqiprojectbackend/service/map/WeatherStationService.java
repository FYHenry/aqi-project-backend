package fr.diginamic.aqiprojectbackend.service.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.WeatherStationDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.WeatherStationDtoOut;
import fr.diginamic.aqiprojectbackend.entity.map.City;
import fr.diginamic.aqiprojectbackend.entity.map.WeatherStation;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.map.CityRepository;
import fr.diginamic.aqiprojectbackend.repository.map.WeatherStationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.util.Dtos.buildHttpStatusResponse;

/** Weather station service */
@Service
@Validated
public class WeatherStationService {
    /** Weather station repository */
    private final WeatherStationRepository weatherStationRepository;
    /** City repository */
    private final CityRepository cityRepository;

    /**
     * Constructor with parameters.
     * @param weatherStationRepository Weather station repository
     * @param cityRepository City repository
     */
    public WeatherStationService(WeatherStationRepository weatherStationRepository,
                                 CityRepository cityRepository) {
        this.weatherStationRepository = weatherStationRepository;
        this.cityRepository = cityRepository;
    }

    /**
     * Create weather station
     * @param body HTTP request body (weather station)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createWeatherStation(WeatherStationDtoIn body, String path){
        HttpStatus httpStatus;
        try {
            weatherStationRepository.save(buildWeatherStationFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return buildHttpStatusResponse(httpStatus, path);
    }

    /**
     * Read weather station
     * @param id Weather station identifier
     * @return HTTP response (weather station)
     */
    public ResponseEntity<WeatherStationDtoOut> readWeatherStation(int id){
        final WeatherStation weatherStation =
                weatherStationRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final WeatherStationDtoOut weatherStationDtoOut =
                buildWeatherStationDtoOutFrom(weatherStation);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(weatherStationDtoOut);
    }

    /**
     * Update weather station
     * @param id Weather station identifier
     * @param body HTTP request body (weather station)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateWeatherStation(int id, WeatherStationDtoIn body, String path){
        final WeatherStation weatherStation = weatherStationRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        weatherStation.setLatitude(body.latitude());
        weatherStation.setLongitude(body.longitude());
        final City city = cityRepository
                .findById(body.cityId())
                .orElseThrow(EntityNotFoundException::new);
        weatherStation.setCity(city);
        weatherStationRepository.save(weatherStation);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * Delete weather station
     * @param id Weather station identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    deleteWeatherStation(int id, String path){
        final WeatherStation weatherStation = weatherStationRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        weatherStationRepository.delete(weatherStation);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * List weather stations
     * @return HTTP response (weather stations)
     */
    public ResponseEntity<List<WeatherStationDtoOut>> listWeatherStations(){
        final List<WeatherStation> weatherStations =
                weatherStationRepository.findAll();
        final List<WeatherStationDtoOut> weatherStationDtoOutList = weatherStations
                .stream()
                .map(this::buildWeatherStationDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(weatherStationDtoOutList);
    }
    
    /**
     * Build weather station from HTTP request body (weather station)
     * @param body HTTP request body (weather station)
     * @return Weather station
     */
    private WeatherStation buildWeatherStationFrom(WeatherStationDtoIn body) {
        final City city = cityRepository
                .findById(body.cityId())
                .orElseThrow(EntityNotFoundException::new);
        return new WeatherStation(body.latitude(), body.longitude(), city);
    }
    
    /**
     * Build weather station as HTTP response from weather station
     * @param weatherStation Weather station
     * @return HTTP response (weather station)
     */
    private WeatherStationDtoOut
    buildWeatherStationDtoOutFrom(WeatherStation weatherStation){
        return new WeatherStationDtoOut(weatherStation.getId(),
                weatherStation.getLatitude(),
                weatherStation.getLongitude(),
                weatherStation.getCity().getInsee());
    }
}
