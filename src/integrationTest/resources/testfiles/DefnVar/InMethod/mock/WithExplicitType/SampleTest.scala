package dummy

import org.mockito.MockitoSugar.mock

class SampleTest {

  def aMethod() {
    var foo: Foo = mock[Foo]
  }
}