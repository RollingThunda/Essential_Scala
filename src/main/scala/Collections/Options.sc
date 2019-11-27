//options represent values that may or may not be present in our code. They are an alternative to
//using null. Scala has a built-in Option type, with sub-types Some and None

//Although map and flatMap don't allow us to extract values from Options, they allow us to compose computations
//together in a safe manner

//OPTIONS AS FLOW CONTROL
//Because Option supports map and flatMap, it also works with for comprehensions.
def readInt(str: String): Option[Int] =
  if(str matches "-?\\d+") Some(str.toInt) else None


val optionA = readInt("123")
val optionB = readInt("234")
for {
  a <- optionA
  b <- optionB
} yield a + b

//EXERCISES
//6.5.1.1 Adding Things
def addOptions(x: Option[Int], y: Option[Int]) = for {
  a <- x
  b <- y
} yield a + b

//6.5.1.2 Adding All Things
def addOptions(opt1: Option[Int], opt2: Option[Int], opt3: Option[Int]) = for {
  a <- opt1
  b <- opt2
  c <- opt3
} yield a + b + c

//6.5.1.3 Another Short Divison
def divide(num: Int, den: Int): Option[Double] = {
  den match {
    case 0 => None
    case _ => Some(num/den)
  }
}

def divideOptions(opt1: Option[Int], opt2: Option[Int]) = for {
  num <- opt1
  den <- opt2
} yield divide(num, den)

divideOptions(Option(1), Option(0))
