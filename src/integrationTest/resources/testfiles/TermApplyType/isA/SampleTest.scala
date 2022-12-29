package dummy

import org.mockito.ArgumentMatchersSugar.isA
import org.mockito.MockitoSugar.when

class SampleTest {

  def aMethod() {
    when(call(isA[Foo])).thenReturn("bar")
  }
}