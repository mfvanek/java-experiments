package io.github.mfvanek.computer.science;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForLecture {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        List<String> lst = new ArrayList<>(Arrays.asList("1s", "2s", "3s"));
        List<Object> obj = (List<Object>)((List<?>) lst); // you can to do so...
        obj.add("str");
        obj.add(Integer.valueOf(1));
        obj.add(Long.valueOf(22L));
        System.out.println(obj);

        System.out.println(lst.size());
        lst.add("another"); // it's OK

        for (String s : lst) {
            System.out.println(s); // java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
        }


        //final int n = 10;
        // final int[] array = IntStream.range(0, n).toArray();
        // final int[] array = {11, 2, 0, -2, 5, 1, -434, 0, 12, 4};
        final int[] array = {5, 2, 4, 3, 1};
        System.out.println("Original array:");
        Arrays.stream(array).forEach(a -> System.out.print(a + " "));
        System.out.println("\n\n");

//        final String[] resultArray = myStupidAlgorithm(array);
//        System.out.println("\n\nResult array:");
//        Arrays.stream(resultArray).forEach(a -> System.out.print(a + " "));

        System.out.println("insertionSort");
        int[] sorted = insertionSort(array, true);
        //System.out.println("\n\nSorted array:");
        //Arrays.stream(sorted).forEach(a -> System.out.print(a + " "));
        System.out.println("\n\n");

        System.out.println("bubbleSort");
        int[] sorted1 = bubbleSort(array, true);
        //System.out.println("\n\nSorted array:");
        //Arrays.stream(sorted1).forEach(a -> System.out.print(a + " "));
    }

    private static String[] myStupidAlgorithm(final int[] input) {
        final int[] src = input.clone();
        final String[] output = new String[src.length];
        for (int i = 0; i < src.length; ++i) {
            src[i] *= 10;
        }
        for (int i = 0; i < src.length; ++i) {
            output[i] = String.format("'%d'", src[i]);
        }
        return output;
    }

    private static int[] insertionSort(int[] src, boolean show) {
        int[] dest = src.clone();
        for (int i = 1; i < src.length; ++i) {
            for (int j = i - 1; j >= 0 && dest[j] > dest[j + 1]; --j) {
                swap(dest, j, j + 1);
                if (show) {
                    Arrays.stream(dest).forEach(a -> System.out.print(a + " "));
                    System.out.println();
                }
            }
        }
        return dest;
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }

    private static int[] bubbleSort(int[] src, boolean show) {
        int[] dest = src.clone();
        final int n = dest.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n - i - 1; ++j) {
                if (dest[j] > dest[j + 1]) {
                    swap(dest, j, j + 1);
                }
                if (show) {
                    Arrays.stream(dest).forEach(a -> System.out.print(a + " "));
                    System.out.println();
                }
            }
        }
        return dest;
    }
}
