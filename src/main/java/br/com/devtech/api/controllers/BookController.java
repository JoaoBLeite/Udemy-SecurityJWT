package br.com.devtech.api.controllers;

import br.com.devtech.api.data.vo.v1.BookCreateData;
import br.com.devtech.api.data.vo.v1.BookDetailsResponse;
import br.com.devtech.api.data.vo.v1.BookUpdateData;
import br.com.devtech.api.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
@RequestMapping("/book/v1")
@RequiredArgsConstructor
@Tag(name = "Book", description = "Endpoints to Managing Books")
public class BookController {

  private final BookService bookService;

  @PostMapping
  @Operation(
      summary = "Creates a new Book",
      description = "Creates a new Book",
      tags = {"Book"},
      responses = {
        @ApiResponse(
            description = "Created",
            responseCode = "201",
            content = @Content(schema = @Schema(implementation = BookDetailsResponse.class))),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public ResponseEntity<BookDetailsResponse> create(
      @RequestBody @Valid BookCreateData book,
      UriComponentsBuilder uriBuilder,
      HttpServletRequest request) {
    var createdBook = bookService.create(book);
    var uri = uriBuilder.path("/book/v1/{id}").buildAndExpand(createdBook.getId()).toUri();

    return ResponseEntity.created(uri).body(createdBook);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Finds a Book by his Id",
      description = "Finds a Book by his Id",
      tags = {"Book"},
      responses = {
        @ApiResponse(
            description = "Success",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = BookDetailsResponse.class))),
        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public ResponseEntity<BookDetailsResponse> findById(@PathVariable(value = "id") Long id) {
    var bookDetailsResponse = bookService.findById(id);
    return ResponseEntity.ok(bookDetailsResponse);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Finds all Books",
      description = "Finds all Books",
      tags = {"Book"},
      responses = {
        @ApiResponse(
            description = "Success",
            responseCode = "200",
            content = {
              @Content(
                  mediaType = "application/json",
                  array =
                      @ArraySchema(schema = @Schema(implementation = BookDetailsResponse.class)))
            }),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public ResponseEntity<List<BookDetailsResponse>> findAll() {
    var bookDetailsResponseList = bookService.findAll();
    return ResponseEntity.ok(bookDetailsResponseList);
  }

  @PutMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Updates a Book Data",
      description = "Updates a Book Data",
      tags = {"Book"},
      responses = {
        @ApiResponse(
            description = "Updated",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = BookDetailsResponse.class))),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public ResponseEntity<BookDetailsResponse> update(@RequestBody BookUpdateData updateData) {
    var updatedBook = bookService.update(updateData);
    return ResponseEntity.ok(updatedBook);
  }

  @DeleteMapping("/{id}")
  @Operation(
      summary = "Deletes a Book Data",
      description = "Deletes a Book Data",
      tags = {"Book"},
      responses = {
        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
    bookService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
