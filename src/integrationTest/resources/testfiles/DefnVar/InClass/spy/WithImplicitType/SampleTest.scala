package dummy

import org.mockito.MockitoSugar.spy

class SampleTest {

  private var x = spy[Foo](new Foo(2))
}