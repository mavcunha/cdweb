package fs;

import org.junit.Test;
import play.test.UnitTest;

import java.io.File;
import java.util.Iterator;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileFinderTest extends UnitTest {

    @Test
    public void shouldReturnFilesFoundGivenAPattern() {
        FileFinder ff = new FileFinder(new File("test/data"), Pattern.compile(".*\\.rtf$"));

        Files files = ff.find();

        Iterator<File> iterator = files.iterator();
        Iterator<File> expected = testFiles().iterator();

        while(iterator.hasNext() || expected.hasNext()) {
            assertThat(iterator.next(),is(expected.next()));
        }

    }

    @Test
    public void returnAEmptyFilesIfNoFilesCanBeFound() {
        FileFinder ff = new FileFinder(new File("test/data"), Pattern.compile("^$"));
        for(File f : ff.find()) {
            fail("Wrongly found file " + f.getAbsolutePath());
        }
    }

    @Test
    public void shouldNotGiveANPEIfListReturnsNull() {
        final File file = mock(File.class);

        when(file.isDirectory()).thenReturn(true);
        when(file.list()).thenReturn(null);
        when(file.isFile()).thenReturn(false);

        FileFinder ff = new FileFinder(file, Pattern.compile(".*"));
        ff.find();
    }

    private Files testFiles() {
        Files files = new Files();
        files.add(new File("test/data/file1.rtf"));
        files.add(new File("test/data/file2.rtf"));
        return files;
    }
}
