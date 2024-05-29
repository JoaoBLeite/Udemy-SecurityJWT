package br.com.devtech.api.services;

import br.com.devtech.api.data.vo.v1.BookCreateData;
import br.com.devtech.api.data.vo.v1.BookDetailsResponse;
import br.com.devtech.api.data.vo.v1.BookUpdateData;
import br.com.devtech.api.exceptions.ResourceNotFoundException;
import br.com.devtech.api.mappers.BookMapper;
import br.com.devtech.api.repositories.BookRepository;
import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  private final BookMapper bookMapper;

  public BookDetailsResponse create(BookCreateData bookData) {
    var entity = bookRepository.save(bookMapper.createDataToEntity(bookData));
    return bookMapper.entityToDetailsResponse(entity);
  }

  public BookDetailsResponse findById(Long id) {
    var bookEntity =
        bookRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Resource not found with ID!"));
    return bookMapper.entityToDetailsResponse(bookEntity);
  }

  public List<BookDetailsResponse> findAll() {
    var bookEntityList = bookRepository.findAll();
    return bookMapper.entitiesListToDetailsResponseList(bookEntityList);
  }

  public BookDetailsResponse update(BookUpdateData updateData) {
    var entity =
        bookRepository
            .findById(updateData.id())
            .orElseThrow(
                () -> new ResourceNotFoundException("No Books records found for the provided ID!"));

    if (Objects.nonNull(updateData.author())) {
      entity.setAuthor(updateData.author());
    }

    if (Objects.nonNull(updateData.title())) {
      entity.setTitle(updateData.title());
    }

    if (Objects.nonNull(updateData.price())) {
      entity.setPrice(updateData.price());
    }

    if (Objects.nonNull(updateData.launchDate())) {
      entity.setLaunchDate(updateData.launchDate());
    }

    return bookMapper.entityToDetailsResponse(bookRepository.save(entity));
  }

  public void delete(Long id) {
    var entity =
        bookRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("No Books records found for the provided ID!"));

    bookRepository.delete(entity);
  }
}
