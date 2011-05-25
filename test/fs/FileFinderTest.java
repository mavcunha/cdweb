package fs;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class FileFinderTest {

    private final Mockery context = new JUnit4Mockery(){{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

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
        final File file = context.mock(File.class);
        context.checking(new Expectations() {{
            allowing(file).isDirectory();
            will(returnValue(true));
            allowing(file).list();
            will(returnValue(null));
            allowing(file).isFile();
            will(returnValue(false));
        }});

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
