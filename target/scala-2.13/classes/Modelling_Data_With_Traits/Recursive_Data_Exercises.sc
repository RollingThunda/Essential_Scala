//4.6.3.1 A List of Methods
sealed trait IntList {
  def length: Int =
    this match {
      case End => 0
      case Pair(head, tail) => 1 + tail.length
    }

  def product: Int =
    this match {
      case End => 1
      case Pair(head, tail) => head * tail.product
    }

  def double: IntList =
    this match {
      case End => End
      case Pair(head, tail) => Pair(head * 2, tail.double)
    }
}
case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList



val example = Pair(1, Pair(2, Pair(3, End)))
assert(example.length == 3)
assert(example.tail.length == 2)
assert(End.length == 0)

assert(example.product == 6)
assert(example.tail.product == 6)
assert(End.product == 1)

assert(example.double == Pair(2, Pair(4, Pair(6, End))))
assert(example.tail.double == Pair(4, Pair(6, End)))
assert(End.double == End)

//4.6.3.2 The Forest of Trees
//pattern matching example
sealed trait Node {
  def sumTree: Int =
    this match {
      case Leaf(element) => element
      case Tree(left, right) => left.sumTree + right.sumTree
    }

  def double: Node =
    this match {
      case Leaf(element) => Leaf(element * 2)
      case Tree(left, right) => Tree(left.double, right.double)
    }
}
final case class Leaf(element: Int) extends Node
final case class Tree(left: Node, right: Node) extends Node

//doing it with polymorphism is like this:
/*
object TreeOps {
  def sum(tree: Tree): Int =
    tree match {
      case Leaf(elt) => elt
      case Node(l, r) => sum(l) + sum(r)
    }
  def double(tree: Tree): Tree =
    tree match {
      case Leaf(elt) => Leaf(elt * 2)
      case Node(l, r) => Node(double(l), double(r))
    }
}
sealed trait Tree {
  def sum: Int
  def double: Tree
}
final case class Node(l: Tree, r: Tree) extends Tree {
  def sum: Int =
    l.sum + r.sum
  def double: Tree =
    Node(l.double, r.double)
}
final case class Leaf(elt: Int) extends Tree {
  def sum: Int =
    elt
  def double: Tree =
    Leaf(elt * 2)
}
*/
//ugh

