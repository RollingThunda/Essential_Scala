//A second kind of type class interface, call TYPE ENRICHMENT allows us to create interfaces that act as if they were
//methods defined on the classes of interest.
//Spose we had:
def numberOfVowels(str: String) =
  str.filter(Seq('a', 'e', 'i', 'o', 'u').contains(_)).length

numberOfVowels("the quick brown fox")

//Since this is a frequently used method, it would be nice if it was a built-in
//method of String.

//7.5.1 Implicit Classes
//We can wrap String in a class that adds our method:
implicit class ExtraStringMethods(str: String) {
  val vowels = Seq('a', 'e', 'i', 'o', 'u')
  def numberOfVowels =
    str.toList.filter(vowels contains _).length
}

new ExtraStringMethods("the quick brown fox").numberOfVowels

//Writing new ExtraStringMethods every time will get unwieldy
//However, if we tag our class with implicit, we inset the constructor call automatically
"The quick brown fox".numberOfVowels

