package fr.diginamic.aqiprojectbackend.utils;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
/** Data Transfer Objects utility */
final public class Dtos {
    /**
     * HTTP status response builder
     * @param httpStatus HTTP status
     * @param path Request path
     * @return HTTP status response
     */
    public static ResponseEntity<HttpStatusDtoOut>
    buildHttpStatusResponse(HttpStatus httpStatus, String path){
        final HttpStatusDtoOut output =
                new HttpStatusDtoOut(LocalDateTime.now(),
                        httpStatus.value(),
                        httpStatus.getReasonPhrase(),
                        path);
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(output);
    }
    /**
     * Constructor.
     */
    private Dtos() {}
}
