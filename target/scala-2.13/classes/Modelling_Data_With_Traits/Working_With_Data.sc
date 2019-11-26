//We will now see pattern for using algebraic data types, known as STRUCTURAL RECURSION.
//Structural recursion says if we have A we must break it into its constituent B and C, that we combine in some way
//to get closer to our desired answer.

//Just as we have two patterns for building algebraic data types, we will have two patterns for decomposing them
//using structural recursion.

//STRUCTURAL RECURSION USING POLYMORPHISM (OO WAY)
//Polymorphism is a fundamental object-oriented technique. If we define a method in a trait, and have different
//implementations in classes extending that trait, when we call that method the implementation on the actual concrete
//instance will be used.
sealed trait A {
  def foo: String
}

final case class B() extends A {
  def foo: String  = "It's a B!"
}
final case class C() extends A {
  def foo: String = "It's a C!"
}
//We declare a value with type A but we see the concrete implementation on B or C used.
val anB: A = B()
anB.foo
val anC: A = C()
anC.foo

//WE can also define an implementation in a trait and change the implementation in extending class using override
sealed trait D {
  def foo: String  = "It's a D"
}
final case class E() extends D {
  override def foo: String = "It's an E"
}
final case class F() extends D {
  override def foo: String = "It's an F"
}

//Remember if you provide a default implementation in a trait, you must ensure that implementation is valid for all
//subtypes

//Product type polymorphism
case class A(b: B, c: C) {
  def f: F = ???
}

//Sum type polymorphism
sealed trait A {
  def f: F
}
final case class B() extends A {
  def f: F = ???
}
final case class C() extends A {
  def f: F = ???
}

//STRUCTURAL RECURSION USING PATTERN MATCHING (FUNCTIONAL WAY)
//Product Type Pattern Matching
def f(a: A): F = a match {
  case A(b,c) => ???
}

//Sum Type Pattern Matching
def f(a: A): F = a match {
  case B() => ???
  case C() => ???
}

//CONCRETE EXAMPLE
//A Feline is a Lion, Tiger, Panther, or Cat.
