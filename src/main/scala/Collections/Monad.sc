//A monad is a generic type that allows us to sequence computations while abstracting away some technicality
//In FOR COMPREHENSION the code hidder in the monad's MAP and FLATMAP.

//EXAMPLES
for {
  a <- getFirstNumber // getFirstNumber returns Option[Int]
  b <- getSecondNumber // getSecondNumber returns Option[Int]
} yield a + b
// The final result is an Option[Int]---the result of
// applying `+` to `a` and `b` if both values are present

for {
  a <- getFirstNumbers // getFirstNumbers returns Seq[Int]
  b <- getSecondNumbers // getSecondNumbers returns Seq[Int]
} yield a + b
// The final result is a Seq[Int]---the results of
// applying `+` to all combinations of `a` and `b`

for {
  a <- getFirstNumber // getFirstNumber returns Future[Int]
  b <- getSecondNumber // getSecondNumber returns Future[Int]
} yield a + b
// The final result is a Future[Int]---a data structure
// that will eventually allow us to access the result of
// applying `+` to `a` and `b`=


//Note that the code looks the same regardless of the monad

