//7.1 Type CLass Instances

//Type classes allow us to extend existing libraries with new functionality, without using inheritance
//or access to the original library source code
//A type class is a trait, defining an interface. However, we can:
//1. Plug in different implementations of an an interface for a given class
//2. Implement an interface without modifying existing code

//We start by USING type classes, before BUILDING them later

//7.1.1 Ordering
//Ordering trait is an example of a type class.
//For a type A, an Ordering[A] defines a comparision method compare that compares two instances of A by some ordering

//Imagine we want to sor a List of Ints

import scala.math.Ordering
val minOrdering = Ordering.fromLessThan[Int](_ < _)
val maxOrdering = Ordering.fromLessThan[Int](_ > _)
List(3, 4, 2).sorted(minOrdering)
List(3, 4, 2).sorted(maxOrdering)

//When we call sorted we pass the Ordering we want to use. These implementations of a type class
//are called TYPE CLASS INSTANCES

//7.1.2 Implicit Values
//Scala provides an IMPLICIT VALUE, that allows us to get the ocmpiler to pass the type class
//interface for us
implicit val ordering = Ordering.fromLessThan[Int](_ < _)
List(2, 4, 3).sorted
List(1, 7, 5).sorted
//The compiler will signal an error if any ambiguity in  which implicit value should be used
//arises

//7.1.6 Exercises
//7.1.6.1 More Orderings
val absOrdering = Ordering.fromLessThan[Int](math.abs(_) < math.abs(_))
assert(List(-4, -1, 0, 2, 3).sorted(absOrdering) == List(0, -1, 2, 3,
  -4))
assert(List(-4, -3, -2, -1).sorted(absOrdering) == List(-1, -2, -3,
  -4))

//7.1.6.2 Ration Orderings
final case class Rational(numerator: Int, denominator: Int)
implicit val ratOrdering = Ordering.fromLessThan[Rational]({ (x, y) =>
  x.numerator * y.denominator < x.denominator * y.numerator
})

assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted ==
  List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))

