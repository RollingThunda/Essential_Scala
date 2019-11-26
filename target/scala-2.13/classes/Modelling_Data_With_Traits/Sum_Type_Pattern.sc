/*
//This models data that is two or more distinct cases. We describe "A is a B or a C".
//eg Feline is Cat, Lion or Tiger.

sealed trait A
final case class B() extends A
final case class C() extends A


//You can have a "is-a AND is-a" pattern:

trait B
trait C
trait A extends B with C

//If we want to to represent that some data conforms a number of different interfaces, we
//will often use a TYPE CLASS instead.

//For a "has-a or has-a" pattern, there are two ways to implement this.
//We can say A has a d of type D, where D is a B or C
trait A {
  def d: D
}
sealed trait D
final case class B() extends D
final case class C() extends D

//Alternatively we could implement as A is a D or E, and D has a B and E has A C
sealed trait A
final case class D(b: B) extends A
final case class E(c: C) extends A
*/


//EXERCISES
//4.4.4.1 Stop on a Dime
sealed trait trafficLight
final case class red() extends trafficLight
final case class amber() extends trafficLight
final case class green() extends trafficLight

//4.4.4.2 Calculator
sealed trait calculator
final case class succeed(result: Int) extends calculator
final case class fail(message: String) extends calculator

//4.4.4.3 Watter, Water, Everywhere
sealed trait Source
case object Well extends Source
case object Spring  extends Source
case object Tap  extends Source

case class bottledWater(size: Int, source: Source, carbonated: Boolean)