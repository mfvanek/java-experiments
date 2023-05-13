package io.github.mfvanek.computer.science;

import java.io.*;
import java.net.URL;

import lombok.SneakyThrows;

public class JaTest3 {

    @SneakyThrows
    public static File getFileFromResource(String fileName) {
        ClassLoader classLoader = JaTest3.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(getFileFromResource("input2.txt")));
             PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            int current = 0;
            int item;
            boolean first = true;
            for (int i = 0; i < n; ++i) {
                item = Integer.parseInt(reader.readLine());
                if (first) {
                    current = item;
                    writer.println(item);
                    first = false;
                } else {
                    if (current != item) {
                        current = item;
                        writer.println(item);
                    }
                }
                // writer.flush();
            }
        }
    }
}
