package testfiles.DefnVal.InClass.spy.WithExplicitType.Imported

import org.mockito.MockitoSugar.spy
import testfilesext.Foo

class SampleTest {

  private val x: Foo = spy[Foo](Foo(2))
}