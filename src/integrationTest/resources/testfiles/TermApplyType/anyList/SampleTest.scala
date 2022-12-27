package dummy

import org.mockito.ArgumentMatchersSugar.anyList
import org.mockito.MockitoSugar.when

class SampleTest {

  def aMethod() {
    when(call(anyList[Foo])).thenReturn("bar")
  }
}