package br.com.devtech.api.mappers;

import br.com.devtech.api.data.vo.v1.PersonVO;
import br.com.devtech.api.models.entities.Person;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PersonMapper {

  PersonVO personToPersonVO(Person person);

  Person personVOToPerson(PersonVO personVO);

  List<PersonVO> personListToPersonVOList(List<Person> personList);
}
