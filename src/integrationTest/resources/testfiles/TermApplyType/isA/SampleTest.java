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

@ExtendWith(MockitoExtension.class)
public class SampleTest {

    public void aMethod() {
        when(call(isA(Foo.class)))
            .thenReturn("bar");
    }
}