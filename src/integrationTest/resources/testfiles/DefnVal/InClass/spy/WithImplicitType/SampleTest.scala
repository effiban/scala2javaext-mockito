package dummy

import org.mockito.MockitoSugar.spy

class SampleTest {

  private val x = spy[Foo](new Foo(2))
}