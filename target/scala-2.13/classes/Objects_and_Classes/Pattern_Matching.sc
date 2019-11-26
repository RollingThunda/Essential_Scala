//case classes is an interaction of pattern matching
//this allows us to evaluate an expression depending on the "shape" of the data.
case class Person (firstName: String, surName: String)

object Stormtrooper {
  def inspect(person: Person): String =
    person match {
      case Person("Luke", "Skywalker") => "Stop, rebel scum!"
      case Person("Han", "Solo") => "Stop, rebel scum!"
      case Person(first, last) => s"Move along, $first"
    }
}

Stormtrooper.inspect(Person("Joe", "Mummma"))
Stormtrooper.inspect(Person("Han", "Solo"))

//Pattern Syntax

//In place of values, we can place:
//1. A name, which matches any value at that position and binds it to the given name.
//2. An underscore, which matches any value and ignores it
//3. A literal value, e.g. "Luke"
//4. Another case class using the same constructor syntax

case class Cat(name: String, colour: String, food: String)

object chipShop {
  def willServe(cat: Cat): Boolean = {
    cat match {
      case Cat(_, _, "Chips") => true
      case Cat(_, _, _) => false
    }
  }
}
