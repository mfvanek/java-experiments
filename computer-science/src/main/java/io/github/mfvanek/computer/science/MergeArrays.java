package io.github.mfvanek.computer.science;

import java.util.Arrays;

public class MergeArrays {

    public static void main(String[] args) {

        System.out.println("100".compareTo("99"));

        int[] a = {1, 3, 6, 7};
        int[] b = {2, 4, 6, 22};
        int n = a.length + b.length;
        int[] res = new int[n];

        int i = 0, j = 0, r = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                res[r++] = a[i++];
            } else {
                res[r++] = b[j++];
            }
        }

        while (i < a.length) {
            res[r++] = a[i++];
        }

        while (j < b.length) {
            res[r++] = b[j++];
        }

        System.out.println(Arrays.toString(res));
    }
}
