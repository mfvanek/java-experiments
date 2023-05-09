package io.github.mfvanek.computer.science;

import java.util.*;

public class JaTest5 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String first = scanner.nextLine();
            String second = scanner.nextLine();
            char[] a1 = first.toCharArray();
            char[] a2 = second.toCharArray();
            Arrays.sort(a1);
            Arrays.sort(a2);
            System.out.println(Arrays.equals(a1, a2) ? 1 : 0);
        }
    }
}
