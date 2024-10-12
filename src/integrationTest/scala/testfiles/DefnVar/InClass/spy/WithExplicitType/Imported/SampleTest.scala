package testfiles.DefnVar.InClass.spy.WithExplicitType.Imported

import org.mockito.MockitoSugar.spy
import testfilesext.Foo

class SampleTest {

  private var x: Foo = spy[Foo](new Foo(2))
}