import org.scalatest.FunSuite
import Calculator_exp._

class CalculatorTest extends  FunSuite {
  test("calculation general") {
    assert(Addition(SquareRoot(Number(-1)), Number(2)).eval ==
      Failure("Square root of negative number"))
    assert(Addition(SquareRoot(Number(4)), Number(2)).eval == Success
    (4.0))
    assert(Division(Number(4), Number(0)).eval == Failure("Division by zero"))
  }
}