package io.github.mfvanek.computer.science;

import java.util.Arrays;

public class ForLecture2 {

    public static void main(String[] args) {
        int[] array = {12, 5, -1, 4, 6, 0};
        bubbleSort(array);
        Arrays.stream(array).forEachOrdered(a -> System.out.print(a + " "));
    }

    private static void bubbleSort(int[] src) {
        for (int i = 0; i < src.length; ++i) {
            for (int j = 0; j < src.length - i - 1; ++j) {
                if (src[j] > src[j + 1]) {
                    int tmp = src[j];
                    src[j] = src[j + 1];
                    src[j + 1] = tmp;
                }
            }
        }
    }
}
