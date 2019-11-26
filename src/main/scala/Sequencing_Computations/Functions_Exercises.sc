//5.2.3.1 A Better Abstraction

sealed trait IntList {
  def fold[A](end: A, f: (Int, A) => A): A =
    this match {
      case End => end
      case Pair(head, tail) => f(head, tail.fold(end, f))
    }

  def sum: Int =
    fold[Int](0, (head, tail) => head + tail)

  def length: Int =
    fold[Int](0, (_, tail) => 1 + tail)

  def product: Int =
    fold[Int](1, (head, tail) => head*tail)

  def double: IntList = {
    fold[IntList](End, (head, tail) => Pair(head * 2, tail))
  }
}

case object End extends IntList
case class Pair(head: Int, tail: IntList) extends IntList


