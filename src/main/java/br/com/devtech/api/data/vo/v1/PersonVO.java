package br.com.devtech.api.data.vo.v1;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PersonVO {

  private Long id;
  private String firstName;
  private String lastName;
  private String address;
  private String gender;
}
