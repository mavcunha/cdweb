package fs;

import org.junit.Test;
import play.test.UnitTest;

import java.io.File;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;

public class FilesTest extends UnitTest {

    @Test(expected = IllegalArgumentException.class)
    public void tryToAddANullAsAFileWillRaiseException() {
        new Files().add(null);
    }

    @Test
    public void shouldAcceptBeingUseOnForeach() {
        File file = mock(File.class);

        Files files = new Files();
        files.add(file);

        Iterator<File> iterator = files.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(file));
        assertThat(iterator.hasNext(), is(false));
    }


}
