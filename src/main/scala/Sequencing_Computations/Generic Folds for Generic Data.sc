//We've sen that when we define a class with generic data, we cannot implement very man methods on that class
//The user supplies the generic type, and this we must ask the user to supply functions that work with that type
//There are some common patterns for using generic data.

//FOLD
sealed trait LinkedList[A] {
  def fold[B](end: B, f: (A, B) => B): B =
    this match {
      case Pair(head, tail) => f(head, tail.fold(end, f))
      case End() => end
    }
}
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]

//fold is just an adaption of structural recursion where we allow the user to pass
//in the functions we apply at each case.
//Any function you write on an algebraic datatype can be written in terms of fold!

//WORKING WITH FUNCTIONS
//Placeholder syntax
((_: Int) * 2)
// res: Int => Int = <function1>
//should only be used for very small functions (lest in be confusing)

//Converting methods to functions
object Sum {
  def sum(x: Int, y: Int) = x + y
}
(Sum.sum _)
// res1: (Int, Int) => Int = <function2>

//MULTIPLE PARAMETER LISTS
//good for type inference