package br.com.devtech.api.mappers;

import br.com.devtech.api.data.vo.v1.BookCreateData;
import br.com.devtech.api.data.vo.v1.BookDetailsResponse;
import br.com.devtech.api.models.entities.BookEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookMapper {

  BookEntity createDataToEntity(BookCreateData createData);

  BookDetailsResponse entityToDetailsResponse(BookEntity entity);

  List<BookDetailsResponse> entitiesListToDetailsResponseList(List<BookEntity> entityList);
}
