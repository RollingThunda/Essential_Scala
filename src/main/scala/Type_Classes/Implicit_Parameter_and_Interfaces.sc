import javax.swing.text.html.HTMLWriter
//Now let's make type classes easier to use

//7.4.1 Implicit Parameter Lists
trait HtmlWriter[A] {
  def write(in: A): String
}
object HtmlWriter {
  def apply[A] (implicit writer: HtmlWriter[A]): HtmlWriter[A] = writer
}

object HtmlUtil {
  def htmlify[A](data: A)(implicit writer: HtmlWriter[A]): String = {
    writer.write(data)
  }

}

//The implicit keyword applies to the whole parameter list, not just an individual
//parameter.

case class Person(name: String, email: String)

implicit object PersonWriter extends HtmlWriter[Person] {
  def write(person: Person) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
}

HtmlUtil.htmlify(Person("John", "john@example.com"))(PersonWriter)

implicit object ApproximationWriter extends HtmlWriter[Int] {
  def write(in: Int): String = s"It's definitely less than ${((in / 10) + 1) * 10}"
}

HtmlUtil.htmlify(2)

//7.4.2 Interfaces Using Implicit Parameters
//In many cases the interface defined by the type class is the same interface we want to use.

HtmlWriter[Person].write(Person("Noel", "noel@example.com"))

//7.4.4 Exercises
//7.4.4.1 Equality Again
case class Person(name: String, email: String)
trait Equal[A] {
  def equal(v1: A, v2: A): Boolean
}

object Equal {
  def apply[A] (implicit eq: Equal[A]): Equal[A] = eq
}

object EmailEqual extends Equal[Person] {
  def equal(v1: Person, v2: Person): Boolean =
    v1.email == v2.email
}
implicit object NameEmailEqual extends Equal[Person] {
  def equal(v1: Person, v2: Person): Boolean =
    v1.email == v2.email && v1.name == v2.name
}

object Eq {
  def apply[A](v1: A, v2: A)(implicit eq: Equal[A]): Boolean = {
    eq.equal(v1, v2)
  }
}
Eq(Person("Noel", "noel@example.com"), Person("Noel", "noel@example.com"))

Equal[Person].equal(
  Person("Noel", "noel@example.org"),
  Person("Noel", "noel@example.org")
)

