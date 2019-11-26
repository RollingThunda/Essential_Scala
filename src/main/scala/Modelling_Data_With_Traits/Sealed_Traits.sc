//Sealed traits are for when we can enumerate all possible classes that can extend a trait.

import java.awt.geom.RectangularShape
import java.util.Date
sealed trait Visitor {
  def id: String
  def createdAt: Date
  def age: Long = new Date().getTime() - createdAt.getTime()
}
//When we mark a trait as sealed we MUST define all of its subtypes in the same file
final case class Anon(id: String, createdAt: Date = new Date()) extends Visitor
final case class User(id: String, email: String, createdAt: Date = new Date()) extends Visitor

//with a sealed trait, the compiler knows to warn us when missing case:
def missingCase(v: Visitor) =
  v match {
    case User(_, _, _) => "Got a user"
  }

//We can still extend the subtypes of a sealed trait outside of the file.
//If we want to prevent this, we should declare them as sealed or final if want to prevent
//extensions even in the same file
sealed trait Shape {
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

//Exercises
object Draw {
  def apply(s: Shape): String = s match {
    case Rectangle(width, height) => s"A rectable of width $width and height $height"
  }
}

//Good Scala developers use types as artificial limitations in place to ensure we don't make
//mistakes in our programs.

sealed trait DivisionResult
case class Finite (val top: Int, val bot: Int) extends DivisionResult
case object Infinite extends DivisionResult


object Divide {
  def apply(a: Int, b: Int): DivisionResult = b match  {
    case 0 => Infinite
    case b => Finite(a,b)
  }
}

val x = Divide(1, 2)
val y = Divide(1, 0)