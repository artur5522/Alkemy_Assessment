package com.restApi.security.jwt.Api.exeptions;

import com.restApi.security.jwt.Api.Helpers.ErrorDetails;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //catch the particular exception and create an ErrorDetails object to send to the client
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(true));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(TokenRelatedExeption.class)
    public ResponseEntity<ErrorDetails> handleTokenException(TokenRelatedExeption exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SortMovieException.class)
    public ResponseEntity<ErrorDetails> handleSortMovieException(SortMovieException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CharacterMovieAssociationException.class)
    public ResponseEntity<ErrorDetails> handleCharacterMovieAssociationException(CharacterMovieAssociationException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest) {
        //check if error is for bad credentials
        if (exception.getMessage().equalsIgnoreCase("bad credentials")) {
            ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
        }
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }

    //method for handling javax.validations exceptions in DTOS objects
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errorsMap = new HashMap<>();
        //map all request errors 
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errorsMap.put(fieldName, message);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMap);
    }
}
