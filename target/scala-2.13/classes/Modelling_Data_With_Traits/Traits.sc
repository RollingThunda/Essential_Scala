//Classes provide us with a way to abstract over objects that have similar properties.
//Now we use traits abstract over classes

//Traits are templates for creating classes, allowing us to express that two or more classes can be considered the same,
//amd thus both implement the same operations.

//Imagine we're modelling visitors to a website, with anon and registered visitors
import java.util.Date

//case class Anon(id: String, createdAt: Date = new Date())

//case class User(id: String, email: String, createdAt: Date = new Date())

//There is obvious duplication here. A trait can give them a type in commmon:

trait Visitor {
  def id: String
  def createdAt: Date
  def age: Long = new Date().getTime - createdAt.getTime
}

case class Anon(id: String, createdAt: Date = new Date()) extends Visitor
case class User(id: String, email: String, createdAt: Date = new Date()) extends Visitor

//The Visitor trait expresses an interface that any subtype must implement.
//Any subtype of Visitor also automatically has a method age as defined in Visitor

//By defining the Visito trait, we can write methods that work with any subtype of it:
def older(v1: Visitor, v2: Visitor): Boolean =
  v1.createdAt.before(v2.createdAt)

older(Anon("123"), User("abc", "example@gmail.com"))

//Traits compared to Classes
//1. Traits cannot have a constructor.
// Order of creation: Traits => Classes => Objects
//2. Traits can define Abstract Methods. For Visitor, id and createdAt are Abstract Methods

//Exercises
trait Feline {
  def colour: String
  def sound: String
}

case class Cat(colour: String, food: String) extends Feline {
  val sound = "meow"
}

case class Lion(colour: String, maneSize: Int) extends Feline {
  val sound = "roar"
}

//Notice that sound is not defined as a constructor arg. Since is a constant, it doesn't
//make sense to give users a chance to modify it.

trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
}

case class Circle(radius: Double) extends Shape {
  val sides = 1
  val perimeter = 2*radius*Math.PI
  val area = radius*Math.PI*Math.PI
}

trait Rectangular extends Shape {
  def width: Double
  def height: Double
  val sides = 4
  override val perimeter =  2*width + 2*height
  override val area = width*height
}

case class Rectangle(val width: Double, val height: Double) extends Rectangular
case class Square(width: Double) extends Rectangular{
  val height = width
}
