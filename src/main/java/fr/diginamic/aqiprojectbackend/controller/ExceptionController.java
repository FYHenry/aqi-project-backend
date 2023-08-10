package fr.diginamic.aqiprojectbackend.controller;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.exception.BadRequestException;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static fr.diginamic.aqiprojectbackend.utils.Dtos.buildHttpStatusResponse;
/** Exception Controller */
@RestControllerAdvice
public class ExceptionController {
    /** Logger */
    private static final Logger logger =
            LoggerFactory.getLogger(ExceptionController.class);

    /** Bad request handler */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<HttpStatusDtoOut>
    badRequest(HttpServletRequest request){
        final String path = request.getRequestURI();
        logger.info("Exception returned : {}",
                BadRequestException.class);
        return buildHttpStatusResponse(HttpStatus.BAD_REQUEST, path);
    }

    /** Entity not found handler */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<HttpStatusDtoOut>
    entityNotFound(HttpServletRequest request){
        final String path = request.getRequestURI();
        logger.info("Exception returned : {}",
                EntityNotFoundException.class);
        return buildHttpStatusResponse(HttpStatus.NOT_FOUND, path);
    }

    /** Unattended exceptions handler */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpStatusDtoOut>
    unattended(HttpServletRequest request){
        final String path = request.getRequestURI();
        logger.info("Exception returned : {}",
                EntityNotFoundException.class);
        return buildHttpStatusResponse(HttpStatus.INTERNAL_SERVER_ERROR, path);
    }
}
