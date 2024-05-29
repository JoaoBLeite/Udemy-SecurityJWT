package br.com.devtech.api.services;

import br.com.devtech.api.data.vo.v2.PersonVOV2;
import br.com.devtech.api.exceptions.ResourceNotFoundException;
import br.com.devtech.api.mappers.custom.PersonCustomMapper;
import br.com.devtech.api.repositories.PersonRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceV2 {

  private final PersonRepository personRepository;

  private final PersonCustomMapper personCustomMapper;

  public List<PersonVOV2> findAll() {
    var persons = personRepository.findAll();
    return personCustomMapper.personListToPersonVOV2List(persons);
  }

  public PersonVOV2 findById(Long id) {
    var entity =
        personRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    return personCustomMapper.personToPersonVOV2(entity);
  }
}
