//Recursive data is defined in terms of itself, and allows us to create data of potentially unbounded size
//To define a valid recursive data we must define a BASE CASE

sealed trait IntList
case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList
//Here ENd is the base case. We construct a list:
Pair(1, Pair(2, Pair(3, End)))

//This a a singly-linked list.
//We write out in this form for an easier reading of the strcuture
val d = End
val c = Pair(3, d)
val b = Pair(2, c)
val a = Pair(1, b)
//a->b->c->d

//This means we can build lists of arbitrary length by repeatedly appending a new element to an existing list
//This is how List works
//example of recursive methods:
def sumList(list: IntList): Int =
  list match {
    case End => 0
    case Pair(head, tail) => head + sumList(tail)
  }

//For the base case, we should generally return the IDENTITY for the function we're trying
//to compute
//For the recursive case, assume the recursion will return the correct result and work
//out what you need to add to get the correct answer

//TAIL RECURSION
//A TAIL CALL is a method call where the caller immediately returns the value.
//a call can be optimised to not use stack space. Due to a limitation in the JVM, Scala
//can only optimise tail calls where the caller calls itself.
import scala.annotation.tailrec
@tailrec
def sumListTail(list: IntList, total: Int): Int =
  list match {
    case End => 0
    case Pair(head, tail) => sumListTail(tail, total + head)
  }
//Any non-tail recursion function can be transformed into a tail recursive version by adding
//an accumulator as we have done with sum above.