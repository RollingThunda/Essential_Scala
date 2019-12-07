//Scala has special syntax for working with collections (any class that implements map and flatMap)
//that makes complicated operations with many nested transformations simpler to write.

Seq(1, 2, 3).map(_ * 2)
//can be written:
for {
  x <- Seq(1,2,3)
} yield x * 2
//expression containing <- is a GENERATOR, with a PATTERN on the LHS and a GENERATOR EXPRESSION on the RHS.
//A FOR COMPREHENSION iterates over the elements in the generator, binding each element to the pattern and calling
//the YIELD expression.

//say we want to double and flatten a seq of seq of numbers
val data = Seq(Seq(1), Seq(2, 3), Seq(4, 5, 6))
data.flatMap(_.map(_ * 2))
//can be hard to read. Instead:
for {
  subseq <- data
  element <- subseq
} yield element * 2

//EXERCISES
//see "Working with Sequences.sc"