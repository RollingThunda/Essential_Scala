//Implicit classes can be used on their won, but we most often combine them with type classes to
//create a more natural style of interface.
trait HtmlWriter[A] {
  def toHtml(in: A): String
}
object HtmlWriter {
  def apply[A] (implicit writer: HtmlWriter[A]): HtmlWriter[A] = writer
}

case class Person(name: String, email: String)

implicit object PersonWriter extends HtmlWriter[Person] {
  def toHtml(person: Person) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
}

implicit class HtmlOps[T](data: T) {
  def toHtml( implicit writer: HtmlWriter[T]) =
    writer.toHtml(data)
}

Person("John", "john@example.com").toHtml

//7.6.2 Exercises
//7.6.2.1 Drinking the Kool Aid
//7.6.2.2 Times
implicit class extraMethods(i: Int) {
  /*
  def yeah(): Unit = {
    for ( _ <- 0 until i ) println("Oh yeah!")
  }

   */

  def times(fn: Int => Unit) = {
    for ( n <- 0 until i) fn(n)
  }

  def yeah(): Unit = times(_ => println("Oh yeah!"))
}

2.yeah()
3.yeah()
-1.yeah()

3.times(i => println(s"Look - it's the number $i!"))

//7.6.3 Easy Equality
trait Equal[A] {
  def equal(v1: A, v2: A): Boolean
}
object Equal {
  def apply[A](implicit instance: Equal[A]): Equal[A] = instance

  implicit class ToEqual[A](in: A) {
    def ===(other: A)(implicit equal: Equal[A]): Boolean =
      equal.equal(in, other)
  }
}

implicit val caseInsensitiveEqual = new Equal[String] {
  def equal(s1: String, s2: String) =
    s1.toLowerCase() == s2.toLowerCase()
}

import Equal._
"foo".===("FOO")

