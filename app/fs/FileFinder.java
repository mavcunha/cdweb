package fs;

import java.io.File;
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
            for (int i = 0; i < children.length; i++) {
                visitAllFiles(new File(dir, children[i]), filter);
            }
        } else {
            addIfMatches(dir, filter);
        }
    }

    private void addIfMatches(File dir, Pattern filter) {
        if(dir.isFile() && filter.matcher(dir.getName()).matches())
            files.add(new LocalFile(rootDir.getAbsolutePath(),dir));
    }
}
