package dummy

import org.mockito.MockitoSugar.spy

class SampleTest {

  def aMethod() {
    var foo: Foo = spy[Foo](new Foo(2))
  }
}