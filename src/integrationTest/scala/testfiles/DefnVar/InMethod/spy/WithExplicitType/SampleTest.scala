package testfiles.DefnVar.InMethod.spy.WithExplicitType

import org.mockito.MockitoSugar.spy
import testfilesext.Foo

class SampleTest {

  def aMethod(): Unit = {
    var foo: Foo = spy[Foo](Foo(2))
  }
}