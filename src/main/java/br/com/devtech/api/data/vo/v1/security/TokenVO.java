package br.com.devtech.api.data.vo.v1.security;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TokenVO {

  private String username;
  private boolean authenticated;
  private Date created;
  private Date expiration;
  private String accessToken;
  private String refreshToken;
}
