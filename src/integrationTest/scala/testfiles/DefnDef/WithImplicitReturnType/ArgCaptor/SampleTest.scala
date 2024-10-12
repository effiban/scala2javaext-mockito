package testfiles.DefnDef.WithImplicitReturnType.ArgCaptor

import org.mockito.captor.{ArgCaptor, Captor}
import testfilesext.Foo

class SampleTest {

  def newCaptor(): Captor[Foo] = ArgCaptor[Foo]
}