//7.7.1 Context Bounds
//When we use type classes we often end up requiring implicit parameters that we pass to a type class interface.
//For example, using HtmlWriter example with a page template

trait HtmlWriter[A] {
  def toHtml(in: A): String
}
object HtmlWriter {
  def apply[A] (implicit writer: HtmlWriter[A]): HtmlWriter[A] = writer
}

implicit class HtmlOps[T](data: T) {
  def toHtml( implicit writer: HtmlWriter[T]): String =
    writer.toHtml(data)
}

def pageTemplateFoo[A](body: A)(implicit writer: HtmlWriter[A]): String = {
  val renderedBody = body.toHtml
  s"<html><head>...</head><body>${renderedBody}</body></html>"
}


//We don't explicitly use the implicit writer in our code, but we need it in the scope
//so the compiler can insert it for the toHtml enrichment
//Context bounds allow us to write this more compactly

def pageTemplate[A: HtmlWriter](body: A): String = {
  val renderedBody = body.toHtml
  s"<html><head>...</head><body>${renderedBody}</body></html>"
}

//7.2.2 Implicitly
//Normally we use context bounds when we don't need explicit access to the implicit
//parameter. If do, however, we can use the implicitly method:
case class Example(name: String)
implicit val implicitExample = Example("implicit")

implicitly[Example] == implicitExample

