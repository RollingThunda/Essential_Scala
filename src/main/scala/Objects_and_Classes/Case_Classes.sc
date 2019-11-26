//Case classes are a useful shorthand for defining a class, a companion object, and a lot of sensible defaults
//in one go. Useful for creating lightweight data-holding classes with minimal hassle]
case class Person(firstName: String, surName: String) {
  def name = firstName + " " + surName
}
//Whenever we declare a case class, Scala automatically generates a class and companion object
val dave = new Person("Dave", "Gurnell")
Person

//The class and companion are pre-populated with:
//1. A field for each constructor argument
dave.firstName
//Note no need to write val in our constructor definition

//2. default toString method that prints a sensible constructor-like representation of the class
dave

//3. Sensible equals and hasCode methods
new Person("Joe", "Mumma").equals(new Person("Joe", "Mumma"))
new Person("George", "Washington") == new Person("George", "Washington")
new Person("George", "Washington") == new Person("George", "Wwshington")

//4. A copy method that creates a new object with the same field values as the current one:
dave.copy()
val daveCopy = dave.copy()
dave == daveCopy
dave.copy(firstName = "Baz")
dave.copy(surName = "Longbottom")

//5. Two traits: java.io.Serializable and scala.Product
//though neither are used directly

//Case Objects!
//a case class with no constructor args, you can instead define a case object
case object Citizen {
  def firstName = "Foster"
  def surName = "Kane"
  def name = s"$firstName $surName"
}
Citizen.toString

//Case classes are the bread and butter of scala data types.

case class Counter(count: Int = 0) {
  def inc = copy(count + 1)
  def dec = copy(count - 1)
}

//What happens when we define a companion object for a case class?
case class Person(firstName: String, surName: String) {
  def name = firstName + " " + surName
}

object Person {
  def apply(name: String): Person = {
    val parts = name.split(" ")
    new Person(parts(0), parts(1))
  }
}

//we end up with a companion object with an overloaded apply method
Person("George Washington")
Person("George", "Washington")