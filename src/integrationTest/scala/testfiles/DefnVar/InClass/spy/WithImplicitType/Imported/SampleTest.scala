package testfiles.DefnVar.InClass.spy.WithImplicitType.Imported

import org.mockito.MockitoSugar.spy
import testfilesext.Foo

class SampleTest {

  private var x = spy[Foo](new Foo(2))
}