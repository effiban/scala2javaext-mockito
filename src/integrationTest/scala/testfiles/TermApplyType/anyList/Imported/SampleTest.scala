package testfiles.TermApplyType.anyList.Imported

import org.mockito.ArgumentMatchersSugar.anyList
import org.mockito.MockitoSugar.{mock, when}
import testfilesext.Foo

class SampleTest {

  private val foo: Foo = mock[Foo]

  def aMethod(): Unit = {
    when(foo.appendTo(anyList[Int])).thenReturn(List(1, 2))
  }
}