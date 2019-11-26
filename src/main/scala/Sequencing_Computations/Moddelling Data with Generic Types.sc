//We will see with generic types we can implement generic sum and product types.

//GENERIC PRODUCT TYPES
//e.g. consider a method that returns an Int and String. What is the return type?
//We could use a regular class without type parameters. But then we would need a class for each combination
case class Pair[A, B](one: A, two: B)
val pair = Pair[String, Int]("hi", 2)
// pair: Pair[String,Int] = Pair(hi,2)
pair.one
// res0: String = hi
pair.two
// res1: Int = 2

//TUPLES
//A tuple is a generalisation of a pair to more terms. With these classes we can represent any kind of THIS AND THAT
//relationship
//Although pattern matching is the natural way to deconstruct a tuple, each class
//also has a complement of fields named 1_, 2_ etc:
val x = (1, "b", true)
// x: (Int, String, Boolean) = (1,b,true)
x._1
// res7: Int = 1

//GENERIC SUM TYPES
sealed trait Sum[A, B]
final case class Left[A, B](a: A) extends Sum[A, B]
final case class Right[A, B](b: B) extends Sum[A, B]

//GENERIC OPTIONAL VALUES
trait Maybe[A]
final case class Full[A](a: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]
val perhaps: Maybe[Int] = Empty[Int]
val perhaps: Maybe[Int] = Full(1)