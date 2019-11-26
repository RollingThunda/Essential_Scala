//Functions allow us to abstract over methods, turning methods into values that we can pass around.

//observe the following code:

sealed trait IntList {
  def length: Int =
    this match {
      case End => 0
      case Pair(hd, tl) => 1 + tl.length
    }
  def double: IntList =
    this match {
      case End => End
      case Pair(hd, tl) => Pair(hd * 2, tl.double)
    }
  def product: Int =
    this match {
      case End => 1
      case Pair(hd, tl) => hd * tl.product
    }
  def sum: Int =
    this match {
      case End => 0
      case Pair(hd, tl) => hd + tl.sum
    }
}
case object End extends IntList
case class Pair(hd: Int, tl: IntList) extends IntList

//All these methods have the same general pattern. It would be nice to remove duplication

def abstraction(end: Int, f: ???): Int =
  this match {
    case End => end
    case Pair(hd, tl) => f(hd, tl.abstraction(end, f))
  }

//Using f to denote some kind of object that does the combination of the head and recursive call for the Pair case.
//Enter the scene: FUNCTION
//A function is like a method: we can call it with parameters and it evaluates to a result.
//Unlike a method, a function is a value. We can pass a function around.

//FUNCTION TYPE
//We write a function type like (A, B) => C

//FUNCTION LITERALS
val sayHi = () => "Hi!"
// sayHi: () => String = <function0>
sayHi()
// res1: String = Hi!
val add1 = (x: Int) => x + 1
// add1: Int => Int = <function1>
add1(10)
// res2: Int = 11
val sum = (x: Int, y:Int) => x + y
// sum: (Int, Int) => Int = <function2>
sum(10, 20)
// res3: Int = 30