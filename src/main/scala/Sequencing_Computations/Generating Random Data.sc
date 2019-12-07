//RANDOM WORDS
//Imagine we wanted to generate realistic text. We will use a Markov Chain model

val subjects = List("Noel", "The cat", "The dog")
val verbs = List("wrote", "chased", "slept on")
val objects = List("the book", "the ball", "the bed")

val permutations = for{
  subject <- subjects
  verb <- verbs
  obj <- objects
} yield s"${subject} ${verb} ${obj}"

//This