package br.com.devtech.api.mappers.custom;

import br.com.devtech.api.data.vo.v2.PersonVOV2;
import br.com.devtech.api.models.entities.Person;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersonCustomMapper {

  public PersonVOV2 personToPersonVOV2(Person person) {
    return PersonVOV2.builder()
        .id(person.getId())
        .firstName(person.getFirstName())
        .lastName(person.getLastName())
        .gender(person.getGender())
        .address(person.getAddress())
        .birthday(new Date())
        .build();
  }

  public List<PersonVOV2> personListToPersonVOV2List(List<Person> personList) {
    return personList.stream().map(this::personToPersonVOV2).toList();
  }
}
