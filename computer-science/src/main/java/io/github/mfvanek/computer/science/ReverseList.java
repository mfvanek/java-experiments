package io.github.mfvanek.computer.science;

import static io.github.mfvanek.computer.science.ListNode.print;

public class ReverseList {

  private static ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode prev = null;
    while (head != null) {
      ListNode temp = head;
      head = head.next;
      temp.next = prev;
      prev = temp;
    }
    return prev;
  }
  public static ListNode reverseBetween(ListNode head, int left, int right) {
    if (head == null || head.next == null || left >= right) {
      return head;
    }

    ListNode originHead = head;
    ListNode prev = null;
    ListNode start = null;
    ListNode endTail = null;

    int counter = 0;
    while (head != null) {
      ++counter;
      if (counter < left) {
        prev = head;
      }
      if (counter == left) {
        start = head;
      }
      if (counter == right) {
        endTail = head.next;
        head.next = null;
        break;
      }
      head = head.next;
    }

    ListNode reversed = reverseList(start);
    head = reversed;
    while (head.next != null) {
      head = head.next;
    }
    head.next = endTail;
    if (prev != null) {
      prev.next = reversed;
      return originHead;
    }
    return reversed;
  }

  public static void main(String[] args) {
    ListNode first = new ListNode(1);
    print(reverseBetween(first, 1, 1));

    ListNode prev = new ListNode(2, first);
    print(reverseBetween(prev, 1, 1));
    print(reverseBetween(prev, 1, 2));
  }
}
