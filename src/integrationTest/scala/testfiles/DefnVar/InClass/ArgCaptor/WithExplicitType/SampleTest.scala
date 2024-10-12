package testfiles.DefnVar.InClass.ArgCaptor.WithExplicitType

import org.mockito.captor.{ArgCaptor, Captor}
import testfilesext.Foo

class SampleTest {

  private var fooCaptor: Captor[Foo] = ArgCaptor[Foo]
}