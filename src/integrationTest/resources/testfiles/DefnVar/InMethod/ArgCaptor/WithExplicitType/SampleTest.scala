package dummy

import org.mockito.captor.{Captor, ArgCaptor}

class SampleTest {

  def aMethod() {
    var fooCaptor: Captor[Foo] = ArgCaptor[Foo]
  }
}