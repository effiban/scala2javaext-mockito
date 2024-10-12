package testfiles.DefnVal.InMethod.spy.WithExplicitType

import org.mockito.MockitoSugar.spy
import testfilesext.Foo

class SampleTest {

  def aMethod(): Unit = {
    val foo: Foo = spy[Foo](Foo(2))
  }
}