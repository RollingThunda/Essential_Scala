//A SEQUENCE is a collection of items with a defined and stable order.
val sequence = Seq(1, 2, 3)
// sequence: Seq[Int] = List(1, 2, 3)
//The value has a type Seq[Int] but is implemented by a List

//BASIC OPERATIONS

//ACCESSING
//We can access elements of a sequence using its apply method
sequence.apply(0)
// res0: Int = 1
sequence(0) // sugared syntax
// res1: Int = 1

sequence.head
// res5: Int = 1
sequence.tail
// res6: Seq[Int] = List(2, 3)

//LENGTH
sequence.length
// res19: Int = 3

//SEARCHING
sequence.contains(2)
// res20: Boolean = true

sequence.find(_ == 3)
// res21: Option[Int] = Some(3)
sequence.find(_ > 4)
// res22: Option[Int] = None

sequence.filter(_ > 1)
// res23: Seq[Int] = List(2, 3)

//SORTING
sequence.sortWith(_ > _)
// res24: Seq[Int] = List(3, 2, 1)

//APPENDING
sequence.:+(4)
// res25: Seq[Int] = List(1, 2, 3, 4)

//LISTS
//Lists have constant time in perpend, head and tail operations and linear-time in append, apply and update.
//Other immutable sequences are available in Scala with different performance characteristics
// they can be imported with
import scala.collection.immutable._
Vector(1, 2, 3)
// res35: scala.collection.immutable.Vector[Int] = Vector(1, 2, 3)
Queue(1, 2, 3)
// res36: scala.collection.immutable.Queue[Int] = Queue(1, 2, 3)

//EXERCISES
//6.1.9.2 Animals
val animals = Seq("cat", "dog", "penguin")
"mouse" +: animals :+ "tyrannosaurus"
2 +: animals

case class Film(
                 name: String,
                 yearOfRelease: Int,
                 imdbRating: Double)


case class Director(
                     firstName: String,
                     lastName: String,
                     yearOfBirth: Int,
                     films: Seq[Film])
val memento = new Film("Memento", 2000, 8.5)
val darkKnight = new Film("Dark Knight", 2008, 9.0)
val inception = new Film("Inception", 2010, 8.8)
val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7)
val outlawJoseyWales = new Film("The Outlaw Josey Wales", 1976, 7.9)
val unforgiven = new Film("Unforgiven", 1992, 8.3)
val granTorino = new Film("Gran Torino", 2008, 8.2)
val invictus = new Film("Invictus", 2009, 7.4)
val predator = new Film("Predator", 1987, 7.9)
val dieHard = new Film("Die Hard", 1988, 8.3)
val huntForRedOctober = new Film("The Hunt for Red October", 1990,
  7.6)
val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8)
val eastwood = new Director("Clint", "Eastwood", 1930,
  Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino,
    invictus))
val mcTiernan = new Director("John", "McTiernan", 1951,
  Seq(predator, dieHard, huntForRedOctober, thomasCrownAffair))
val nolan = new Director("Christopher", "Nolan", 1970,
  Seq(memento, darkKnight, inception))
val someGuy = new Director("Just", "Some Guy", 1990,
  Seq())
val directors = Seq(eastwood, mcTiernan, nolan, someGuy)
// TODO: Write your code here!

def profilicDirectors(numberOfFilms: Int): Seq[Director] = {
  directors.filter(_.films.length > numberOfFilms)
}

