package br.com.devtech.api.controllers;

import br.com.devtech.api.data.vo.v2.PersonVOV2;
import br.com.devtech.api.services.PersonServiceV2;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person/v2")
@RequiredArgsConstructor
@Tag(name = "People V2", description = "Endpoints to Managing People")
public class PersonControllerV2 {

  private final PersonServiceV2 personServiceV2;

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
                  array = @ArraySchema(schema = @Schema(implementation = PersonVOV2.class)))
            }),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public ResponseEntity<List<PersonVOV2>> findAll() {
    return ResponseEntity.ok(personServiceV2.findAll());
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
            content = @Content(schema = @Schema(implementation = PersonVOV2.class))),
        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public ResponseEntity<PersonVOV2> findById(@PathVariable(value = "id") Long id) {
    var person = personServiceV2.findById(id);
    return ResponseEntity.ok(person);
  }
}
