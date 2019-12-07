//A (big M) Map is a collection that maps KEYS to VALUES.
val example = Map("a" -> 1, "b" -> 2, "c" -> 3)

//ACCESSING VALUES
//use apply or get
example("a")
example.get("a")

//apply will crash if it attempts to look up a non-existent key
example.get("d")

//DETERMINE MEMBERSHIP
example.contains("a")

//SIZE
example.size

//ADDING
example++Map("c" -> 10, "d" -> 11, "e" -> 12)
example--Seq("b", "c")
//for single arguments:
example + ("d" -> 4) - "c"

//SORTED MAPS
scala.collection.immutable.ListMap("a" -> 1) + ("b" -> 2) + ("c" -> 3) + ("d" -> 4) + ("e" -> 5)
//methods on ordered and unordered maps are almost identical, but performance may vary

//MAP AND FLATMAP
//Maps, like Seq extend the Traversable trait, meaning they inherit the standard amp and
//flatMap methods.
//
example.map(pair => pair._1 -> pair._2 * 2)
//Returns a Map as expected. But what happens when a function doesn't return a pair?
example.map(pair => pair._1 + "=" + pair._2)
//We get an iterable. A more complicated example:
example.flatMap {
  case (str, num) =>
    (1 to 3).map(x => (str + x) -> (num * x))
}
//Returns a Map, as flatMap returns a sequence of pairs.
//If it didn't, we'd get a more generic data type :
for{
  (str, num) <- example
  x <- 1 to 3
} yield (x + str) + "=" + (x * num)

//SETS
//Sets are unordered collections that contain no duplicate elements.

//Exercises
//6.8.3.1 Favourites
val people = Set(
  "Alice",
  "Bob",
  "Charlie",
  "Derek",
  "Edith",
  "Fred")

val ages = Map(
  "Alice" -> 20,
  "Bob" -> 30,
  "Charlie" -> 50,
  "Derek" -> 40,
  "Edith" -> 10,
  "Fred" -> 60)

val favouriteColours = Map(
  "Bob" -> "green",
  "Derek" -> "magenta",
  "Fred" -> "yellow")

val favouriteLolcats = Map(
  "Alice" -> "Long Cat",
  "Charlie" -> "Ceiling Cat",
  "Edith" -> "Cloud Cat")

def favouriteColour(person: String): String = {
  favouriteColours.getOrElse(person, "beige")
}

def printColours() = for {
  person <- people
} println(s"${person}'s favorite color is ${favouriteColour(person)}!")

printColours()

def loookUp[A](person: String, map: Map[String, A]): Option[A] = {
  map.get(person)
}

val oldest: Option[String] = people.foldLeft(Option.empty[String]) { (older, person) =>
  if (ages.getOrElse(person, 0) > older.flatMap(ages.get).getOrElse(0))
    {
      Some(person)
    } else {
    older
  }
}

val favourite: Option[String] =
  for {
    oldest <- oldest
    colour <- favouriteColours.get(oldest)
  } yield colour

//6.8.4 DIY
//6.8.4.1 Union of Sets
def union[A](first: Set[A], second: Set[A]): Set[A] = {
  first.foldLeft(second) { (set, element) =>
    set + element
  }
}

//6.8.4.2 Union of Maps
def union_map[A](first: Map[A, Int], second: Map[A, Int]): Map[A, Int] = {
  first.foldLeft(second) { (map, element) =>
    val (key, value) = element
    val total = value + map.get(key).getOrElse(0)
    map + (key -> total)
  }
}

val map1 = Map('a' -> 1, 'b' -> 2)
val map2 = Map('a' -> 3, 'b' -> 6, 'c' -> 9)

union_map(map1, map2)

//6.8.4.3 Generic Union
def union_gen[A, B](first: Map[A, B], second: Map[A, B], fn: (B, B) => B, base: B): Map[A, B] = {
  first.foldLeft(second) { (map, element) =>
    val (key, value) = element
    val total = fn(value,  map.get(key).getOrElse(base))
    map + (key -> total)
  }
}