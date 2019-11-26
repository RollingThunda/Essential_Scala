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

  //5.4.2 Mapping Maybe
  def map[B](fn: A => B): Maybe[B] =
    this.flatMap[B](v => Full[B](fn(v)))
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

//EXERCISES
//5.5.4.1 Mapping Lists
val list: LinkedList[Int] = Pair(1, Pair(2, Pair(3, End())))
val newList =
  list
    .map[Int](v => 2*v)
    .map[Int](v => v+1)
    .map[Double](v => v.toFloat/3)


//5.5.4.3 Sequencing Computations
val list2 = List(1, 2, 3)
val list2Neg = list2.flatMap[Int](v => List(v, -v))

val list3: List[Maybe[Int]] = List(Full(3), Full(2), Full(1))
val listEven = list3.map[Maybe[Int]](v => v.flatMap[Int]{ x =>
  if (x%2 == 0) Full(x) else Empty()
})

//5.5.4.4 Sum
sealed trait Sum[A, B] {
  def fold[C](fail: A => C, success: B => C): C =
    this match {
      case Fail(v) => fail(v)
      case Success(v) => success(v)
    }

  def map[C](fn: B => C): Sum[A, C] =
    this match {
      case Fail(value) => Fail(value)
      case Success(value) => Success(fn(value))
    }

  def flatMap[C](fn: B => Sum[A, C]): Sum[A, C] =
    this match {
      case Fail(value) => Fail(value)
      case Success(value) => fn(value)
    }
}

final case class Fail[A, B](value: A) extends Sum[A, B]
final case class Success[A, B](value: B) extends Sum[A, B]



