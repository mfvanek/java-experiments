package io.github.mfvanek.computer.science;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class BackspaceCompare {

    public static boolean backspaceCompare(String s, String t) {
    /*
    // naive
    return processBackspace(s).equals(processBackspace(t));
     */

        int i = s.length() - 1;
        int j = t.length() - 1;

        while (i >= 0 && j >= 0) {
            i = processBackspace(s, i);
            j = processBackspace(t, j);
            if (i < 0 || j < 0) {
                return i == j;
            }
            if (s.charAt(i) != t.charAt(j)) {
                return false;
            }
            --i;
            --j;
        }

        if (i >= 0) {
            i = processBackspace(s, i);
        }
        if (j >= 0) {
            j = processBackspace(t, j);
        }
        return i == j;
    }

    private static int processBackspace(String str, int index) {
        if (str.charAt(index) == '#') {
            int charsToDelete = 1;
            --index;
            while (index >= 0 && (charsToDelete > 0 || str.charAt(index) == '#')) {
                if (str.charAt(index) == '#') {
                    ++charsToDelete;
                } else {
                    --charsToDelete;
                }
                --index;
            }
//      if (index == 0 && charsToDelete > 0 ){
//        --index;
//      }
        }
        return index;
    }

    private static Collection<Character> processBackspace(String str) {
        Deque<Character> res = new ArrayDeque<>();
        char[] arr = str.toCharArray();
        for (char c : arr) {
            if (c != '#') {
                res.addLast(c);
            } else {
                if (!res.isEmpty()) {
                    res.removeLast();
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        System.out.println(backspaceCompare("y#fo##f", "y#f#o##f")); // true

        System.out.println(backspaceCompare("nzp#o#g", "b#nzp#o#g")); // true

        System.out.println(backspaceCompare("ab#c", "ad#c")); // true

        System.out.println(backspaceCompare("ab##", "c#d#")); // true

        System.out.println(backspaceCompare("a#c", "b")); // false

        System.out.println(backspaceCompare("#", "b")); // false
    }
}
