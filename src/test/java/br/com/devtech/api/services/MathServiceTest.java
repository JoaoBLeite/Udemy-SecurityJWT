package br.com.devtech.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.devtech.api.enums.MathOperationEnum;
import br.com.devtech.api.exceptions.UnsupportedMathOperationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MathServiceTest {

  private static MathService mathService;

  private static final String VALID_NUMBER_1 = "10";
  private static final String VALID_NUMBER_2 = "5";
  private static final String INVALID_NUMBER = "invalid number";

  private static final double SUM_RESULT = 15;
  private static final double SUB_RESULT = 5;
  private static final double MUL_RESULT = 50;
  private static final double DIV_RESULT = 2;
  private static final double MEAN_RESULT = 7.5;

  @BeforeAll
  public static void setUp() {
    mathService = new MathService();
  }

  @Test
  public void testCalculateSumWhenAddingTwoNumbersThenReturnSum() {
    double result = mathService.calculate(MathOperationEnum.SUM, VALID_NUMBER_1, VALID_NUMBER_2);
    assertEquals(SUM_RESULT, result);
  }

  @Test
  public void testCalculateSubtractionWhenSubtractingTwoNumbersThenReturnDifference() {
    double result =
        mathService.calculate(MathOperationEnum.SUBTRACTION, VALID_NUMBER_1, VALID_NUMBER_2);
    assertEquals(SUB_RESULT, result);
  }

  @Test
  public void testCalculateMultiplicationWhenMultiplyingTwoNumbersThenReturnProduct() {
    double result =
        mathService.calculate(MathOperationEnum.MULTIPLICATION, VALID_NUMBER_1, VALID_NUMBER_2);
    assertEquals(MUL_RESULT, result);
  }

  @Test
  public void testCalculateDivisionWhenDividingTwoNumbersThenReturnQuotient() {
    double result =
        mathService.calculate(MathOperationEnum.DIVISION, VALID_NUMBER_1, VALID_NUMBER_2);
    assertEquals(DIV_RESULT, result);
  }

  @Test
  public void testCalculateMeanWhenCalculatingMeanOfTwoNumbersThenReturnAverage() {
    double result = mathService.calculate(MathOperationEnum.MEAN, VALID_NUMBER_1, VALID_NUMBER_2);
    assertEquals(MEAN_RESULT, result);
  }

  @Test
  public void testCalculateWithNonNumericInputThenThrowUnsupportedMathOperationException() {
    assertThrows(
        UnsupportedMathOperationException.class,
        () -> mathService.calculate(MathOperationEnum.SUM, VALID_NUMBER_1, INVALID_NUMBER));
  }
}
