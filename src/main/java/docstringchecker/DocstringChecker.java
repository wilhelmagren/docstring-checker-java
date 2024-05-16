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
import java.util.stream.Stream;

import docstringchecker.FileChecker;
import docstringchecker.FileCollection;

public class DocstringChecker {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("You need to provide exactly one argument, the root from where to search for outdated files!");
            return;
        }

        String root = args[0];
        FileCollection fileCollection = new FileCollection(root);
        FileChecker fileChecker = new FileChecker(fileCollection);
        fileChecker.verifyFileDocstrings();
        fileChecker.summarize();
    }
}
