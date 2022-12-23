package dummy

import org.mockito.MockitoSugar.spy

class SampleTest {

  def aMethod() {
    val foo: Foo = spy[Foo]
  }
}