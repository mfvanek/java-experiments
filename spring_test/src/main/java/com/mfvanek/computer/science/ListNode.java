package com.mfvanek.computer.science;

public class ListNode {
  int val;
  ListNode next;

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  static void print(ListNode head) {
    if (head == null) {
      System.out.println("empty!");
      return;
    }

    System.out.print('[');
    do {
      System.out.print(head.val + " ");
      head = head.next;
    } while (head != null);
    System.out.print(']');
    System.out.println();
  }
}
