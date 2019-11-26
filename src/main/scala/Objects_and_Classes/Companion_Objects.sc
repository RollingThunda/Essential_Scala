//Sometimes we want to create a method that logically belongs to a class but is independent of nay particular object.
//Scala's solution: singleton objects
//Scala programmers almost always prefer to implement additional constructors as apply methods on an object with the
//same name as the class
class Timestamp(val seconds: Long)

object Timestamp {
  def apply(hours: Long, minutes: Long, seconds: Long): Timestamp =
    new Timestamp(60*60*hours + 60*minutes + seconds)
}

Timestamp(1,1,1).seconds

//Scala has two namespaces: a space of type names and a space of value names
//Note that the companion object is NOT an instance of the class.
//It is a singleton object with its own type:
Timestamp

//A companion object must be defined in the same file as the associated class

//Friendly Person Factory
class Person(val firstName: String, val surName: String){
  def name: String = s"$firstName $surName"
}

object Person {
  def apply(name: String): Person = {
    val parts = name.split(" ")
    new Person(parts(0), parts(1))
  }
}

val Joe = Person("Joe Mumma")
Joe.name
Joe.firstName

class Director(
                val firstName: String,
                val lastName: String,
                val yearOfBirth: Int) {
  def name: String =
    s"$firstName $lastName"
  def copy(
            firstName: String = this.firstName,
            lastName: String = this.lastName,
            yearOfBirth: Int = this.yearOfBirth) =
    new Director(firstName, lastName, yearOfBirth)
}
object Director {
  def apply(firstName: String, lastName: String, yearOfBirth: Int):
  Director =
    new Director(firstName, lastName, yearOfBirth)
  def older(director1: Director, director2: Director): Director =
    if (director1.yearOfBirth < director2.yearOfBirth) director1 else
      director2
}

val clint = Director("Clint", "Eastwood", 1930)
val nolan = Director("Chris", "Nolan", 1970)
Director.older(clint, nolan).name

