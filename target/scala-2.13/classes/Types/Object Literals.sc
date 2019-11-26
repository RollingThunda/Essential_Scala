object Test {}
Test

object Test2 {
  def name: String = "Probably the best object"
}
Test2.name

object Test3 {
  def hello(name: String) = {
    "hello" + name
  }
}
Test3.hello("Joe")

println("methods vs fields")
object Test7 {
  val simpleField = {
    println("Evaluating simpleField")
    42
  }

  lazy val lazyField = {
    println("Evaluating lazyField")
    42
  }

  def noParameterMethod = {
    println("Evaluating noParameterMethod")
    42
  }
}
//When an object is first loaded, Scala runs through its definitions and calculates
//values of each of its fields.
//The body expression of a field is run only after, after which the final value is stored
//in the object
Test7
Test7.simpleField
Test7.lazyField
Test7.lazyField
Test7.noParameterMethod
Test7.noParameterMethod

//incidentally, this demonstrates "dirty functions". println is a noddy example of side-effect

println("Order of evaluation")
object argh {
  def a = {
    println("a")
    1
  }

  val b = {
    println("b")
    a + 2
  }

  def c = {
    println("c")
    a
    b + "c"
  }
}
argh.c + argh.b + argh.a

println("Greetings, human")
object Person {
  val firstName = "Joe"
  val lastName = "Muamma"
}

object Alien {
  def greet(p: Person.type ): String = {
    "Greetings" + p.firstName + p.lastName
  }
}
Alien.greet(Person)
//Notice the type on the p parameter of person.type. This is a singleton type.
//this prevents us using greet on any other object.
//This imposes a significant limitation on our ability to write programs in Scala
//We can only write methods that work with built-in types or singletons.
//We need the ability to define our own types and create multiple values of each
//enter the scene: CLASSES
