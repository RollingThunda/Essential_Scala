//WE will express is-a and has-a relationships. In the terminology of functional programming, we are learning about
//SUM and PRODUCT types, which together are called ALGEBRAIC DATA

//Our goal in this section is to see how to translate a data model into Scala code.

//PRODUCT TYPE PATTERN
//Our first pattern is to model data that contains other data. We might describe this as "A has a B and C".
//The way we writ this is to use a case class. We've already done this, but now we formalising it:
/*
case class A(b: B, c: C)

trait A {
  def b: B
  def c: C
}


//SUM TYPE PATTERN
sealed trait A
final case class B() extends A
final case class C() extends A
 */