package testfiles.DefnVal.InMethod.mock.WithExplicitType

import org.mockito.MockitoSugar.mock
import testfilesext.Foo

class SampleTest {

  def aMethod(): Unit = {
    val foo: Foo = mock[Foo]
  }
}