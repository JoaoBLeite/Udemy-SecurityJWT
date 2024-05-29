package br.com.devtech.api.services;

import br.com.devtech.api.data.vo.v1.security.AccountCredentialsVO;
import br.com.devtech.api.data.vo.v1.security.TokenVO;
import br.com.devtech.api.repositories.UserRepository;
import br.com.devtech.api.security.jwt.JwtTokenProvider;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;

  private final JwtTokenProvider jwtTokenProvider;

  private final UserRepository userRepository;

  public TokenVO signin(AccountCredentialsVO accountCredentials) {
    try {
      var username = accountCredentials.getUsername();
      var password = accountCredentials.getPassword();

      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));

      var user = userRepository.findByUsername(username);
      var tokenResponse = new TokenVO();
      if (Objects.nonNull(user)) {
        tokenResponse = jwtTokenProvider.createAccessToken(username, user.getRoles());
      } else {
        throw new UsernameNotFoundException("Username " + username + " not found!");
      }

      return tokenResponse;
    } catch (Exception e) {
      throw new BadCredentialsException("Invalid username/password supplied!");
    }
  }
}
