package dummy;

import java.io.*;
import java.lang.*;
import java.math.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.mockito.*;
import org.mockito.ArgumentMatchers.isA;
import org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SampleTest {

    public SampleTest() {
    }

    public void aMethod() {
        when(call(isA(Foo.class)))
            .thenReturn("bar");
    }
}