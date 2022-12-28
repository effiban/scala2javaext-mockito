package dummy;

import java.io.*;
import java.lang.*;
import java.math.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.mockito.*;
import org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SampleTest {

    public SampleTest() {
    }

    public void aMethod() {
        when(call(any(Foo.class)))
            .thenReturn("bar");
    }
}