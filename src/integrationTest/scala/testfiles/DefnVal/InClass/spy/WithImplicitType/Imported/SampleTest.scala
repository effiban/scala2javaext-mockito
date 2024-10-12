package testfiles.DefnVal.InClass.spy.WithImplicitType.Imported

import org.mockito.MockitoSugar.spy
import testfilesext.Foo

class SampleTest {

  private val x = spy[Foo](Foo(2))
}