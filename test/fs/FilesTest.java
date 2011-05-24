package fs;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JMock.class)
public class FilesTest {

    private final Mockery context = new JUnit4Mockery(){{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    @Test(expected = IllegalArgumentException.class)
    public void tryToAddANullAsAFileWillRaiseException() {
        new Files().add(null);
    }

    @Test
    public void shouldAcceptBeingUseOnForeach() {
        File file = context.mock(File.class);

        Files files = new Files();
        files.add(file);

        Iterator<File> iterator = files.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(file));
        assertThat(iterator.hasNext(), is(false));
    }


}
