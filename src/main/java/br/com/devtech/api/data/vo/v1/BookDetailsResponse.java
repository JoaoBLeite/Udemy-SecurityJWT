package br.com.devtech.api.data.vo.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailsResponse {

  private Long id;
  private String title;
  private String author;
  private Double price;
  private Date launchDate;
}
