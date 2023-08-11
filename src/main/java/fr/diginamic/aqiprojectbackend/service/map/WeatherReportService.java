package fr.diginamic.aqiprojectbackend.service.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.WeatherReportDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.WeatherReportDtoOut;
import fr.diginamic.aqiprojectbackend.entity.map.ReportDate;
import fr.diginamic.aqiprojectbackend.entity.map.WeatherReport;
import fr.diginamic.aqiprojectbackend.entity.map.WeatherStation;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.map.ReportDateRepository;
import fr.diginamic.aqiprojectbackend.repository.map.WeatherReportRepository;
import fr.diginamic.aqiprojectbackend.repository.map.WeatherStationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.utils.Dtos.buildHttpStatusResponse;

/** Weather report service */
@Service
@Validated
public class WeatherReportService {
    /** Weather report repository */
    private final WeatherReportRepository weatherReportRepository;
    /** Weather station repository */
    private final WeatherStationRepository weatherStationRepository;
    /** Report date repository */
    private final ReportDateRepository reportDateRepository;

    /**
     * Constructor with parameters.
     * @param weatherReportRepository Weather report repository
     * @param weatherStationRepository Weather station repository
     * @param reportDateRepository Report date repository
     */
    public WeatherReportService(WeatherReportRepository weatherReportRepository,
                                WeatherStationRepository weatherStationRepository,
                                ReportDateRepository reportDateRepository) {
        this.weatherReportRepository = weatherReportRepository;
        this.weatherStationRepository = weatherStationRepository;
        this.reportDateRepository = reportDateRepository;
    }

    /**
     * Create weather report
     * @param body HTTP request body (weather report)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createWeatherReport(WeatherReportDtoIn body, String path){
        HttpStatus httpStatus;
        try {
            weatherReportRepository.save(buildWeatherReportFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return buildHttpStatusResponse(httpStatus, path);
    }

    /**
     * Read weather report
     * @param id Weather report identifier
     * @return HTTP response (weather report)
     */
    public ResponseEntity<WeatherReportDtoOut> readWeatherReport(int id){
        final WeatherReport weatherReport =
                weatherReportRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final WeatherReportDtoOut weatherReportDtoOut =
                buildWeatherReportDtoOutFrom(weatherReport);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(weatherReportDtoOut);
    }

    /**
     * Update weather report
     * @param id Weather report identifier
     * @param body HTTP request body (weather report)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateWeatherReport(int id, WeatherReportDtoIn body, String path) {
        final WeatherReport weatherReport = weatherReportRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        weatherReport.setWeatherCode(body.weatherCode());
        weatherReport.setTemperature(body.temperature());
        weatherReport.setHumidity(body.humidity());
        weatherReport.setPressure(body.pressure());
        final WeatherStation weatherStation = weatherStationRepository
                .findById(body.weatherStationId())
                .orElseThrow(EntityNotFoundException::new);
        weatherReport.setWeatherStation(weatherStation);
        final ReportDate reportDate = reportDateRepository
                .findById(body.reportDateId())
                .orElseThrow(EntityNotFoundException::new);
        weatherReport.setReportDate(reportDate);
        weatherReportRepository.save(weatherReport);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * Delete weather report
     * @param id Weather report identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    deleteWeatherReport(int id, String path){
        final WeatherReport weatherReport = weatherReportRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        weatherReportRepository.delete(weatherReport);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * List weather reports
     * @return HTTP response (weather reports)
     */
    public ResponseEntity<List<WeatherReportDtoOut>> listWeatherReports(){
        final List<WeatherReport> weatherReports =
                weatherReportRepository.findAll();
        final List<WeatherReportDtoOut> weatherReportDtoOutList = weatherReports
                .stream()
                .map(this::buildWeatherReportDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(weatherReportDtoOutList);
    }

    /**
     * Build weather report from HTTP request body (weather report)
     * @param body HTTP request body (weather report)
     * @return Weather report
     */
    private WeatherReport buildWeatherReportFrom(WeatherReportDtoIn body) {
        final WeatherStation weatherStation = weatherStationRepository
                .findById(body.weatherStationId())
                .orElseThrow(EntityNotFoundException::new);
        final ReportDate reportDate = reportDateRepository
                .findById(body.reportDateId())
                .orElseThrow(EntityNotFoundException::new);
        return new WeatherReport(body.weatherCode(),
                body.temperature(),
                body.humidity(),
                body.pressure(),
                weatherStation,
                reportDate);
    }

    /**
     * Build weather report as HTTP response from weather report
     * @param weatherReport Weather report
     * @return HTTP response (weather report)
     */
    private WeatherReportDtoOut
    buildWeatherReportDtoOutFrom(WeatherReport weatherReport){
        return new WeatherReportDtoOut(weatherReport.getId(),
                weatherReport.getWeatherCode(),
                weatherReport.getTemperature(),
                weatherReport.getHumidity(),
                weatherReport.getPressure(),
                weatherReport.getWeatherStation().getId(),
                weatherReport.getReportDate().getId());
    }
}