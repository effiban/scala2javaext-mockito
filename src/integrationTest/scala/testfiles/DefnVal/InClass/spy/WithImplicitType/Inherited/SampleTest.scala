package testfiles.DefnVal.InClass.spy.WithImplicitType.Inherited

import org.mockito.MockitoSugar
import testfilesext.Foo

class SampleTest extends MockitoSugar {

  private val x = spy[Foo](Foo(2))
}