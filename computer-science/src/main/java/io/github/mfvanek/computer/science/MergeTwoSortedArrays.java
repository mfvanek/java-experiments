package io.github.mfvanek.computer.science;

import java.util.Arrays;
import java.util.List;

public class MergeTwoSortedArrays {

  public static void merge(int[] nums1, int m, int[] nums2, int n) {
    if (n == 0) {
      return;
    }

    /*
    // naive
    for (int i = 0; i < n; ++i) {
      nums1[m+i] = nums2[i];
    }
    Arrays.sort(nums1);
    */

    /*
    // temp array
    int[] temp = new int[m + n];
    int i = 0;
    int j = 0;
    int t = 0;
    while (i < m && j < n) {
      if (nums1[i] <= nums2[j]) {
        temp[t++] = nums1[i++];
      } else {
        temp[t++] = nums2[j++];
      }
    }

    while (i < m) {
      temp[t++] = nums1[i++];
    }

    while (j < n) {
      temp[t++] = nums2[j++];
    }

    for (t = 0; t < temp.length; ++t) {
      nums1[t] = temp[t];
    }
     */

    // in place version
    int i = m - 1;
    int j = n - 1;
    int t = m + n - 1;

    while (i >= 0 && j >= 0) {
      if (nums1[i] > nums2[j]) {
        nums1[t--] = nums1[i--];
      } else {
        nums1[t--] = nums2[j--];
      }
    }

    // does not have sense
//    while (i >= 0) {
//      nums1[t--] = nums1[i--];
//    }

    while (j >= 0) {
      nums1[t--] = nums2[j--];
    }
  }

  public static void main(String[] args) {
    int[] nums1 = {1,2,3,0,0,0};
    int[] nums2 = {2,5,6};
    merge(nums1, 3, nums2, 3);
    System.out.println(Arrays.toString(nums1));

    int[] nums3 = {};
    merge(nums2, 3, nums3, 0);
    System.out.println(Arrays.toString(nums2));

    int[] nums4 = {0};
    int[] nums5 = {1};
    merge(nums4, 0, nums5, 1);
    System.out.println(Arrays.toString(nums4));
  }
}
