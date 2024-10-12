package testfiles.DefnVal.InMethod.ArgCaptor.WithExplicitType

import org.mockito.captor.{ArgCaptor, Captor}
import testfilesext.Foo

class SampleTest {

  def aMethod(): Unit = {
    val fooCaptor: Captor[Foo] = ArgCaptor[Foo]
  }
}