package br.com.devtech.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.devtech.api.data.vo.v1.PersonVO;
import br.com.devtech.api.exceptions.ResourceNotFoundException;
import br.com.devtech.api.mappers.PersonMapper;
import br.com.devtech.api.models.entities.Person;
import br.com.devtech.api.repositories.PersonRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

  @Mock private PersonRepository personRepository;
  @Mock private static PersonMapper personMapper;

  @InjectMocks private PersonService personService;

  private final Person PERSON = new Person(1L, "Fn1", "Ln1", "Ad1", "Gn1");
  private final PersonVO EXPECTED_PERSON_VO = new PersonVO(1L, "Fn1", "Ln1", "Ad1", "Gn1");
  private final List<Person> PERSON_LIST =
      Arrays.asList(
          new Person(1L, "Fn1", "Ln1", "Ad1", "Gn1"), new Person(2L, "Fn2", "Ln2", "Ad2", "Gn2"));
  private final List<PersonVO> EXPECTED_PERSON_VO_LIST =
      Arrays.asList(
          new PersonVO(1L, "Fn1", "Ln1", "Ad1", "Gn1"),
          new PersonVO(2L, "Fn2", "Ln2", "Ad2", "Gn2"));

  @Test
  public void create_whenSaveMethodIsCalled_savePersonData() {
    // Arrange
    when(personRepository.save(PERSON)).thenReturn(PERSON);
    when(personMapper.personVOToPerson(EXPECTED_PERSON_VO)).thenReturn(PERSON);
    when(personMapper.personToPersonVO(PERSON)).thenReturn(EXPECTED_PERSON_VO);

    // Act
    var result = personService.create(EXPECTED_PERSON_VO);

    // Assert
    assertNotNull(result);
  }

  @Test
  public void testFindAllWhenGettingAllPersonsThenReturnListOfPersons() {
    // Arrange
    when(personRepository.findAll()).thenReturn(PERSON_LIST);
    when(personMapper.personListToPersonVOList(PERSON_LIST)).thenReturn(EXPECTED_PERSON_VO_LIST);

    // Act
    var result = personService.findAll();

    // Assert
    assertEquals(EXPECTED_PERSON_VO_LIST, result);
  }

  @Test
  public void testFindByIdWhenGettingPersonByIdThenReturnPerson() {
    // Arrange
    when(personRepository.findById(anyLong())).thenReturn(Optional.of(PERSON));
    when(personMapper.personToPersonVO(PERSON)).thenReturn(EXPECTED_PERSON_VO);

    // Act
    var result = personService.findById(PERSON.getId());

    // Assert
    assertNotNull(result);
    assertEquals(PERSON.getId(), result.getId());
  }

  @Test
  public void testFindByIdWhenPersonNotFoundThenThrowResourceNotFoundException() {
    // Arrange
    final Long NON_EXISTENT_ID = -1L;
    when(personRepository.findById(NON_EXISTENT_ID)).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(ResourceNotFoundException.class, () -> personService.findById(NON_EXISTENT_ID));
  }

  @Test
  public void testUpdatePersonWhenExistingIdThenReturnUpdatedPersonVO() {
    // Arrange
    Long id = 1L;
    PersonVO inputPersonVO = new PersonVO(id, "John", "Doe", "Male", "123 Street");
    Person inputPerson = new Person(id, "John", "Doe", "Male", "123 Street");
    Person updatedPerson = new Person(id, "John", "Doe", "Male", "123 Updated Street");

    when(personRepository.findById(id)).thenReturn(Optional.of(inputPerson));
    when(personRepository.save(any(Person.class))).thenAnswer(invocation -> {
        // Simulate the save operation
      return invocation.<Person>getArgument(0);
    });
    when(personMapper.personToPersonVO(updatedPerson)).thenReturn(inputPersonVO);

    // Act
    PersonVO result = personService.update(inputPersonVO);

    // Assert
    assertNotNull(result);
    assertEquals(inputPersonVO.getId(), result.getId());
    assertEquals(inputPersonVO.getFirstName(), result.getFirstName());
    assertEquals(inputPersonVO.getLastName(), result.getLastName());
    assertEquals(inputPersonVO.getGender(), result.getGender());
  }

  @Test
  public void update_whenPersonNotFound_throwsException() {
    // Arrange
    PersonVO personVO = new PersonVO();
    personVO.setId(anyLong()); // Assuming ID does not exist in the database

    when(personRepository.findById(personVO.getId())).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(
        ResourceNotFoundException.class,
        () -> {
          personService.update(personVO);
        });

    // Verify
    verify(personRepository, times(1)).findById(personVO.getId());
    verify(personRepository, never()).save(any());
  }

  @Test
  public void delete_whenDeleteMethodIsCalled_deletePersonData() {
    // Arrange
    Long id = 1L;
    Person person = new Person();
    person.setId(id);
    when(personRepository.findById(id)).thenReturn(Optional.of(person));

    // Act
    personService.delete(id);

    // Assert
    verify(personRepository, times(1)).delete(person);
  }

  @Test
  public void delete_whenPersonNotFound_throwsException() {
    // Arrange
    when(personRepository.findById(anyLong())).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(
        ResourceNotFoundException.class,
        () -> {
          personService.delete(anyLong());
        });

    // Verify
    verify(personRepository, times(1)).findById(anyLong());
    verify(personRepository, never()).delete(any());
  }
}
