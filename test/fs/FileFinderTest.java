package fs;

import org.junit.Test;
import play.test.UnitTest;

import java.io.File;
import java.util.Iterator;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileFinderTest extends UnitTest {

    @Test
    public void shouldReturnFilesFoundGivenAPattern() {
        FileFinder ff = new FileFinder(new File("test/data"));

        Files files = ff.find(Pattern.compile(".*\\.rtf$"));

        Iterator<File> iterator = files.iterator();
        Iterator<File> expected = testFiles().iterator();

        while(iterator.hasNext() || expected.hasNext()) {
            assertThat(iterator.next(),is(expected.next()));
        }

    }

    @Test
    public void returnAEmptyFilesIfNoFilesCanBeFound() {
        FileFinder ff = new FileFinder(new File("test/data"));
        for(File f : ff.find(Pattern.compile("^$"))) {
            fail("Wrongly found file " + f.getAbsolutePath());
        }
    }

    @Test
    public void shouldNotGiveANPEIfListReturnsNull() {
        final File baseDir = mock(File.class);

        when(baseDir.isDirectory()).thenReturn(true);
        when(baseDir.list()).thenReturn(null);
        when(baseDir.isFile()).thenReturn(false);

        FileFinder ff = new FileFinder(baseDir);
        ff.find(Pattern.compile(".*"));
    }

    private Files testFiles() {
        Files files = new Files();
        files.add(new File("test/data/file1.rtf"));
        files.add(new File("test/data/file2.rtf"));
        return files;
    }
}
