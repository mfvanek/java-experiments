package io.github.mfvanek.computer.science;

public class PeakIndexInMountainArray {
  public static int peakIndexInMountainArray(int[] arr) {
        /*
        // naive O(n)
        int max = arr[0];
        int res = 0;
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] > max) {
                max = arr[i];
                res = i;
            }
        }
        return res;
        */

    int left = 0;
    int right = arr.length - 1;
    while (right > left) {
      int m =  (left + right) / 2;
      if (arr[m-1] < arr[m] && arr[m+1] < arr[m]) {
        return m;
      }
      if (arr[m-1] > arr[m]) {
        right = m;
      } else {
        left = m + 1;
      }
    }
    return left;
  }

  public static void main(String[] args) {
    System.out.println(peakIndexInMountainArray(new int[]{3,5,3,2,0})); // 1
    System.out.println(peakIndexInMountainArray(new int[]{0,2,3,5,3})); // 3
    System.out.println(peakIndexInMountainArray(new int[]{2,3,2})); // 1
    System.out.println(peakIndexInMountainArray(new int[]{0,2,1,0})); // 1
    System.out.println(peakIndexInMountainArray(new int[]{0,10,5,2})); // 1
    System.out.println(peakIndexInMountainArray(new int[]{18,29,38,59,98,100,99,98,90})); // 5
  }
}
