//Consider Adder class from last chapter
//Adder is an object representing a computation
//Like having a method we can pass as a value
//These objects are called functions, and are the basis of functional programming

//Function Application Syntax
//By convention, an object can be "called" like a function if it has a method called apply.
//This allows shorthand, foo.apply(args) === foo(args)

class Adder(amount: Int) {
  def apply(in: Int): Int = in + amount
}

val add3 = new Adder(3)
add3.apply(2)
add3(2)

//At the moment we can define a class ADDer to capture the idea of adding to a number,
//but that code isn't properly portable across codebases
//Later on we will see how Scala copes with this by defining a generic set of function
//types that we can use in a variety of situations

