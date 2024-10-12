package testfiles.DefnVar.InClass.mock.WithImplicitType.Imported

import org.mockito.MockitoSugar.mock
import testfilesext.Foo

class SampleTest {

  private var x = mock[Foo]
}