import java.time.temporal.TemporalAmount

import org.w3c.dom.css.Counter
//Here we see how to abstract over objects using classes, which are templates for constructing objects.

//Defining a class
class Person {
  val firstName = "Joe"
  val lastName = "Momma"
  def name = firstName + " " + lastName
}
//Person
//unlike objects, we cannot use a class in an expression.
//A class is not a value, and there is a different namespace in which classes live

val Joe = new Person
Joe.firstName
val newJoe = new Person

object alien {
  def greet (p: Person) =
    "Greetings, " + p.firstName + " " + p.lastName
}
alien.greet(Joe)
alien.greet(newJoe)

//TIP: Scala classes are all subclasses of java.lang.Object
//This gives compatibility Scala => Java
//In Scala this is known as "AnyRef"

//Constructors
//As it stands our Person class is rather useless: they all have the same name

class Person(first: String, last: String) {
  val firstName = first
  val lastName = last
  def name = firstName + " " + lastName
}
val baz = new Person("Baz", "Turner")
baz.name

//This can be short-handed to
class Person(val firstName: String, val lastName: String) {
  def name = firstName + " " + lastName
}
new Person("Harry", "Potter").firstName

//types at bottom of hierarchy
def bad = throw new Exception("error")
def otherBad = null
def bar = if(false) "foo" else otherBad
bar
//for category theory on sets, Nothing is the empty set
//def absurd[A]: Nothing => A
//compiles but can't run absurd!

//Exercises
class Adder(amount: Int) {
  def add(in: Int) = in + amount
}

class Counter(val count: Int) {
  def dec = new Counter(count - 1)
  def inc = new Counter(count + 1)
  //"overloading"
  def dec(amount: Int = 1): Counter = new Counter(count - amount)
  def inc(amount: Int = 1): Counter = new Counter(count + amount)

  def adjust(adder: Adder) = new Counter(adder.add(count))
}
//because fields are immutable, we must return new Counter.
//Gives a way of returning new state without side-effects of assignment.
//They also permit method chaining:
new Counter(10).inc.dec.inc.count
new Counter(10).adjust(new Adder(15)).inc.count
new Counter(10).inc(15).inc.count
new Counter(14).count
