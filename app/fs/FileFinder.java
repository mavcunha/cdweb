package fs;

import java.io.File;
import java.util.regex.Pattern;

public class FileFinder {

    private final File rootDir;
    private final Files files = new Files();
    private final Pattern pattern;

    public FileFinder(File root, Pattern filter) {
        rootDir = root;
        pattern = filter;
    }

    public Files find() {
        visitAllFiles(rootDir);
        return files;
    }

    private void visitAllFiles(File dir) {
        String[] children;
        if (dir.isDirectory() && (children = dir.list()) != null) {
            for (int i = 0; i < children.length; i++) {
                visitAllFiles(new File(dir, children[i]));
            }
        } else {
            addIfMatches(dir);
        }
    }

    private void addIfMatches(File dir) {
        if(dir.isFile() && pattern.matcher(dir.getName()).matches())
            files.add(new LocalFile(rootDir.getAbsolutePath(),dir));
    }
}
