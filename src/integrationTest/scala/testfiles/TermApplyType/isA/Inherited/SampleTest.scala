package testfiles.TermApplyType.isA.Inherited

import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import testfilesext.Foo

class SampleTest extends MockitoSugar with ArgumentMatchersSugar {

  private val foo = mock[Foo]

  def aMethod(): Unit = {
    when(foo.add(isA[Foo])).thenReturn(Foo(3))
  }
}