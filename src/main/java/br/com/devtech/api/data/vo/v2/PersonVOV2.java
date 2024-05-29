package br.com.devtech.api.data.vo.v2;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PersonVOV2 {

  private Long id;
  private String firstName;
  private String lastName;
  private String address;
  private String gender;
  private Date birthday;
}
