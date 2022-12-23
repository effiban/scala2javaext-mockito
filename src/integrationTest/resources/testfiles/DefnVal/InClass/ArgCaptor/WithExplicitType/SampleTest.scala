package dummy

import org.mockito.captor.{Captor, ArgCaptor}

class SampleTest {

  private val fooCaptor: Captor[Foo] = ArgCaptor[Foo]
}