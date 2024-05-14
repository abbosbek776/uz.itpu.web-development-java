package uk.com.bookcloud.exceptionHandler;


import uk.com.bookcloud.common.ResponseData;
import uk.com.bookcloud.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller Advice for handling exceptions.
 * This class handles various types of exceptions and sends a structured error response to the client.
 */
@RestControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(final Exception e) {
        return new ResponseEntity<>(new ResponseData<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(final RuntimeException e) {
        return new ResponseEntity<>(new ResponseData<>(e.getMessage(), HttpStatus.BAD_GATEWAY.value()), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(final CustomNotFoundException e) {
        return new ResponseEntity<>(new ResponseData<>(e.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InValidInputException.class)
    public ResponseEntity<Object> handleInValidInputException(final InValidInputException e) {
        return new ResponseEntity<>(new ResponseData<>(e.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handleAlreadyExistsException(final AlreadyExistsException e) {
        return new ResponseEntity<>(new ResponseData<>(e.getMessage(), HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ObjectError errorObject = ex.getBindingResult().getAllErrors().get(0);
        String message = errorObject.getDefaultMessage();

        Map<String, String> errors = new HashMap<>();

        errors.put("errorMessage", message);
        errors.put("timestamp", String.valueOf(System.currentTimeMillis()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(final UsernameNotFoundException e) {
        return new ResponseEntity<>(new ResponseData<>(e.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomForbiddenException.class)
    public ResponseEntity<Object> handleCustomForbiddenException(final CustomForbiddenException e) {
        return new ResponseEntity<>(new ResponseData<>(e.getMessage(), HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>(new ResponseData<>(ex.getMessage(), HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        return new ResponseEntity<>(new ResponseData<>(ex.getMessage(), HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnexpectedException.class)
    public ResponseEntity<Object> handleUnexpectedException(final UnexpectedException e) {
        return new ResponseEntity<>(new ResponseData<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
