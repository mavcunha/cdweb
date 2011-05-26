package fs;

import org.junit.Test;
import org.mockito.internal.util.reflection.FieldSetter;
import play.test.UnitTest;

import java.io.File;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

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

    @Test
    public void shouldRetrieveInSameOrder() {
        File file1 = mock(File.class);
        File file2 = mock(File.class);

        Files files = new Files();
        files.add(file1);
        files.add(file2);

        Iterator<File> iterator = files.iterator();
        assertThat(iterator.next(),is(file1));
        assertThat(iterator.next(),is(file2));
    }

    @Test
    public void emptyFilesObjecReturnsIteratorWithoutItems() {
        Files files = new Files();
        assertThat(files.iterator().hasNext(),is(false));
    }

    @Test
    public void iteratorNotEmptyForNonEmptyFilesObject() {
        Files files = new Files();
        files.add(new File("some_file"));
        assertThat(files.iterator().hasNext(),is(true));
    }
}
