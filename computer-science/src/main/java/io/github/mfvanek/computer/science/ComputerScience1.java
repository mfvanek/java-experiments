package io.github.mfvanek.computer.science;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ComputerScience1 {

  public static void main(String[] args) {
    System.out.println(" == removeElement");
    int[] a = {3,2,2,3};
    System.out.println(removeElement(a, 3)); //2
    System.out.println(Arrays.toString(a));

    int[] b = {4,4,4,4};
    System.out.println(removeElement(b, 4)); //0
    System.out.println(Arrays.toString(b));
    /*
    System.out.println(" == isPalindrome");
    System.out.println(isPalindrome(1000021)); // false
    System.out.println(isPalindrome(0)); // true
    System.out.println(isPalindrome(-1)); // false
    System.out.println(isPalindrome(12)); // false
    System.out.println(isPalindrome(121)); // true

    System.out.println(" == squareOfDigits");
    System.out.println(squareOfDigits(8));
    System.out.println(squareOfDigits(10));
    System.out.println(squareOfDigits(19));

    //final int[] rawArray = {0,-1,2,9,4,8,3,5,1};
    final int[] rawArray = {1,3,7,8,9,11};
    final int[] sortedArray = rawArray.clone();
    Arrays.sort(sortedArray);
    System.out.println("raw array = " + Arrays.toString(rawArray));
    System.out.println("sorted array = " + Arrays.toString(sortedArray));
    searchAndPrintResult(sortedArray, 6);
    searchAndPrintResult(sortedArray, 7);
    searchAndPrintResult(sortedArray, 8);
    searchAndPrintResult(sortedArray, 0);
    searchAndPrintResult(sortedArray, 1);
    final Scanner scanner = new Scanner(System.in);
    System.out.println("Input a number you want to search for...");
    final int elementToFind = scanner.nextInt();
    searchAndPrintResult(sortedArray, elementToFind);
    */
  }

  public static int removeElement(int[] nums, int val) {
    int j = nums.length - 1;
    for (int i = 0; i <= j; ++i) {
      if (nums[i] == val) {
        while (nums[j] == val && j > i) {
          --j;
        }
        if (i==j) {
          return i;
        }
        nums[i] = nums[j];
        --j;
      }
    }
    return j + 1;
  }

  public static boolean isPalindrome(int x) {
    if (x < 0) {
      return false;
    }

    List<Integer> digitsReversed = new LinkedList<>();
    int res = x;
    int digit = 0;
    while (res > 9) {
      digit = res % 10;
      digitsReversed.add(digit);
      res = res / 10;
    }
    digitsReversed.add(res);

    List<Integer> digits = new ArrayList<>(digitsReversed);
    Collections.reverse(digits);

    final Iterator<Integer> first = digits.iterator();
    final Iterator<Integer> second = digitsReversed.iterator();
    while (first.hasNext()) {
      if (first.next().intValue() != second.next().intValue()) {
        return false;
      }
    }
    return true;
  }

  private static int squareOfDigits(int n) {
    int sum = 0;
    int digit= 0;
    while(n > 9) {
      digit = n % 10;
      n = n / 10;
      sum = sum + digit * digit;
    }
    sum = sum + n* n;
    return sum;
  }

  private static int binarySearch(int[] array, int left, int right, int elem) {
    // naive! stack overflow due to recursion. Needed iterative solution
    if (left > right) {
      return -1; // not found
    }
    final int mid = (left + right)/2;
    // final int mid = 1 + (right - 1)/2; Error!!!!
    System.out.printf(" ----- left = %d, right = %d, mid = %d, array[mid] = %d", left, right, mid, array[mid]);
    System.out.println();
    if (array[mid] == elem) {
      return mid; // found
    }
    if (array[mid] > elem) {
      return binarySearch(array, left, mid-1, elem);
    }
    return binarySearch(array, mid+1, right, elem);
  }

  private static void searchAndPrintResult(int[] array, int elem) {
    System.out.println();
    final int result = binarySearch(array, 0, array.length - 1, elem);
    if (result == -1) {
      System.out.printf("Element %d not found in array%n", elem);
      System.out.println();
    } else {
      System.out.printf("Element %d found at position %d: %d", elem, result, array[result]);
      System.out.println();
    }
  }
}
