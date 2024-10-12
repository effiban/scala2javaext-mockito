package testfiles.TermApply.eqTo.Imported

import org.mockito.ArgumentMatchersSugar.eqTo
import org.mockito.MockitoSugar.when
import testfilesext.Foo

class SampleTest {

  def aMethod(): Unit = {
    val foo: Foo = Foo(2)
    when(foo.appendTo(eqTo("bar"))).thenReturn("zoo")
  }
}