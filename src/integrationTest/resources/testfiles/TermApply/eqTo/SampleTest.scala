package dummy

import org.mockito.ArgumentMatchersSugar.eqTo
import org.mockito.MockitoSugar.when

class SampleTest {

  def aMethod() {
    when(foo.getBar(eqTo("bar"))).thenReturn("zoo")
  }
}