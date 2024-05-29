package br.com.devtech.api.controllers;

import br.com.devtech.api.data.vo.v1.PersonVO;
import br.com.devtech.api.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/person/v1")
@RequiredArgsConstructor
@Tag(name = "People V1", description = "Endpoints to Managing People")
public class PersonController {

  private final PersonService personService;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Creates a new Person",
      description = "Creates a new Person",
      tags = {"People"},
      responses = {
        @ApiResponse(
            description = "Created",
            responseCode = "201",
            content = @Content(schema = @Schema(implementation = PersonVO.class))),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public ResponseEntity<PersonVO> create(
      @RequestBody PersonVO person, UriComponentsBuilder uriBuilder) {
    var createdPerson = personService.create(person);
    var uri = uriBuilder.path("/person/v1/{id}").buildAndExpand(createdPerson.getId()).toUri();

    return ResponseEntity.created(uri).body(createdPerson);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Finds all People",
      description = "Finds all People",
      tags = {"People"},
      responses = {
        @ApiResponse(
            description = "Success",
            responseCode = "200",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))
            }),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public ResponseEntity<List<PersonVO>> findAll() {
    return ResponseEntity.ok(personService.findAll());
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Finds a Person by his Id",
      description = "Finds a Person by his Id",
      tags = {"People"},
      responses = {
        @ApiResponse(
            description = "Success",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = PersonVO.class))),
        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public ResponseEntity<PersonVO> findById(@PathVariable(value = "id") Long id) {
    var person = personService.findById(id);
    return ResponseEntity.ok(person);
  }

  @PutMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Updates a Person Data",
      description = "Updates a Person Data",
      tags = {"People"},
      responses = {
        @ApiResponse(
            description = "Updated",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = PersonVO.class))),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public ResponseEntity<PersonVO> update(@RequestBody PersonVO person) {
    var updatedPerson = personService.update(person);
    return ResponseEntity.ok(updatedPerson);
  }

  @DeleteMapping("/{id}")
  @Operation(
      summary = "Deletes a Person Data",
      description = "Deletes a Person Data",
      tags = {"People"},
      responses = {
        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
    personService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
