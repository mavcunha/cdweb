package fs;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LocalFileTest {
    @Test
    public void shouldKnowItsRelativePath() {
        File f = new File("/some/long/complete/path/file.txt");
        LocalFile lf = new LocalFile("/some/long/complete", f);
        assertThat(lf.getRelativePath(),is("path/file.txt"));
    }

    @Test
    public void shouldReturnComplePathAsFileObjectDoes() {
        File f = new File("/some/long/complete/path/file.txt");
        LocalFile lf = new LocalFile("/some/long/complete", f);
        assertThat(lf.getPath(), is("/some/long/complete/path/file.txt"));
    }
}
