package fr.diginamic.aqiprojectbackend.utils;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
/** Data Transfer Objects utility */
final public class Dtos {
    /**
     * HTTP status DTO output builder
     * @param httpStatus HTTP status
     * @param path Request path
     * @return HTTP status DTO output
     */
    public static HttpStatusDtoOut buildHttpStatusDtoOut(HttpStatus httpStatus,
                                                         String path){
        return new HttpStatusDtoOut(LocalDateTime.now(),
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                path);
    }

    /**
     * Constructor.
     */
    private Dtos() {}
}
