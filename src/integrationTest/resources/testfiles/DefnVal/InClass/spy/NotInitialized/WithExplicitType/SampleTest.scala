package dummy

import org.mockito.MockitoSugar.spy

class SampleTest {

  private val x: Foo = spy[Foo]
}