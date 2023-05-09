package io.github.mfvanek.computer.science;

import java.util.*;

public class JaTest2 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int maxSeq = 0;
            int curSeq = 0;
            for (int i = 0; i < n; ++i) {
                int item = scanner.nextInt();
                if (item == 1) {
                    ++curSeq;
                } else {
                    maxSeq = maxSeq >= curSeq ? maxSeq : curSeq;
                    curSeq = 0;
                }
            }
            maxSeq = maxSeq >= curSeq ? maxSeq : curSeq;
            System.out.println(maxSeq);
        }
    }
}
