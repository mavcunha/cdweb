package fs;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Pattern;

public class FileFinder {

    private final File rootDir;
    private final Files files = new Files();

    public FileFinder(File root) {
        rootDir = root;
    }

    public Files find(Pattern filter) {
        visitAllFiles(rootDir, filter);
        return files;
    }

    private void visitAllFiles(File dir, Pattern filter) {
        String[] children;
        if (dir.isDirectory() && (children = dir.list()) != null) {
            Arrays.sort(children);
            for (int i = 0; i < children.length; i++) {
                visitAllFiles(new File(dir, children[i]), filter);
            }
        } else {
            addIfMatches(dir, filter);
        }
    }

    private void addIfMatches(File file, Pattern filter) {
        if(file.isFile() && filter.matcher(file.getName()).matches())
            files.add(new LocalFile(rootDir.getAbsolutePath(),file));
    }
}
