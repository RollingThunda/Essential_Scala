import scala.collection.immutable.Seq
//now we will see how FP allows us to process sequences in a terse and declarative style

//MAP
val sequence = Seq(1, 2, 3)
sequence.map(elt => elt * 2)
sequence.map(_ * 2)

"DOG".permutations.toList

//FLATMAP
Seq("A", "WET", "DOG").flatMap(_.permutations.toList)

//FOLDS
//folds exist as either right or left folds

//FOREACH (dirty)
//for each does not return a useful result - we use it purely for its side-effect

//ALGEBRA OF SEQUENCES
/*
        We provide
Seq[A]    ||    A => Unit        ||    foreach
Seq[A]    ||    A => B           ||    map
Seq[A]    ||    A => Seq[B]      ||    flatmap
Seq[A]    ||    B; (B, A) => B   ||    foldLeft
Seq[A]    ||    B; (A, B) => B   ||    foldRight

 */

//EXERCISES
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


//my code here
val nolanFilms = nolan.films.map(_.name)
val cinephile = directors.flatMap(_.films.map(_.name))
val vintageMc = mcTiernan.films.sortWith(_.yearOfRelease < _.yearOfRelease).headOption
val highScores = directors.flatMap(_.films).sortWith((a, b) => a.imdbRating > b.imdbRating)
val avgScores = directors.map(d => d.films.foldLeft(0.0)((s, f) => s + f.imdbRating)/d.films.length)
val earliest = directors.flatMap(d => d.films).sortWith(_.yearOfRelease < _.yearOfRelease).headOption
