package fs;

import org.apache.commons.lang.StringUtils;

import java.io.File;

public class LocalFile extends File {
    private final String partialPath;
    private final File realFile;

    public LocalFile(String partialPath, File file) {
        super(file.getPath());
        this.realFile = file;
        this.partialPath = partialPath;
    }

    public String getRelativePath() {
        String s = realFile.getAbsolutePath().substring(partialPath.length());
        return StringUtils.removeStart(s, File.separator);
    }
}
