//In this section we cover VARIANCE ANNOTATIONS, which allows us to control sub-class relations between
//types with type parameters

//Recall Maybe. ideally we would like to drop the unused type parameter on Empty:
/*
sealed trait Maybe[A]
final case class Full[A](value: A) extends Maybe[A]
case object Empty extends Maybe[???]
 */
//we cannot put Unit or Nothing in ???. The probelm is Maybe[Nothing] is not a
//supertype of Maybe[Int]

//If we have some type Foo[A], and A is a subtype of B, is Foo[A] a subtype of
//Foo[B]? The answer depends on the VARIANCE of the type Foo.
//The variance of a generic type determines how its supertype/subtype relationships
//change with respect to its type parameters

//A type Foo[T] is INVARIANT in terms of T, meaning that the types Foo[A] and Foo[B]
//are unrelated regardless of the relationship between A and B. This is the default
//variance of any generic type in Scala

//A type Foo[+T] is COVARIANT in terms of T, meaning that Foo[A] is a supertype of
//Foo[B] if A is a supertype of B. Most Scala collection classes are covariant
//in terms of their contents.

//A type Foo[-T] is CONTRAVARIANT in terms of T, meaning Foo[A] is a subtype of
//Foo[B] if A is a supertype of B.

//FUNCTION TYPES
trait Function0[+R] {
  def apply: R
}
trait Function1[-A, +B] {
  def apply(a: A): B
}
trait Function2[-A, -B, +C] {
  def apply(a: A, b: B): C
}
// and so on...
//Functions are contravariant in terms of their arguments and covariant in terms
//of their return type.

//COVARIANT SUM TYPES
sealed trait Maybe[+A]
final case class Full[A](value: A) extends Maybe[A]
case object Empty extends Maybe[Nothing]

//Exercises
//5.6.4.1 Covariant Sum
//5.6.6.2 Calculator again
sealed trait Sum[+A, +B] {
  def flatMap[AA >: A, C](fn: B => Sum[AA, C]): Sum[AA, C] =
    this match {
      case Fail(value) => Fail(value)
      case Success(value) => fn(value)
    }

  def map[C](fn: B => C): Sum[A, C] =
    this match {
      case Fail(value) => Fail(value)
      case Success(value) => Success(fn(value))
    }

  def fold[C](fail: A => C, success: B => C): C =
    this match {
      case Fail(v) => fail(v)
      case Success(v) => success(v)
    }
}

final case class Fail[A, B](value: A) extends Sum[A, Nothing]
final case class Success[A, B](value: B) extends Sum[Nothing, B]

//Remember that functions, hence methods, are contravariant in their input parameters.
//Therefore we need a new type that is a supertype of A in the contravariant position.

//EXERCISES
sealed trait Expression {
  def lift2(left: Expression, right: Expression, fn: (Double, Double) => Sum[String, Double]) =
    left.eval.flatMap(l => right.eval.flatMap(r => fn(l, r)))

  def eval: Sum[String, Double] =
    this match {
      case Addition(left, right) => lift2(left, right, (l, r) => Success(l+r))
      case Subtraction(left, right) => lift2(left, right, (l, r) => Success(l-r))
      case Division(left, right) => lift2(left, right, (l, r) =>
        r match {
          case 0 => Fail("can't divide by zero")
          case _ => Success(l/r)
        }
      )
      case SquareRoot(value) => value.eval flatMap { v =>
        if (v<0) {
          Fail("can't root a negative")
        }else {
          Success(Math.sqrt(v))
        }

      }

      case Number(value) => Success(value)
    }
}
final case class Addition(left: Expression, right: Expression) extends Expression
final case class Subtraction(left: Expression, right: Expression) extends Expression
final case class Division(left: Expression, right: Expression) extends Expression
final case class SquareRoot(value: Expression) extends Expression
final case class Number(value: Int) extends Expression

//Division(Number(1), Number(0)).eval
assert(Addition(Number(1), Number(2)).eval == Success(3))
assert(SquareRoot(Number(-1)).eval == Fail("can't root a negative"))
assert(Division(Number(4), Number(0)).eval == Fail("can't divide by zero"))
assert(Division(Addition(Subtraction(Number(8), Number(6)), Number(2)),Number(2)).eval==Success(2.0))