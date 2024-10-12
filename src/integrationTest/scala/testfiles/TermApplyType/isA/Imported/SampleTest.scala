package testfiles.TermApplyType.isA.Imported

import org.mockito.ArgumentMatchersSugar.isA
import org.mockito.MockitoSugar.{mock, when}
import testfilesext.Foo

class SampleTest {

  private val foo = mock[Foo]

  def aMethod(): Unit = {
    when(foo.add(isA[Foo])).thenReturn(Foo(3))
  }
}