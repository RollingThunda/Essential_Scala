//GENERICS
//Let's start with a box that stores a single value.
final case class Box[A](value: A)
Box(2)
res0.value
Box("hi")
res2.value

//The syntax [A] is a TYPE PARAMETER. WE can use them in methods:
def generic[A](in: A): A = in
generic[String]("foo")
generic(1) //Scala will infer if we omit the type parameter

//traits also get generics
sealed trait Result[A]
case class Success[A](result: A) extends Result[A]
case class Failure[A](reason: String) extends Result[A]

//Exercises
//5.1.3 Generic List
//5.1.3.2 Working With Generic Types
sealed trait LinkedList[A] {
  def length: Int =
    this match {
      case End() => 0
      case Pair(head, tail) => 1 + tail.length
    }
}
final case class End[A]() extends LinkedList[A]
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]

val example = Pair(1, Pair(2, Pair(3, End())))
assert(example.length == 3)
assert(example.tail.length == 2)
assert(End().length == 0)