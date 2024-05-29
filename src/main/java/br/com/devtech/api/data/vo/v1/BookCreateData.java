package br.com.devtech.api.data.vo.v1;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record BookCreateData(
    @NotBlank(message = "title field is mandatory") String title,
    @NotBlank(message = "author field is mandatory") String author,
    @NotNull(message = "price field is mandatory") Double price,
    @NotNull(message = "launchDate field is mandatory") Date launchDate) {}
