//4.5.6.1 Traffic Lights



sealed trait TrafficLight {
  def next: TrafficLight =
    this match {
      case Red => Red

      case Green => Yellow
      case Yellow => Red
    }
}
case object Red extends TrafficLight
case object Green extends TrafficLight
case object Yellow extends TrafficLight


//4.5.6.2 Calculation
sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(reason: String) extends Calculation

object Calculator {
  def +(calc: Calculation, operand: Int): Calculation =
    calc match {
      case Success(result) =>  Success(result + operand)
      case Failure(reason) => Failure(reason)
    }

  def -(calc: Calculation, operand: Int): Calculation =
    calc match {
      case Success(result) =>  Success(result - operand)
      case Failure(reason) => Failure(reason)
    }

  def div(calc: Calculation, operand: Int): Calculation =
    calc match {
      case Success(0) => Failure("Divide by zero")
      case Success(result) => Success(result / operand)
      case Failure(reason) => Failure(reason)
    }
}

assert(Calculator.+(Success(1), 1) == Success(2))
assert(Calculator.-(Success(1), 1) == Success(0))
assert(Calculator.+(Failure("Badness"), 1) == Failure("Badness"))
assert(Calculator.+(Success(1), 1) == Success(2))
assert(Calculator.-(Success(1), 1) == Success(0))
assert(Calculator.+(Failure("Badness"), 1) == Failure("Badness"))

