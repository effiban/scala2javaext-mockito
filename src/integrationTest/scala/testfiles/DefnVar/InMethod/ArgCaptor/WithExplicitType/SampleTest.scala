package testfiles.DefnVar.InMethod.ArgCaptor.WithExplicitType

import org.mockito.captor.{ArgCaptor, Captor}
import testfilesext.Foo

class SampleTest {

  def aMethod(): Unit = {
    var fooCaptor: Captor[Foo] = ArgCaptor[Foo]
  }
}