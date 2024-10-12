package testfiles.TermApplyType.any.Inherited

import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import testfilesext.Foo

class SampleTest extends MockitoSugar with ArgumentMatchersSugar {

  private val foo = mock[Foo]

  def aMethod(): Unit = {
    when(foo.add(any[Foo])).thenReturn(Foo(5))
  }
}