package io.github.mfvanek.computer.science;

public class NextGreatestLetter {

  public static char nextGreatestLetter(char[] letters, char target) {
        /*
        // naive O(n) solution
        for (int i = 0; i < letters.length; ++i) {
            if (letters[i] > target) {
                return letters[i];
            }
        }
        return letters[0];
        */

    int left = 0;
    int right = letters.length - 1;
    while (right > left) {
      int mid = (left + right) / 2;
      if (letters[mid] <= target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    if (letters[left] > target) {
      return letters[left];
    } else if (left < letters.length - 1) {
      return letters[++left];
    }
    return letters[0];
  }

  public static void main(String[] args) {
    System.out.println(nextGreatestLetter(new char[]{'c','f','j'}, 'c')); // f
  }
}
