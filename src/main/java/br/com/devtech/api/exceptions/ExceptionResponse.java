package br.com.devtech.api.exceptions;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ExceptionResponse implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  private final LocalDateTime timestamp;
  private final String message;
  private final String details;

  public ExceptionResponse(String message, String details) {
    this.timestamp = LocalDateTime.now();
    this.message = message;
    this.details = details;
  }
}
