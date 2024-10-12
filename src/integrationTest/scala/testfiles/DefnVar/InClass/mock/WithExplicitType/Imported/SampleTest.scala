package testfiles.DefnVar.InClass.mock.WithExplicitType.Imported

import org.mockito.MockitoSugar.mock
import testfilesext.Foo
class SampleTest {

  private var x: Foo = mock[Foo]
}