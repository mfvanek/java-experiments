package io.github.mfvanek.computer.science;

import java.util.Arrays;

public class SortedSquares {

  public static int[] sortedSquares(int[] nums) {
    /*
    // naive
    int[] res = new int[nums.length];
    for (int i = 0; i < nums.length; ++i) {
      res[i] = nums[i] * nums[i];
    }
    Arrays.sort(res); // O(nlogn)
    return res;
     */

    if (nums.length == 0) {
      return nums;
    }

    /*
    int[] positive = new int[nums.length];
    int p = -1;
    int[] negative = new int[nums.length];
    int n = -1;
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] < 0) {
        negative[++n] = nums[i] * nums[i];
      } else {
        positive[++p] = nums[i] * nums[i];
      }
    }

    if (n == -1) {
      return positive;
    }

    int[] res = new int[nums.length];
    int i = 0;
    int pi = 0;
    while (pi <= p && n >= 0) {
      if (negative[n] < positive[pi]) {
        res[i++] = negative[n--];
      } else {
        res[i++] = positive[pi++];
      }
    }

    while (n >= 0) {
      res[i++] = negative[n--];
    }

    while (pi <= p) {
      res[i++] = positive[pi++];
    }

    return res;
     */

    // single pass
    int[] res = new int[nums.length];
    final int n = nums.length;
    int i = 0;
    int j = n - 1;
    for (int p = n - 1; p >= 0; --p) {
      if (Math.abs(nums[i]) > Math.abs(nums[j])) {
        res[p] = nums[i] * nums[i];
        ++i;
      } else {
        res[p] = nums[j] * nums[j];
        --j;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    int[] arr1 = {1,2,4,7};
    System.out.println(Arrays.toString(arr1));
    System.out.println(Arrays.toString(sortedSquares(arr1)));
    System.out.println();

    int[] arr2 = {};
    System.out.println(Arrays.toString(arr2));
    System.out.println(Arrays.toString(sortedSquares(arr2)));
    System.out.println();

    int[] arr3 = {-3,-1,0,1,2,3,4};
    System.out.println(Arrays.toString(arr3));
    System.out.println(Arrays.toString(sortedSquares(arr3)));
    System.out.println();
  }
}
