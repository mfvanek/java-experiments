package com.mfvanek.computer.science;

public class NumArrayDP {

  static class NumArray {

    private final int[] c;

    public NumArray(int[] nums) {
      c = new int[nums.length + 1];
      c[0] = 0;
      for (int i = 0; i< nums.length; ++i) {
        c[i+1] = c[i] + nums[i];
      }
    }

    public int sumRange(int left, int right) {
      return c[right+1] - c[left];
    }
  }

  public static void main(String[] args) {
    int[] nums = {-2, 0, 3, -5, 2, -1};
    NumArray obj = new NumArray(nums);
    System.out.println(obj.sumRange(0, 2)); // 1
    System.out.println(obj.sumRange(2, 5)); // -1
    System.out.println(obj.sumRange(0, 5)); // -3
  }
}
