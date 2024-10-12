package testfiles.TermApplyType.any.Imported

import org.mockito.ArgumentMatchersSugar.any
import org.mockito.MockitoSugar.{mock, when}
import testfilesext.Foo

class SampleTest {

  private val foo = mock[Foo]

  def aMethod(): Unit = {
    when(foo.add(any[Foo])).thenReturn(Foo(5))
  }
}