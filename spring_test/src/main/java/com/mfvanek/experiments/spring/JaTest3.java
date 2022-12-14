package com.mfvanek.experiments.spring;

import java.io.*;

public class JaTest3 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\src\\experiments\\spring_test\\src\\main\\resources\\input.txt"));
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
