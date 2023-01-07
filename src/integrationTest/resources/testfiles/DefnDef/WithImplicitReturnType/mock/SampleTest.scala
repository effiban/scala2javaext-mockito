package dummy

import org.mockito.MockitoSugar.mock

class SampleTest {

  def newMock() = mock[Foo]
}