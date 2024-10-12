package testfiles.DefnVal.InClass.ArgCaptor.WithExplicitType

import org.mockito.captor.{ArgCaptor, Captor}
import testfilesext.Foo

class SampleTest {

  private val fooCaptor: Captor[Foo] = ArgCaptor[Foo]
}