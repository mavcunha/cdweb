package fs;

import java.io.File;
import java.util.regex.Pattern;

public class FileFinder {

    private final File rootDir;
    private final Files files = new Files();
    private final Pattern pattern;

    public FileFinder(String root, Pattern filter) {
        rootDir = new File(root);
        pattern = filter;
    }

    public Files find() {
        visitAllFiles(rootDir);
        return files;
    }

    private void visitAllFiles(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                visitAllFiles(new File(dir, children[i]));
            }
        } else {
            addIfMatches(dir);
        }
    }

    private void addIfMatches(File dir) {
        if(pattern.matcher(dir.getName()).matches())
            files.add(dir);
    }
}
