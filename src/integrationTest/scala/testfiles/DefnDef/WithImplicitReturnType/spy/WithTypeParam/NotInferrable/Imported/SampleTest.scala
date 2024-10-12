package testfiles.DefnDef.WithImplicitReturnType.spy.WithTypeParam.NotInferrable.Imported

import org.mockito.MockitoSugar.spy
import testfilesext.Foo

class SampleTest {

  private def newSpy() = {
    val foo: Foo = new Foo()
    spy[Foo](foo)
  }
}