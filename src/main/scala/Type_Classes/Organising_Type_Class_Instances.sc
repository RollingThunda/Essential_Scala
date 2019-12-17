import javax.swing.plaf.basic.DragRecognitionSupport.BeforeDrag
//7.2.1 Implicit Scope
//The compiler searches the implicit scope when it tries to find an implicit value to supply as an implicit parameter
//The first part is the normal scope where other identifiers are found.
//This includes within any enclosing class, object or trait or imported from elsewhere.
//The implicit scope also includes the companion objects of types involved in the method call with
//the implicit parameter

//def sorted[B >: A] (implicit ord: math.Ordering[B]): List[A]
//The compiler will look at :
//1. the companion object of Ordering
//2. the companion object of the type B

//The practical upshot is we can define type class instances in the companion object
//of our types and will be found by the compiler

final case class Rational(numerator: Int, denominator: Int)

object Rational {
  implicit val ordering = Ordering.fromLessThan[Rational]( (x, y) =>
    x.numerator * y.denominator < x.denominator * y.numerator
  )
}

object Example {
  def example() =
    assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted
      ==
      List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))
}
Example.example()

//7.2.2 Implicit Priority
//In practice, local scope takes precedence over instances found in companion objects

object Example2 {
  implicit val higherPriorityImplicit = Ordering.fromLessThan[Rational
  ]((x, y) =>
    x.numerator * y.denominator > x.denominator * y.numerator
  )
  def example() =
    assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted
      ==
      List(Rational(3, 4), Rational(1, 2), Rational(1, 3)))
}

Example2.example()

//7.2.5 Exercises
//7.2.5.1 Ordering Orders
final case class Order(units: Int, unitPrice: Double) {
  val totalPrice: Double = units * unitPrice
}

object Order {
  implicit val lessThanOrdering = Ordering.fromLessThan[Order]{ (x, y) =>
    x.totalPrice < y.totalPrice
  }
}
object OrderUnitPriceOrdering {
  implicit val unitPriceOrdering = Ordering.fromLessThan[Order]{ (x, y) =>
    x.unitPrice < y.unitPrice
  }
}
object OrderUnitsOrdering {
  implicit val unitsOrdering = Ordering.fromLessThan[Order] { (x, y) =>
    x.units < y.units
  }
}
