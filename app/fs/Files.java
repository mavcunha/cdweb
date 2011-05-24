package fs;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Files implements Iterable<File> {

    private List<File> files = new ArrayList<File>();

    public void add(File f) {
        if(f == null)
            throw new IllegalArgumentException("File shouldn't be null.");
        this.files.add(f);
    }

    public Iterator<File> iterator() {
        return this.files.iterator();
    }
}