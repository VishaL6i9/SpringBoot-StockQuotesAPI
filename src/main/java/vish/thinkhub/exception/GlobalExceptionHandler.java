package vish.thinkhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StockQuoteException.class)
    public ResponseEntity<ErrorResponse> handleStockQuoteException(StockQuoteException ex) {
        ErrorResponse error = new ErrorResponse("Stock Quote Error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<ErrorResponse> handleExternalApiException(ExternalApiException ex) {
        if (ex.getMessage().contains("API rate exceeded")) {
            ErrorResponse error = new ErrorResponse("API Rate Exceeded", "API rate limit exceeded. Please try again later.");
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).header("Location", "/error/api-rate-exceeded").build();
        } else {
            ErrorResponse error = new ErrorResponse("External API Error", ex.getMessage());
            return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse("Internal Server Error", "An unexpected error occurred");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
