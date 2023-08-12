package fr.diginamic.aqiprojectbackend.service.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.ReportDateDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.ReportDateDtoOut;
import fr.diginamic.aqiprojectbackend.entity.map.ReportDate;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.map.ReportDateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.utils.Dtos.buildHttpStatusResponse;

/** Report date service */
@Service
@Validated
public class ReportDateService {
    /** Report date repository */
    private final ReportDateRepository reportDateRepository;

    /**
     * Constructor with parameters.
     * @param reportDateRepository Report date repository
     */
    public ReportDateService(ReportDateRepository reportDateRepository) {
        this.reportDateRepository = reportDateRepository;
    }

    /**
     * Create report date
     * @param body HTTP request body (report date)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createReportDate(ReportDateDtoIn body, String path){
        HttpStatus httpStatus;
        try {
            reportDateRepository.save(buildReportDateFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return buildHttpStatusResponse(httpStatus, path);
    }

    /**
     * Read report date
     * @param id Report date identifier
     * @return HTTP response (report date)
     */
    public ResponseEntity<ReportDateDtoOut> readReportDate(int id){
        final ReportDate reportDate =
                reportDateRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final ReportDateDtoOut reportDateDtoOut =
                buildReportDateDtoOutFrom(reportDate);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(reportDateDtoOut);
    }

    /**
     * Update report date
     * @param id Report date identifier
     * @param body HTTP request body (report date)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateReportDate(int id, ReportDateDtoIn body, String path){
        final ReportDate reportDate = reportDateRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        reportDate.setDate(body.date());
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * Delete report date
     * @param id Report date identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    deleteReportDate(int id, String path){
        final ReportDate reportDate = reportDateRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        reportDateRepository.delete(reportDate);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * List report dates
     * @return HTTP response (report dates)
     */
    public ResponseEntity<List<ReportDateDtoOut>> listReportDates(){
        final List<ReportDate> reportDates =
                reportDateRepository.findAll();
        final List<ReportDateDtoOut> reportDateDtoOutList = reportDates
                .stream()
                .map(this::buildReportDateDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(reportDateDtoOutList);
    }

    /**
     * Build report date from HTTP request body (report date)
     * @param body HTTP request body (report date)
     * @return Report date
     */
    private ReportDate buildReportDateFrom(ReportDateDtoIn body) {
        return new ReportDate(body.date());
    }

    /**
     * Build report date as HTTP response from report date
     * @param reportDate ReportDate
     * @return HTTP response (report date)
     */
    private ReportDateDtoOut
    buildReportDateDtoOutFrom(ReportDate reportDate){
        return new ReportDateDtoOut(reportDate.getId(), reportDate.getDate());
    }
}
