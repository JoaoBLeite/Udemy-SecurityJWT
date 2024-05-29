package br.com.devtech.api.services;

import br.com.devtech.api.data.vo.v1.PersonVO;
import br.com.devtech.api.exceptions.ResourceNotFoundException;
import br.com.devtech.api.mappers.PersonMapper;
import br.com.devtech.api.repositories.PersonRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonRepository personRepository;

  private final PersonMapper personMapper;

  public PersonVO create(PersonVO personVO) {

    var person = personMapper.personVOToPerson(personVO);
    var entity = personRepository.save(person);

    return personMapper.personToPersonVO(entity);
  }

  public List<PersonVO> findAll() {
    var persons = personRepository.findAll();
    return personMapper.personListToPersonVOList(persons);
  }

  public PersonVO findById(Long id) {
    var entity =
        personRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    return personMapper.personToPersonVO(entity);
  }

  public PersonVO update(PersonVO personVO) {

    var entity =
        personRepository
            .findById(personVO.getId())
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

    if (Objects.nonNull(personVO.getFirstName())) {
      entity.setFirstName(personVO.getFirstName());
    }

    if (Objects.nonNull(personVO.getLastName())) {
      entity.setLastName(personVO.getLastName());
    }

    if (Objects.nonNull(personVO.getGender())) {
      entity.setGender(personVO.getGender());
    }

    if (Objects.nonNull(personVO.getAddress())) {
      entity.setAddress(personVO.getAddress());
    }

    return personMapper.personToPersonVO(personRepository.save(entity));
  }

  public void delete(Long id) {
    var entity =
        personRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

    personRepository.delete(entity);
  }
}
