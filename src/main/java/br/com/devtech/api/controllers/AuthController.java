package br.com.devtech.api.controllers;

import br.com.devtech.api.data.vo.v1.security.AccountCredentialsVO;
import br.com.devtech.api.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/v1")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Endpoints to handle authentication matters")
public class AuthController {

  private final AuthService authService;

  @Operation(summary = "Authenticates a user and returns a token")
  @PostMapping(value = "/signin")
  public ResponseEntity<?> signin(@RequestBody AccountCredentialsVO accountCredentials) {
    if (checkIfAreInvalidParams(accountCredentials)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
    }

    var token = authService.signin(accountCredentials);

    if (Objects.isNull(token)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
    }

    return ResponseEntity.ok(token);
  }

  private boolean checkIfAreInvalidParams(AccountCredentialsVO accountCredentials) {
    return (Objects.isNull(accountCredentials)
        || Objects.isNull(accountCredentials.getUsername())
        || accountCredentials.getUsername().isBlank()
        || Objects.isNull(accountCredentials.getPassword())
        || accountCredentials.getPassword().isBlank());
  }
}
