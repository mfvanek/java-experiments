package io.github.mfvanek.computer.science;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class JaTest6 {

    private static int counter = 0;

    public static void main(String[] args) {
        try (BufferedReader input = new BufferedReader(new FileReader("input.txt"));
             PrintWriter output = new PrintWriter(new FileWriter("output.txt"))) {
            counter = 0;
            int k = Integer.parseInt(input.readLine(), 10);
            if (k > 0) {
                Deque<String> files = new LinkedList<>();
                for (int i = 0; i < k; ++i) {
                    String[] tmp = input.readLine().trim().split(" ");
                    if (tmp.length > 1) {
                        int n = Integer.parseInt(tmp[0], 10);
                        if (n > 0) {
                            final String fileName = makeFileName();
                            files.addLast(fileName);
                            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
                                for (int j = 0; j < tmp.length && j < n + 1; ++j) {
                                    writer.println(tmp[j]);
                                }
                            }
                        }
                    }
                }

                while (files.size() > 1) {
                    final String newFile = merge(files.removeFirst(), files.removeFirst());
                    files.addLast(newFile);
                }

                toOutput(output, files.peekFirst());
            }
        } catch (Exception e) {
        }
    }

    private static String makeFileName() {
        return String.format("%d.t", counter++);
    }

    private static void toOutput(PrintWriter output, String fileName) throws IOException {
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            int n = Integer.parseInt(input.readLine(), 10);
            for (int i = 0; i < n; ++i) {
                output.print(input.readLine() + " ");
            }
        }
        Files.delete(Paths.get(fileName));
    }

    private static String merge(String firstFile, String secondFile) throws IOException {
        final String fileName = makeFileName();
        try (BufferedReader first = new BufferedReader(new FileReader(firstFile));
             BufferedReader second = new BufferedReader(new FileReader(secondFile));
             PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            int n1 = Integer.parseInt(first.readLine(), 10);
            int n2 = Integer.parseInt(second.readLine(), 10);
            int n = n1 + n2;
            writer.println(n);

            int i = 0, j = 0;
            int a = Integer.parseInt(first.readLine(), 10);
            int b = Integer.parseInt(second.readLine(), 10);
            while (i < n1 && j < n2) {
                if (a < b) {
                    writer.println(a);
                    ++i;
                    if (i < n1) {
                        a = Integer.parseInt(first.readLine(), 10);
                    }
                } else {
                    writer.println(b);
                    ++j;
                    if (j < n2) {
                        b = Integer.parseInt(second.readLine(), 10);
                    }
                }
            }

            while (i < n1) {
                writer.println(a);
                ++i;
                if (i < n1) {
                    a = Integer.parseInt(first.readLine(), 10);
                }
            }

            while (j < n2) {
                writer.println(b);
                ++j;
                if (j < n2) {
                    b = Integer.parseInt(second.readLine(), 10);
                }
            }
        }

        Files.delete(Paths.get(firstFile));
        Files.delete(Paths.get(secondFile));
        return fileName;
    }
}
