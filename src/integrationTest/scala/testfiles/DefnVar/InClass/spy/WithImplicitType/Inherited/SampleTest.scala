package testfiles.DefnVar.InClass.spy.WithImplicitType.Inherited

import org.mockito.MockitoSugar
import testfilesext.Foo

class SampleTest extends MockitoSugar {

  private var x = spy[Foo](new Foo(2))
}