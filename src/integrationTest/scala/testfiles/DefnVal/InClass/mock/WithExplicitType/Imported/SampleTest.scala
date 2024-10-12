package testfiles.DefnVal.InClass.mock.WithExplicitType.Imported

import org.mockito.MockitoSugar.mock
import testfilesext.Foo
class SampleTest {

  private val x: Foo = mock[Foo]
}