package br.com.devtech.api.data.vo.v1.security;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = {"username", "password"})
public class AccountCredentialsVO {

  private String username;
  private String password;
}
