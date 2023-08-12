package fr.diginamic.aqiprojectbackend.service.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.AirQualityReportDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.AirQualityReportDtoOut;
import fr.diginamic.aqiprojectbackend.entity.map.AirQualityReport;
import fr.diginamic.aqiprojectbackend.entity.map.AirQualityStation;
import fr.diginamic.aqiprojectbackend.entity.map.ReportDate;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.map.AirQualityReportRepository;
import fr.diginamic.aqiprojectbackend.repository.map.AirQualityStationRepository;
import fr.diginamic.aqiprojectbackend.repository.map.ReportDateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.util.Dtos.buildHttpStatusResponse;

/** Air quality report service */
@Service
@Validated
public class AirQualityReportService {
    /** Air quality report repository */
    private final AirQualityReportRepository airQualityReportRepository;
    /** Air quality station repository */
    private final AirQualityStationRepository airQualityStationRepository;
    /** Report date repository */
    private final ReportDateRepository reportDateRepository;

    /**
     * Constructor with parameters.
     * @param airQualityReportRepository Air quality report repository
     * @param airQualityStationRepository Air quality station repository
     * @param reportDateRepository Report date repository
     */
    public AirQualityReportService(AirQualityReportRepository airQualityReportRepository,
                                   AirQualityStationRepository airQualityStationRepository,
                                   ReportDateRepository reportDateRepository) {
        this.airQualityReportRepository = airQualityReportRepository;
        this.airQualityStationRepository = airQualityStationRepository;
        this.reportDateRepository = reportDateRepository;
    }

    /**
     * Create air quality report
     * @param body HTTP request body (air quality report)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createAirQualityReport(AirQualityReportDtoIn body, String path){
        HttpStatus httpStatus;
        try {
            airQualityReportRepository.save(buildAirQualityReportFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return buildHttpStatusResponse(httpStatus, path);
    }

    /**
     * Read air quality report
     * @param id Air quality report identifier
     * @return HTTP response (air quality report)
     */
    public ResponseEntity<AirQualityReportDtoOut> readAirQualityReport(int id){
        final AirQualityReport airQualityReport =
                airQualityReportRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final AirQualityReportDtoOut airQualityReportDtoOut =
                buildAirQualityReportDtoOutFrom(airQualityReport);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(airQualityReportDtoOut);
    }

    /**
     * Update air quality report
     * @param id Air quality report identifier
     * @param body HTTP request body (air quality report)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateAirQualityReport(int id, AirQualityReportDtoIn body, String path){
        final AirQualityReport airQualityReport = airQualityReportRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        airQualityReport.setAqi(body.aqi());
        airQualityReport.setPm25(body.pm25());
        airQualityReport.setPm10(body.pm10());
        airQualityReport.setO3(body.o3());
        airQualityReport.setNo2(body.no2());
        final AirQualityStation airQualityStation = airQualityStationRepository
                .findById(body.airQualityStationId())
                .orElseThrow(EntityNotFoundException::new);
        airQualityReport.setAirQualityStation(airQualityStation);
        final ReportDate reportDate = reportDateRepository
                .findById(body.reportDateId())
                .orElseThrow(EntityNotFoundException::new);
        airQualityReport.setReportDate(reportDate);
        airQualityReportRepository.save(airQualityReport);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * Delete air quality report
     * @param id Air quality report identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    deleteAirQualityReport(int id, String path){
        final AirQualityReport airQualityReport = airQualityReportRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        airQualityReportRepository.delete(airQualityReport);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * List air quality reports
     * @return HTTP response (air quality reports)
     */
    public ResponseEntity<List<AirQualityReportDtoOut>> listAirQualityReports(){
        final List<AirQualityReport> airQualityReports =
                airQualityReportRepository.findAll();
        final List<AirQualityReportDtoOut> airQualityReportDtoOutList = airQualityReports
                .stream()
                .map(this::buildAirQualityReportDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(airQualityReportDtoOutList);
    }

    /**
     * Build air quality report from HTTP request body (air quality report)
     * @param body HTTP request body (air quality report)
     * @return Air quality report
     */
    private AirQualityReport buildAirQualityReportFrom(AirQualityReportDtoIn body) {
        final AirQualityStation airQualityStation = airQualityStationRepository
                .findById(body.airQualityStationId())
                .orElseThrow(EntityNotFoundException::new);
        final ReportDate reportDate = reportDateRepository
                .findById(body.airQualityStationId())
                .orElseThrow(EntityNotFoundException::new);
        return new AirQualityReport(body.aqi(),
                body.pm25(),
                body.pm10(),
                body.o3(),
                body.no2(),
                airQualityStation,
                reportDate);
    }

    /**
     * Build air quality report as HTTP response from air quality report
     * @param airQualityReport Air quality report
     * @return HTTP response (air quality report)
     */
    private AirQualityReportDtoOut
    buildAirQualityReportDtoOutFrom(AirQualityReport airQualityReport){
        return new AirQualityReportDtoOut(airQualityReport.getId(),
                airQualityReport.getAqi(),
                airQualityReport.getPm25(),
                airQualityReport.getPm10(),
                airQualityReport.getO3(),
                airQualityReport.getNo2(),
                airQualityReport.getAirQualityStation().getId(),
                airQualityReport.getReportDate().getId());
    }
}
