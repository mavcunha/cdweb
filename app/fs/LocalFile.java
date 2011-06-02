package fs;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;

import static org.apache.commons.io.FilenameUtils.*;

public class LocalFile extends File {
    private final String partialPath;
    private final File realFile;

    public LocalFile(String partialPath, File file) {
        super(file.getPath());
        this.realFile = file;
        this.partialPath = partialPath;
    }

    public String getWebPath() {
        String s = realFile.getAbsolutePath().substring(partialPath.length());
        return StringUtils.removeStart(s, File.separator);
    }

    public String getNameWithoutExt() {
        return removeExtension(getName());
    }

    public String getRelativePath() {
        return getPathNoEndSeparator(FilenameUtils.getPath(getWebPath()));
    }
}
