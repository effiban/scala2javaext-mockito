package dummy

import org.mockito.captor.{Captor, ArgCaptor}

class SampleTest {

  private var fooCaptor: Captor[Foo] = ArgCaptor[Foo]
}