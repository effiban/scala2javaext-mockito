package testfiles.DefnVar.InMethod.mock.WithExplicitType

import org.mockito.MockitoSugar.mock
import testfilesext.Foo

class SampleTest {

  def aMethod(): Unit = {
    var foo: Foo = mock[Foo]
  }
}