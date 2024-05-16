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

import java.nio.charset.StandardCharsets;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileChecker {
    private FileCollection fileCollection;
    private int numInvalid;

    public FileChecker (FileCollection fileCollection) {
        this.fileCollection = fileCollection;
    }

    public void verifyFileDocstrings() {
        for (Path file : this.fileCollection) {
            this.verifyFile(file);
        }
    }

    private void verifyFile(Path file) {
        try (Stream<String> stream = Files.lines(file,StandardCharsets.UTF_8)) {
            stream.forEach(l -> {
                if (l.startsWith(" * Last updated: ")) {
                    String foundDate = l.substring(17, 27);
                    try {
                        BasicFileAttributes fileAttributes = Files.readAttributes(file, BasicFileAttributes.class);
                        String fileLastModified = fileAttributes.lastModifiedTime().toString().substring(0, 10);
                        if (!fileLastModified.equals(foundDate)) {
                            System.out.println("\n \uD83E\uDDF8 Invalid docstring in file: " + file.toString());
                            System.out.println("    - Date in file:  " + foundDate);
                            System.out.println("    - Last modified: " + fileLastModified);
                            this.numInvalid += 1;
                        }
                    } catch (Exception e) {
                        System.err.println("Could not read file attributes: " + file.toString());
                    }
                }
            });

        } catch (Exception e) {
            System.err.println("Could not read file as UTF-8: " + file.toString());
        }
    }

    public void summarize() {
        if (this.numInvalid > 0) {
            System.out.println("\n \uD83D\uDCA5 There were " + this.numInvalid + " outdated docstring(s) in total. Resolve this please!");
        } else {
            System.out.println("\n \uD83C\uDF89 There were no outdated docstrings. Great job!");
        }
    }
}