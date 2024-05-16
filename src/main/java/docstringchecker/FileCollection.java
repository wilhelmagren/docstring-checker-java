/*
 * MIT License
 *
 * Copyright (c) 2024 Wilhelm Ågren
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * File created: 2024-05-16
 * Last updated: 2024-05-16
 */

package docstringchecker;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class FileCollection implements Iterable<Path> {
    private List<Path> fileList;

    public static boolean isJavaFile(Path file) {
        int index = file.toString().lastIndexOf(".");
        if (index > 0) {
            return file.toString().substring(index + 1).equals("java");
        }
        return false;
    }

    public FileCollection (String root) {

        try (Stream<Path> files = Files.walk(Paths.get(root))) {
            this.fileList = files
                    .filter(Files::isRegularFile)
                    .filter(Files::isWritable)
                    .filter(FileCollection::isJavaFile)
                    .toList();
        }
        catch (Exception e) {
            System.err.println("Could not recursively list files from " + root);
            System.exit(1);
        }
    }

    public List<Path> getFileList() {
        return this.fileList;
    }

    public Iterator<Path> iterator() {
        return new FileCollectionIterator(this);
    }
}

class FileCollectionIterator implements Iterator<Path> {
    private int current;
    final private List<Path> fileList;

    public FileCollectionIterator (FileCollection fileCollection) {
        this.current = 0;
        this.fileList = fileCollection.getFileList();
    }

    public boolean hasNext() {
        return (this.current) != this.fileList.size();
    }

    public Path next() {
        this.current += 1;
        return this.fileList.get(this.current - 1);
    }
}