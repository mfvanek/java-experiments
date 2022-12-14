package com.mfvanek.computer.science;

import java.util.Arrays;

public class DynamicProgramming {

  public static void main(String[] args) {
    System.out.println(" ==  fibonacci");
    System.out.println(fibonacci(0));
    System.out.println(fibonacci(1));
    System.out.println(fibonacci(6));
    System.out.println(fibonacci(7));

    System.out.println(" ==  climbing stairs");
    System.out.println(climbingStairs(2));
    System.out.println(climbingStairs(3));
    System.out.println(climbingStairs(4));
    System.out.println(climbingStairs(5));

    System.out.println(" ==  max profit");
    System.out.println(maxProfit(new int[]{7,1,5,3,6,4})); //5
    System.out.println(maxProfit(new int[]{7,6,4,3,1})); //0

    System.out.println(" ==  maxSubArray");
    System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4})); //6
    System.out.println(maxSubArray(new int[]{5,4,-1,7,8})); //23

    System.out.println(" == countBits");
    System.out.println(Arrays.toString(countBits(2))); //0,1,1
    System.out.println(Arrays.toString(countBits(5))); //0,1,1,2,1,2
    System.out.println(Arrays.toString(countBits(7))); //0,1,1,2,1,2,2,3
  }

  static int fibonacci(int n) {
    // 1, 1, 2, 3, 5, 8, 13, 21
    /*
    // naive with recursion
    if (n > 2) {
      return fibonacci(n-1) + fibonacci(n-2);
    }
    return 1;
    */

    if (n==0) {
      return 0;
    }
    if (n <= 2) {
      return 1;
    }
    // dynamic programming
    int[] nums = new int[n];
    nums[0] = 1;
    nums[1] = 1;
    for (int i = 2; i < n; ++i) {
      nums[i] = nums[i-1] + nums[i-2];
    }
    return nums[n-1];
  }

  static int climbingStairs(int n) {
    // 0 - 0 - no move!
    // 1 - 1
    // 2 - 2
    // 3 - 3
    // 4 - 5
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
      return 2;
    }
    int[] nums = new int[n+1];
    nums[0] = 0; // I think it means "no move"
    nums[1] = 1;
    nums[2] = 2;
    for (int i = 3; i <= n; ++i) {
      nums[i] = nums[i-1]+nums[i-2];
    }
    return nums[n];
  }

  public static int maxProfit(int[] prices) {
    /*
    // naive - O(n^2)
    int res = 0;
    int n = prices.length;
    for (int i = 0; i < n; ++i) {
      for (int j = i + 1; j < n; ++j) {
        res = Math.max(res, prices[j] - prices[i]);
      }
    }
    return res;
     */
    if (prices.length < 2) {
      return 0;
    }
    int minPrice = prices[0];
    int maxProfit = 0;
    for (int i = 1; i < prices.length; ++i) {
      maxProfit = Math.max(maxProfit, prices[i] - minPrice);
      minPrice = Math.min(minPrice, prices[i]);
    }
    return maxProfit;
  }

  public static int maxSubArray(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    /*
    // naive O(n^2)
    int res = nums[0];
    for (int i = 0; i < nums.length; ++i) {
      int sum = 0;
      for (int j = i; j < nums.length; ++j) {
        sum = sum + nums[j];
        res = Math.max(res, sum);
      }
    }
    return res;
     */
    int res = nums[0];
    int sum = 0;
    for (int i =0; i < nums.length; ++i){
      sum = Math.max(nums[i], sum + nums[i]);
      res = Math.max(res, sum);
    }
    return res;
  }

  public static int[] countBits(int n) {
    int[] res = new int[n+1];
    /*
    // naive
    for (int i = 0; i<=n;++i) {
      String b = Integer.toBinaryString(i); // or Integer.toString(i, 2);
      int sum = 0;
      for (char c : b.toCharArray()) {
        if (c == '1'){
          ++sum;
        }
      }
      res[i] = sum;
    }
     */

    /*
    // more efficient
    for (int i = 0; i<=n;++i) {
      res[i] = countSetBits(i);
    }*/

    // DP
    res[0] = 0;
    for (int i = 1; i<= n;++i) {
      // 2 - 10
      // 4 - 100
      if ((i & 1) == 0) { // even
        res[i] = res[i >> 1];
      } else { // odd
        res[i] = res[i-1] + 1;
      }
    }
    return res;
  }

  private static int countSetBits(int n) {
    int count = 0;
    while (n > 0) {
      count = count + (n & 1);
      n = n >> 1;
    }
    return count;
  }
}
