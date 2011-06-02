package fs;

import org.junit.Test;
import play.test.UnitTest;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;

public class LocalFileTest extends UnitTest {
    @Test
    public void shouldKnowItsWebPath() {
        File f = new File("/some/long/complete/path/file.txt");
        LocalFile lf = new LocalFile("/some/long/complete", f);
        assertEquals(lf.getWebPath(),"path/file.txt");
    }

    @Test
    public void shouldReturnComplePathAsFileObjectDoes() {
        File f = new File("/some/long/complete/path/file.txt");
        LocalFile lf = new LocalFile("/some/long/complete", f);
        assertEquals(lf.getPath(), "/some/long/complete/path/file.txt");
    }

    @Test
    public void canReturnFilenameWithoutExtension() {
        LocalFile f = new LocalFile("/some/long", new File("/some/long/file.rtf"));
        assertThat(f.getNameWithoutExt(),is("file"));
    }

    @Test
    public void canReturnFileRelativePathGivenARootPath() {
        LocalFile f = new LocalFile("/some/long", new File("/some/long/relative/file.rtf"));
        assertThat(f.getRelativePath(),is("relative"));
    }
}
