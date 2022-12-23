package dummy

import org.mockito.captor.{Captor, ArgCaptor}

class SampleTest {

  def aMethod() {
    val fooCaptor: Captor[Foo] = ArgCaptor[Foo]
  }
}