package com.mfvanek.experiments.spring;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    /*
a -> a
ab -> ab ba
abc -> abc
    */
    public static void main(String[] args) {

        String str = "aba";
        permute_v1(str);
        System.out.println("--------------");
        permute_v2(str);
        System.out.println("--------------");
        permute_v3(str);
    }

    private static void permute_v2(String str) {
        List<String> res = permuteUnique(str);
        res.forEach(System.out::println);
    }

    private static List<String> permuteUnique(String str) {
        final List<String> res = new ArrayList<>();

        if (str == null) {
            return res;
        }

        if (str.length() == 0) {
            res.add("");
            return res;
        }

        char first = str.charAt(0);
        List<String> words = permuteUnique(str.substring(1));
        for (String word : words) {
            for (int i = 0; i <= word.length(); ++i) {
                String perm = insertCharAt(word, first, i);
                res.add(perm);
            }
        }

        return res;
    }

    private static String insertCharAt(String word, char first, int i) {
        if (i == 0) {
            return first + word;
        }

        if (i == word.length()) {
            return word + first;
        }

        return word.substring(0, i) + first + word.substring(i);
    }

    private static void permute_v1(String str) {
        char[] arr = str.toCharArray();
        permute_v1(arr, 0, str.length());
    }

    private static void permute_v1(char[] arr, int start, int end) {
        if (start == end) {
            System.out.println(arr);
            return;
        }

        for (int i = start; i < end; ++i) {
            swap(arr, start, i);
            System.out.println(" - " + start + " -- " + String.valueOf(arr));
            permute_v1(arr, start + 1, end);
            swap(arr, start, i);
        }
    }

    private static void swap(char[] arr, int s, int i) {
        char tmp = arr[s];
        arr[s] = arr[i];
        arr[i] = tmp;
    }

    private static void permute_v3(String str) {
        char[] arr = str.toCharArray();
        permute_v3(arr, 0, str.length());
    }

    private static void permute_v3(char[] str, int s, int e) {
        if (s == e) {
            System.out.println(str);
            return;
        }

        for (int i = s; i < e; ++i) {
            if (shouldSwap(str, s, i)) {
                swap(str, i, s);
                permute_v3(str, s + 1, e);
                swap(str, i, s);
            }
        }
    }

    private static boolean shouldSwap(char[] str, int s, int curr) {
        for (int i = s; i < curr; ++i) {
            if (str[i] == str[curr]) {
                return false;
            }
        }
        return true;
    }
}
