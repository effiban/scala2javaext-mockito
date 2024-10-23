package testfiles.DefnVal.InClass.mock.WithExplicitType.Inherited.FromOuterClass

import org.mockito.MockitoSugar
import testfilesext.Foo

class SampleTest extends MockitoSugar {

  class SampleTestInner {
    private val x: Foo = mock[Foo]
  }
}