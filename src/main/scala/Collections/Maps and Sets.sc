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

