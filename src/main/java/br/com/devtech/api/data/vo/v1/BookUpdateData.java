package br.com.devtech.api.data.vo.v1;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

public record BookUpdateData(
    @NotNull(message = "id field is mandatory") Long id,
    String title,
    String author,
    Double price,
    Date launchDate) {}
