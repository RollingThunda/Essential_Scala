//Blocks
{
  println("This is a side-effect")
  println("This is also a side-effect")
  3
}

//We can also use a block when we want to name intermediate results
def name: String = {
  val title = "Professor"
  val name = "Farnsworth"
  title + " " + name
}
name

//what type is this?
if (1> 2) "alien" else "predator" //String
if (1 > 2) "alien" else 2001 //Any is the "grand supper type" of Scala
if (false) "hello" //else returns Unit if undefined

