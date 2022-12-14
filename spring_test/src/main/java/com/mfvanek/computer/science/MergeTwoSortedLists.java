package com.mfvanek.computer.science;

import static com.mfvanek.computer.science.ListNode.print;

public class MergeTwoSortedLists {

  public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null) {
      return list2;
    }
    if (list2 == null) {
      return list1;
    }
    ListNode newHead = null;
    ListNode first = null;
    ListNode second = null;
    if (list1.val < list2.val) {
      newHead = list1;
      first = list1.next;
      second = list2;
    } else {
      newHead = list2;
      first = list2.next;
      second = list1;
    }

    ListNode current = newHead;
    while (first != null) {
      if (second == null || first.val < second.val) {
        current.next = first;
        current = first;
      } else {
        while (second != null && second.val <= first.val) {
          current.next = second;
          current = second;
          second = second.next;
        }
        current.next = first;
        current = first;
      }
      first = first.next;
    }

    if (second != null) {
      current.next = second;
    }

    return newHead;
  }

  public static void main(String[] args) {
    ListNode l14 = new ListNode(4);
    ListNode l12 = new ListNode(2, l14);
    ListNode l11 = new ListNode(1, l12);
    ListNode l1p = new ListNode(-10, l11);

    ListNode l24 = new ListNode(4);
    ListNode l23 = new ListNode(3, l24);
    ListNode l22 = new ListNode(2, l23);
    ListNode l21 = new ListNode(1, l22);

    // print(mergeTwoLists(l11, l21));

    ListNode l31 = new ListNode(-7);

    print(mergeTwoLists(l1p, l31));
  }
}
