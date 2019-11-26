//Now we will look at some other common patterns of computation that are
//1. Often more convenient to use than fold for algebraic data types
//2. Can be implemented for certain types of data that do not support a fold.

//MAP
//This encapsulates the pattern of having a type F[A] and a function A => B, and we want a result F[B]
sealed trait LinkedList[A] {
  def map[B](fn: A=> B): LinkedList[B] =
    this match {
      case Pair(head, tail) => Pair(fn(head), tail.map(fn))
      case End() => End[B]()
    }
}
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]

//FLATMAP
//This encapsulates the pattern of having a type F[A], a function A => F[B], and wanting a result F[B]
sealed trait Maybe[A] {
  def flatMap[B](fn: A => Maybe[B]): Maybe[B] =
    this match {
      case Full(value) => fn(value)
      case Empty() => Empty[B]()
    }
}
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]

//FUNCTORS AND MONADS
//A type like F[A] with a map method is called a FUNCTOR. If a functor also has a flatMap method it's called a MONAD.
//Although the immediate use of map and flatmap are in collection classes like Lists, the bigger picture is
//sequencing computations.
//The general idea is a monad represents a value some context. The context depends on the monad we're using. eg:
//>An optional value
//>a sum of values
//>a list of values

