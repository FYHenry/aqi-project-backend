package fr.diginamic.aqiprojectbackend.controller;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.exception.BadRequestException;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static fr.diginamic.aqiprojectbackend.utils.Dtos.buildHttpStatusDtoOut;

@RestControllerAdvice
public class ExceptionController {
    private static final Logger logger =
            LoggerFactory.getLogger(ExceptionController.class);
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<HttpStatusDtoOut>
    badRequest(HttpServletRequest request){
        final String path = request.getRequestURI();
        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        logger.info("Exception returned : {}",
                BadRequestException.class);
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buildHttpStatusDtoOut(httpStatus, path));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<HttpStatusDtoOut>
    entityNotFound(HttpServletRequest request){
        final String path = request.getRequestURI();
        final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        logger.info("Exception returned : {}",
                EntityNotFoundException.class);
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buildHttpStatusDtoOut(httpStatus, path));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpStatusDtoOut>
    unattended(HttpServletRequest request){
        final String path = request.getRequestURI();
        final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        logger.info("Exception returned : {}",
                EntityNotFoundException.class);
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buildHttpStatusDtoOut(httpStatus, path));
    }
}
