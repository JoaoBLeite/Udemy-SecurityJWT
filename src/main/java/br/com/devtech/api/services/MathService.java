package br.com.devtech.api.services;

import br.com.devtech.api.enums.MathOperationEnum;
import br.com.devtech.api.exceptions.UnsupportedMathOperationException;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class MathService {

  /**
   * Calculates the result of a mathematical operation.
   *
   * @param operation the mathematical operation to perform (e.g., SUM, SUBTRACTION, MULTIPLICATION, DIVISION)
   * @param numberOne the first number for the operation
   * @param numberTwo the second number for the operation
   * @return the result of the mathematical operation
   * @throws UnsupportedMathOperationException if either of the input parameters is not numeric
   */
  public double calculate(MathOperationEnum operation, String numberOne, String numberTwo)
      throws UnsupportedMathOperationException {

    if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
      throw new UnsupportedMathOperationException("Parameter not valid! Must be numeric!");
    }

    return switch (operation) {
      case SUM -> Double.parseDouble(numberOne) + Double.parseDouble(numberTwo);
      case SUBTRACTION -> Double.parseDouble(numberOne) - Double.parseDouble(numberTwo);
      case MULTIPLICATION -> Double.parseDouble(numberOne) * Double.parseDouble(numberTwo);
      case DIVISION -> Double.parseDouble(numberOne) / Double.parseDouble(numberTwo);
      case MEAN -> (Double.parseDouble(numberOne) + Double.parseDouble(numberTwo)) / 2;
    };
  }

  /**
   * Validate if a number is valid or not
   *
   * @param strNumber Number on String format
   * @return true: valid | false: non valid
   */
  private boolean isNumeric(String strNumber) {
    if (Objects.isNull(strNumber)) {
      return false;
    }

    return strNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
  }
}
