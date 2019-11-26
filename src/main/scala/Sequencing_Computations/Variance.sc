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
