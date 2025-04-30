package io.github.mfvanek.array.interview;

/*
Input
aabbababaacccdaaad
K = 2
Output
abababaccdaad

Input
aabbababaacccdaaad
K = 1
Output
aabbababaacccdaaad

Input
aabbababaacccdaaad
K = 3
Output
aabbababaacdad
*/
// The main method must be in a class named "Main".
public class ArrayInterview {

    public static void main(String[] args) {
        //System.out.println("Hello world!");
        System.out.println(removeConsecutiveDuplicates("aabbababaacccdaaad", 2));
        System.out.println(removeConsecutiveDuplicates("aabbababaacccdaaad", 3));
    }

    public static String removeConsecutiveDuplicates(String input, int k) {
        if (k <= 1) return input;

        char[] arr = input.toCharArray();
        StringBuilder res = new StringBuilder();
        int counter = 1;
        char prev = arr[0]; // TODO check size
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] == prev) {
                ++counter;
                if (counter == k) {
                    res.append(prev);
                    ++i;
                    if (i == arr.length) break;
                    prev = arr[i];
                    counter = 1;
                    continue;
                }
            } else {
                res.append(prev);
                prev = arr[i];
                counter = 1;
            }
        }
        res.append(prev);
        return res.toString();
    }
}
