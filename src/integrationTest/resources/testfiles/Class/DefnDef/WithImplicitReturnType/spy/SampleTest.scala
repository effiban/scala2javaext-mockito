package dummy

import org.mockito.MockitoSugar.spy

class SampleTest {

  def newSpy() = spy[Foo]
}