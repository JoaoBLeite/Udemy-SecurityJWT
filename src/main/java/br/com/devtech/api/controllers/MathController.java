package br.com.devtech.api.controllers;

import br.com.devtech.api.enums.MathOperationEnum;
import br.com.devtech.api.exceptions.UnsupportedMathOperationException;
import br.com.devtech.api.services.MathService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
@RequiredArgsConstructor
public class MathController {

  private final MathService mathService;

  @GetMapping("/sum/{numberOne}/{numberTwo}")
  @Operation(
      summary = "Sum two numbers",
      description = "Sum two numbers",
      tags = {"Math"},
      responses = {
        @ApiResponse(
            description = "Success",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = Double.class))),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public Double sum(
      @PathVariable(value = "numberOne") String numberOne,
      @PathVariable(value = "numberTwo") String numberTwo)
      throws UnsupportedMathOperationException {
    return mathService.calculate(MathOperationEnum.SUM, numberOne, numberTwo);
  }

  @GetMapping("/sub/{numberOne}/{numberTwo}")
  @Operation(
      summary = "Subtract two numbers",
      description = "Subtract two numbers",
      tags = {"Math"},
      responses = {
        @ApiResponse(
            description = "Success",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = Double.class))),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public Double sub(
      @PathVariable(value = "numberOne") String numberOne,
      @PathVariable(value = "numberTwo") String numberTwo)
      throws UnsupportedMathOperationException {
    return mathService.calculate(MathOperationEnum.SUBTRACTION, numberOne, numberTwo);
  }

  @GetMapping("/mul/{numberOne}/{numberTwo}")
  @Operation(
      summary = "Multiply two numbers",
      description = "Multiply two numbers",
      tags = {"Math"},
      responses = {
        @ApiResponse(
            description = "Success",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = Double.class))),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public Double mul(
      @PathVariable(value = "numberOne") String numberOne,
      @PathVariable(value = "numberTwo") String numberTwo)
      throws UnsupportedMathOperationException {
    return mathService.calculate(MathOperationEnum.MULTIPLICATION, numberOne, numberTwo);
  }

  @GetMapping("/div/{numberOne}/{numberTwo}")
  @Operation(
      summary = "Divide two numbers",
      description = "Divide two numbers",
      tags = {"Math"},
      responses = {
        @ApiResponse(
            description = "Success",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = Double.class))),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public Double div(
      @PathVariable(value = "numberOne") String numberOne,
      @PathVariable(value = "numberTwo") String numberTwo)
      throws UnsupportedMathOperationException {
    return mathService.calculate(MathOperationEnum.DIVISION, numberOne, numberTwo);
  }

  @GetMapping("/mean/{numberOne}/{numberTwo}")
  @Operation(
      summary = "Average between two numbers",
      description = "Average between two numbers",
      tags = {"Math"},
      responses = {
        @ApiResponse(
            description = "Success",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = Double.class))),
        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
      })
  public Double mean(
      @PathVariable(value = "numberOne") String numberOne,
      @PathVariable(value = "numberTwo") String numberTwo)
      throws UnsupportedMathOperationException {
    return mathService.calculate(MathOperationEnum.MEAN, numberOne, numberTwo);
  }
}
