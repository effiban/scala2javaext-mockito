package testfilesext

case class Foo(x: Int) {
  def this() = this(2)

  def appendTo(str: String): String = str + x
  def appendTo(list: List[Int]): List[Int] = list :+ x

  def add(other: Foo): Foo = Foo(x + other.x)
}
