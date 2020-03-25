package cooperative.controllers;

import cooperative.exception.CarNotFoundException;
import cooperative.exception.CustomerNotFoundException;
import cooperative.exception.GarageNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ CustomerNotFoundException.class })
    protected ResponseEntity<Object> handleCustomerNotFound(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Customer not found",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ CarNotFoundException.class })
    protected ResponseEntity<Object> handleCarNotFound(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Customer not found",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ GarageNotFoundException.class })
    protected ResponseEntity<Object> handleGarageNotFound(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Customer not found",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
