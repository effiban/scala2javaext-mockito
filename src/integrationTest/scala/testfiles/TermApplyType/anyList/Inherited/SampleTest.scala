package testfiles.TermApplyType.anyList.Inherited

import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import testfilesext.Foo

class SampleTest extends MockitoSugar with ArgumentMatchersSugar {

  private val foo: Foo = mock[Foo]

  def aMethod(): Unit = {
    when(foo.appendTo(anyList[Int])).thenReturn(List(1, 2))
  }
}