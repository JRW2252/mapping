package com.mapping.mapping.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUtil {

    public static String loadResource(String fileName) throws IOException, URISyntaxException {
        URI uri = FileUtil.class.getClassLoader().getResource(fileName).toURI();
        return new String(Files.readAllBytes(Paths.get(uri)));
    }

    public static List<String> getLines(final String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(loadFile(fileName));
        List<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        scanner.close();
        return lines;
    }

    public static File loadFile(final String fileName) throws FileNotFoundException {
        return Optional.of(FileUtil.class)
            .map(Class::getClassLoader)
            .map(loader -> loader.getResource(fileName))
            .map(URL::getFile)
            .map(File::new)
            .orElseThrow(() -> new FileNotFoundException(String.format("File %s is not found in Resources directory", fileName)));
    }
}
