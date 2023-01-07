package dummy

import org.mockito.captor.ArgCaptor

class SampleTest {

  def newCaptor() = ArgCaptor[Foo]
}