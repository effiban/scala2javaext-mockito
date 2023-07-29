package dummy;

import java.io.*;
import java.lang.*;
import java.math.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.mockito.*;
import org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
public class SampleTest {

    public void aMethod() {
        Foo foo = spy(new Foo(2));
    }
}