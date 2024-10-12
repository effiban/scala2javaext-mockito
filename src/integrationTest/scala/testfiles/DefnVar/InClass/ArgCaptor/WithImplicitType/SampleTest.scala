package testfiles.DefnVar.InClass.ArgCaptor.WithImplicitType

import org.mockito.captor.{ArgCaptor, Captor}
import testfilesext.Foo

class SampleTest {

  private var fooCaptor = ArgCaptor[Foo]
}