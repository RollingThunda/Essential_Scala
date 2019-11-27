//FILTERING
for(x <- Seq(-2, -1, 0, 1, 2) if x > 0) yield x

//PARALLEL ITERATION
for {
  x <- Seq(1, 2, 3)
  y <- Seq(4, 5, 6)
} yield x + y
// res1: Seq[Int] =

//If we to do each pair, write:
for {
  (a, b) <- Seq(1, 2, 3).zip(Seq(4, 5, 6))
} yield a + b

//Note the pattern matching ability

//INTERMEDIATE RESULTS
for {
  x <- Seq(1, 2, 3)
  square = x * x
  y <- Seq(4, 5, 6)
} yield square * y

