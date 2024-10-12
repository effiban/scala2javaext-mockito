package testfiles.TermApply.eqTo.Inherited

import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import testfilesext.Foo

class SampleTest extends ArgumentMatchersSugar with MockitoSugar {

  def aMethod(): Unit = {
    val foo: Foo = Foo(2)
    when(foo.appendTo(eqTo("bar"))).thenReturn("zoo")
  }
}