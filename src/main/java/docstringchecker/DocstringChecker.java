package docstringchecker;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DocstringChecker {
    public static void main(String[] args) {

        String root = args[1];

        try {
            Stream<Path> files = Files.walk(Paths.get(root));
            files
                .filter(Files::isRegularFile)
                .filter(Files::isWritable)
                .forEach(System.out::println);
        }
        catch(Exception e) {
            System.err.println(String.format(
                "Could not recursively list files from %s", root
            ));
        }
    }
}
