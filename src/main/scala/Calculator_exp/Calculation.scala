package Calculator_exp

sealed trait Calculation
case class Success(result: Double) extends Calculation
case class Failure(reason: String) extends Calculation
