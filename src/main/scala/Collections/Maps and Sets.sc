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
//Maps, like Seq extend the Traversable trait