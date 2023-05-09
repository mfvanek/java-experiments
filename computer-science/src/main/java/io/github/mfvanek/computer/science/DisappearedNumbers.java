package io.github.mfvanek.computer.science;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisappearedNumbers {

  public static List<Integer> findDisappearedNumbers(int[] nums) {
    List<Integer> result = new ArrayList<>();

        /*
        // naive
        Set<Integer> temp = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            temp.add(nums[i]);
        }

        for (int i = 1; i < nums.length + 1; ++i) {
            if (!temp.contains(i)) {
                result.add(i);
            }
        }
        */

    int temp = 0;
    for (int i = 0; i < nums.length; ++i) {
      temp = Math.abs(nums[i]) - 1;
      if (nums[temp] > 0) {
        nums[temp] = -1 * nums[temp];
      }
    }

    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] > 0) {
        result.add(i+1);
      }
    }

    return result;
  }

  public static int missingNumber(int[] nums) {

    /*
    // naive
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] != i) {
        return i;
      }
    }
    return nums.length;
     */

    int res = 0;
    for (int i = 0; i < nums.length; ++i) {
      res = res ^ nums[i];
      res = res ^ i;
    }
    return res ^ nums.length;
  }

  public static void main(String[] args) {
    int[] nums = {4,3,2,7,8,2,3,1};
    System.out.println(findDisappearedNumbers(nums));

    int[] nums1 = {3,0,1}; // 2
    System.out.println(missingNumber(nums1));
    int[] nums2 = {0,1}; // 2
    System.out.println(missingNumber(nums2));
    int[] nums3 = {9,6,4,2,3,5,7,0,1}; // 8
    System.out.println(missingNumber(nums3));
  }
}
