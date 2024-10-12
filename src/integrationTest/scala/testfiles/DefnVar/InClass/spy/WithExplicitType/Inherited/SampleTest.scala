package testfiles.DefnVar.InClass.spy.WithExplicitType.Inherited

import org.mockito.MockitoSugar
import testfilesext.Foo

class SampleTest extends MockitoSugar{

  private var x: Foo = spy[Foo](new Foo(2))
}