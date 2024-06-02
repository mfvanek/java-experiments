package io.github.mfvanek.computer.science;

public class ReverseString {

    public static void main(String[] args) {
        final String s = "qwerty!";
        System.out.println(reverseString(s));
    }

    public static String reverseString(String str) {
        final char[] chars = str.toCharArray();
        reverseString(chars);
        return String.valueOf(chars);
    }

    public static void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; ++i) {
            char temp = s[i];
            int j = s.length - i - 1;
            s[i] = s[j];
            s[j] = temp;
        }
    }
}
