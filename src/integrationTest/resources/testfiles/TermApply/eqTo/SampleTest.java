package dummy;

import java.io.*;
import java.lang.*;
import java.math.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.mockito.*;
import org.mockito.ArgumentMatchersSugar.eqTo;
import org.mockito.MockitoSugar.when;

@RunWith(MockitoJUnitRunner.class)
public class SampleTest {

    public SampleTest() {
    }

    public void aMethod() {
        when(foo.getBar(eq("bar")))
            .thenReturn("zoo");
    }
}