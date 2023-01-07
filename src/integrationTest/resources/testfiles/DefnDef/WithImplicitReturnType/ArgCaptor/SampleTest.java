package dummy;

import java.io.*;
import java.lang.*;
import java.math.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.mockito.*;
import org.mockito.ArgumentCaptor;

@RunWith(MockitoJUnitRunner.class)
public class SampleTest {

    public SampleTest() {
    }

    public ArgumentCaptor<Foo> newCaptor() {
        return ArgumentCaptor.forClass(Foo.class);
    }
}