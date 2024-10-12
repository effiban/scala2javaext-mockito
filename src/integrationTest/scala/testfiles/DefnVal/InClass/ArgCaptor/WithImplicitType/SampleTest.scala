package testfiles.DefnVal.InClass.ArgCaptor.WithImplicitType

import org.mockito.captor.ArgCaptor
import testfilesext.Foo

class SampleTest {

  private val fooCaptor = ArgCaptor[Foo]
}