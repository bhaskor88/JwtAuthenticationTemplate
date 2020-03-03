package com.bohniman.cid.appapi.exception;

import java.util.Date;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import com.bohniman.cid.appapi.Utils.BindingResultToMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;

/**
 * CustomExceptionHandler
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // This is helping to get global exception. All controller comes here for
                  // exception advice
// @RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    public CustomExceptionHandler() {
        super();
    }

    @ExceptionHandler({ CommonException.class })
    public ResponseEntity<Object> handleCommonException(final RuntimeException ex, final WebRequest request) {
        log.info("Common Exception");
        CustomException customException = new CustomException(ex.getMessage(), HttpStatus.BAD_REQUEST, new Date());
        return handleExceptionInternal(ex, customException, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
            final HttpStatus status, final WebRequest request) {
        log.info("Bind Exception");
        CustomValidationException validationException = new CustomValidationException(
                BindingResultToMap.convertBindingResultToMap(ex.getBindingResult()), HttpStatus.BAD_REQUEST,
                new Date());
        return handleExceptionInternal(ex, validationException, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ FileStorageException.class })
    public ResponseEntity<Object> handleFileStorageException(final RuntimeException ex, final WebRequest request) {
        log.info(ex.getMessage());
        CustomException customException = new CustomException("Error Saving File(s)", HttpStatus.BAD_REQUEST,
                new Date());
        return handleExceptionInternal(ex, customException, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    // 403
    @ExceptionHandler({ ForbiddenException.class })
    public ResponseEntity<Object> handleForbiddenRequest(final ForbiddenException ex, final WebRequest request) {
        log.info("Forbidden Exception");
        CustomException customException = new CustomException("Forbidden", HttpStatus.FORBIDDEN, new Date());
        return handleExceptionInternal(ex, customException, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    // API
    // 400

    @ExceptionHandler({ BadRequestException.class })
    public ResponseEntity<Object> handleBadRequest(final BadRequestException ex, final WebRequest request) {
        log.info("Bad Request Exception");
        CustomException customException = new CustomException("Bad Request", HttpStatus.BAD_REQUEST, new Date());
        return handleExceptionInternal(ex, customException, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
        log.info("Constraint Violation Exception");
        CustomException customException = new CustomException("Bad Request", HttpStatus.BAD_REQUEST, new Date());
        return handleExceptionInternal(ex, customException, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
        log.info("Data Integrity Violation Exception");
        CustomException customException = new CustomException("Bad Request", HttpStatus.BAD_REQUEST, new Date());
        return handleExceptionInternal(ex, customException, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
            final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        log.info("Http Message Not Readable Exception");
        // ex.getCause() instanceof JsonMappingException, JsonParseException // for
        // additional information later on
        CustomException customException = new CustomException("Bad Request", HttpStatus.BAD_REQUEST, new Date());
        return handleExceptionInternal(ex, customException, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
            final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        log.info("Method Argument Not Valid Exception");
        CustomValidationException validationException = new CustomValidationException(
                BindingResultToMap.convertBindingResultToMap(ex.getBindingResult()), HttpStatus.BAD_REQUEST,
                new Date());
        return handleExceptionInternal(ex, validationException, headers, HttpStatus.BAD_REQUEST, request);
    }

    // 404
    @ExceptionHandler(value = { EntityNotFoundException.class, MyResourceNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        log.info("My Resource Not Found Exception");
        CustomException customException = new CustomException("Not Found", HttpStatus.NOT_FOUND, new Date());
        return handleExceptionInternal(ex, customException, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // 409
    @ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class })
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        log.info("Invalid Data Access Api Usage Exception");
        ex.printStackTrace();
        CustomException customException = new CustomException(
                "Something went wrong. Please contact system administrator.", HttpStatus.CONFLICT, new Date());
        return handleExceptionInternal(ex, customException, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    // 412
    // 500
    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        ex.printStackTrace();
        CustomException customException = new CustomException(
                "Something went wrong. Please contact system administrator.", HttpStatus.INTERNAL_SERVER_ERROR,
                new Date());
        return handleExceptionInternal(ex, customException, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

}