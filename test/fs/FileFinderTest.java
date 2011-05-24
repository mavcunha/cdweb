package fs;

import org.junit.Test;

import java.io.File;
import java.util.Iterator;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class FileFinderTest {
    @Test
    public void shouldReturnFilesFoundGivenAPattern() {
        FileFinder ff = new FileFinder("test/data", Pattern.compile(".*\\.rtf$"));

        Files files = ff.find();

        Iterator<File> iterator = files.iterator();
        Iterator<File> expected = testFiles().iterator();

        while(iterator.hasNext() || expected.hasNext()) {
            assertThat(iterator.next(),is(expected.next()));
        }

    }

    @Test
    public void returnAEmptyFilesIfNoFilesCanBeFound() {
        FileFinder ff = new FileFinder("test/data", Pattern.compile("^$"));
        for(File f : ff.find()) {
            fail("Wrongly found file " + f.getAbsolutePath());
        }
    }

    private Files testFiles() {
        Files files = new Files();
        files.add(new File("test/data/file1.rtf"));
        files.add(new File("test/data/file2.rtf"));
        return files;
    }
}
