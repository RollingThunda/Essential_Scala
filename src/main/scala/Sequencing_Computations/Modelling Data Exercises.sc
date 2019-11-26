//5.4.6.2 Folding Maybe
sealed trait Maybe[A] {
  def fold[B](full: A=> B, empty: B): B =
    this match {
      case Full(value) => full(value)
      case Empty() =>empty
    }
}
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]

sealed trait Sum[A, B] {
  def fold[C](left: A=> C, right: B => C): C =
    this match {
      case Left(value) => left(value)
      case Right(value) => right(value)
    }
}
final case class Left[A, B](value: A) extends Sum[A, B]
final case class Right[A, B](value: B) extends Sum[A, B]