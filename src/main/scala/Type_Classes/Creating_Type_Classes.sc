//7.3.1 Elements of Type Classes
//1. The actual type class itself
//2. The type class instances
//3. Interfaces using the implicit parameters
//4. Interfaces using enrichment and implicit parameters

//7.3.2 Creating a Type Class
//e.g. converting data to HTML.
trait HtmlWriter[A] {
  def write(in: A): String
}

case class Person(name: String, email: String)

object PersonWriter extends HtmlWriter[Person] {
  def write(person: Person) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
}

PersonWriter.write(Person("John", "john@example.com"))

import java.util.Date
object DateWriter extends HtmlWriter[Date] {
  def write(in: Date) = s"<span>${in.toString}</span>"
}

object ObfuscatedPersonWriter extends HtmlWriter[Person] {
  def write(person: Person) =
    s"<span>${person.name} (${person.email.replaceAll("@", " at ")})</span>"
}

//7.3.4 Exercises
///7.3.4.1 Equality
trait Equal[A] {
  def equal(left: A, right: A): Boolean
}

object PersonEmailEqual extends Equal[Person] {
  def equal(left: Person, right: Person): Boolean
  = left.email == right.email
}

object PersonNameEmailEqual extends Equal[Person] {
  def equal(left: Person, right: Person): Boolean =
    (left.name ==right.name) && (left.email == right.email)
}