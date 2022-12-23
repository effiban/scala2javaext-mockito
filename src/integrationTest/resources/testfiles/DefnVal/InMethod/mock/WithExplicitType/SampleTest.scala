package dummy

import org.mockito.MockitoSugar.mock

class SampleTest {

  def aMethod() {
    val foo: Foo = mock[Foo]
  }
}