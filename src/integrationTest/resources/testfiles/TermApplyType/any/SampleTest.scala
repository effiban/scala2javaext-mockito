package dummy

import org.mockito.ArgumentMatchersSugar.any
import org.mockito.MockitoSugar.when

class SampleTest {

  def aMethod() {
    when(call(any[Foo])).thenReturn("bar")
  }
}