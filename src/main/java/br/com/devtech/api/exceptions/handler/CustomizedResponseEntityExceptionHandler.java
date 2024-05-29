package br.com.devtech.api.exceptions.handler;

import br.com.devtech.api.exceptions.ExceptionResponse;
import br.com.devtech.api.exceptions.InvalidJwtAuthenticationException;
import br.com.devtech.api.exceptions.ResourceNotFoundException;
import br.com.devtech.api.exceptions.UnsupportedMathOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExceptionResponse> handlerAllExceptions(
      Exception ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(UnsupportedMathOperationException.class)
  public final ResponseEntity<ExceptionResponse> handlerUnsupportedMathOperationExceptions(
      Exception ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public final ResponseEntity<ExceptionResponse> handlerResourceNotFoundExceptions(
      Exception ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidJwtAuthenticationException.class)
  public final ResponseEntity<ExceptionResponse> handlerInvalidJwtAuthenticationExceptions(
      Exception ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
  }
}
