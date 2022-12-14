package com.mfvanek.experiments.spring;

import java.util.*;

public class JaTest1 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String j = scanner.nextLine();

            Set<Character> jewels = new HashSet<>();
            for (char c : j.toCharArray()) {
                jewels.add(c);
            }
            String s = scanner.nextLine();

            int counter = 0;
            for (char c : s.toCharArray()) {
                if (jewels.contains(c)) {
                    ++counter;
                }
            }
            System.out.println(counter);
        }
    }
}
