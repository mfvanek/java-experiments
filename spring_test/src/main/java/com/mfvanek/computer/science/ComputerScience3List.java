package com.mfvanek.computer.science;

import org.springframework.lang.Nullable;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ComputerScience3List {

  public static void main(String[] args) {
    /*
    System.out.println(" == removeNthFromEnd");
    ListNode lst1 = create(new int[]{1, 2, 2, 1});
    printAll(lst1);
    printAll(removeNthFromEnd(lst1, 1));

    lst1 = create(new int[]{1, 2, 2, 1});
    printAll(lst1);
    printAll(removeNthFromEnd(lst1, 4));

    lst1 = create(new int[]{1});
    printAll(lst1);
    printAll(removeNthFromEnd(lst1, 1));

    ListNode l4 = new ListNode(2);
    ListNode l3 = new ListNode(0, l4);
    ListNode l2 = new ListNode(3, l3);
    ListNode l1 = new ListNode(1, l2);
    System.out.println(isPalindrome(l1)); // false
    ListNode lst1 = new ListNode(1);
    ListNode lst2 = new ListNode(2, lst1);
    ListNode lst3 = new ListNode(1, lst2);
    ListNode lst4 = new ListNode(2, lst2);
    ListNode lst5 = new ListNode(1, lst4);
    System.out.println(isPalindrome(lst3)); // true
    System.out.println(isPalindrome(lst4)); // false
    System.out.println(isPalindrome(lst5)); // true

*/
    System.out.println(" == deleteDuplicates");
    final ListNode lst0 = create(new int[]{1, 1});
    printAll(lst0);
    printAll(deleteDuplicates(lst0));

    final ListNode lst1 = create(new int[]{1, 1, 2, 2, 3});
    printAll(lst1);
    printAll(deleteDuplicates(lst1));

    final ListNode lst2 = create(new int[]{1,1,2,3,3,4,4,5});
    printAll(lst2);
    printAll(deleteDuplicates(lst2));

    /*
    SimpleLinkedList list = new SimpleLinkedList();
    System.out.print("list = ");
    list.printAll();
    System.out.println();

    list.add(Node.head(1));
    list.add(Node.head(3));
    final Node second = Node.head(7);
    list.add(second);
    list.add(Node.head(4));
    list.add(Node.head(6));
    final Node last = Node.withNext(second, 77);
    list.add(last);
    final Node cycleNode = list.detectCycle();
    if (cycleNode != null) {
      System.out.println("Cycle starting node = " + cycleNode.baggage);
    }

    System.out.print("list = ");
    list.printAll();
    System.out.println();
     */
  }

  /*
  Дан односвязный список. Нужно удалить N-й элемент с конца. Вернуть ссылку на head.
  Пример:
  Input:  [1,2,3,4,5], n = 2
  Output: [1,2,3,5]
   */
  public static ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) {
      return null;
    }

    if (head.next == null && n == 1) {
      return null;
    }

    /*
    // naive
    int nodesCount = 0;
    ListNode cur = head;
    while (cur != null) {
      ++nodesCount;
      cur = cur.next;
    }

    if (n > nodesCount) {
      return head;
    }

    ListNode dummy = new ListNode(-1, head);
    cur = dummy;
    for (int index = 0; index < nodesCount - n; ++index) {
      cur = cur.next;
    }

    if (cur.next != null) {
      cur.next = cur.next.next;
    } else {
      cur.next = null;
    }
    return dummy.next;
     */

    ListNode dummy = new ListNode(-1, head);
    ListNode slow = dummy;
    ListNode fast = dummy;

    for (int i = 1; i <= n; ++i) {
      fast = fast.next;
    }

    while (fast.next != null) {
      slow = slow.next;
      fast = fast.next;
    }

    slow.next = slow.next != null ? slow.next.next : null;
    return dummy.next;
  }

  public static ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    /*
    // naive
    Map<Integer, Integer> temp = new HashMap<>();
    ListNode cur = head;
    while (cur != null) {
      temp.compute(cur.val, (k,v) -> v != null ? v + 1 : 1);
      cur = cur.next;
    }
    final Set<Integer> duplicated = temp.entrySet().stream()
        .filter(e -> e.getValue() > 1)
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet());
    ListNode dummy = new ListNode(-1);
    ListNode prev = null;
    cur = head;
    while (cur != null) {
      if (!duplicated.contains(cur.val)) {
        if (prev == null) {
          prev = cur;
          dummy.next = prev;
        } else {
          prev.next = cur;
          prev = cur;
        }
      }
      cur = cur.next;
    }
    if (prev != null) {
      prev.next = null;
    }
    return dummy.next;
     */

    ListNode dummy = new ListNode(-1, head);
    ListNode prev = dummy;
    ListNode cur = head;
    while (cur != null && cur.next != null) {
      if (cur.val == cur.next.val) {
        int temp = cur.val;
        while (cur != null && cur.val == temp) {
          cur = cur.next;
        }
      } else {
        prev.next = cur;
        prev = cur;
        cur = cur.next;
      }
    }
    prev.next = cur;
    return dummy.next;
  }

  static ListNode create(int[] vals) {
    ListNode next = null;
    for (int j = vals.length - 1; j >= 0; --j) {
      ListNode cur = new ListNode(vals[j], next);
      next = cur;
    }
    return next;
  }

  static void printAll(ListNode head) {
    if (head == null) {
      System.out.println("empty!");
      return;
    }
    ListNode elem = head;
    System.out.print('[');
    do {
      System.out.print(elem.val);
      elem = elem.next;
    } while (elem != null);
    System.out.print(']');
    System.out.println();
  }

  public static ListNode middleNode(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  public static boolean isPalindrome(ListNode head) {
    /*
    // wrong (
    Deque<Integer> stack = new LinkedList<>();
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      stack.addLast(slow.val);
      slow = slow.next;
      fast = fast.next.next;
    }
    while (slow != null) {
      slow = slow.next;
      if (slow.val != stack.pollLast().intValue()) {
        return false;
      }
    }
    return true;
     */
    /*
    wrong
    ListNode slow = head;
    ListNode fast = head;
    int res = 0;
    while (fast != null && fast.next != null) {
      res = res ^ slow.val;
      slow = slow.next;
      fast = fast.next.next;
    }
    if (fast != null) { // odd nodes in the list
      res = res ^ slow.val;
    }
    while (slow != null) {
      res = res ^ slow.val;
      slow = slow.next;
    }
    return res == 0;
     */
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    if (fast != null) { // odd nodes in the list
      slow = slow.next; //move forward
    }
    slow = reverse(slow);
    fast = head;
    while(slow != null) {
      if (slow.val != fast.val) {
        return false;
      }
      slow = slow.next;
      fast = fast.next;
    }
    return true;
  }

  private static ListNode reverse(ListNode head) {
    ListNode prev = null;
    while (head != null) {
      ListNode temp = head;
      head = head.next;
      temp.next = prev;
      prev = temp;
    }
    return prev;
  }

  public static class Node {
    Node next;
    final int baggage;

    private Node(@Nullable Node next, final int elem) {
      this.next = next;
      this.baggage = elem;
    }

    public static Node head(final int elem) {
      return new Node(null, elem);
    }

    public static Node withNext(Node next, final int elem) {
      Objects.requireNonNull(next);
      return new Node(next, elem);
    }
  }

  public static class SimpleLinkedList {
    private Node head;
    private Node tail;

    void add(Node item) {
      Objects.requireNonNull(item);
      if (tail != null) {
        tail.next = item;
      } else {
        head = item;
      }
      tail = item;
    }

    void printAll() {
      if (hasCycle()) {
        System.out.println("Has cycle!!!!");
        return;
      }
      if (head == null) {
        System.out.println("empty!");
        return;
      }
      Node elem = head;
      System.out.print('[');
      do {
        System.out.print(elem.baggage);
        elem = elem.next;
      } while (elem != null);
      System.out.print(']');
    }

    boolean hasCycle() {
      if (head == null) {
        return false;
      }
//      Set<Node> visitedNodes = new HashSet<>();
//      Node elem = head;
//      do {
//        final boolean result = visitedNodes.add(elem);
//        if (!result) {
//          return true;
//        }
//        elem = elem.next;
//      } while (elem != null);
      Node slow = head;
      Node fast = head;
      do {

        slow = slow.next;
        fast = fast.next;
        if (fast == null) {
          return false;
        }
        fast = fast.next;
        if (fast == slow) {
          return true; // cycle!
        }
      } while (slow != null && fast != null);
      return false;
    }

    @Nullable
    Node detectCycle() {
      if (head == null) {
        return null;
      }
      boolean intersection = false;
      Node slow = head;
      Node fast = head;
      while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (fast == slow) {
          intersection = true;
          break;
        }
      }
      if (!intersection) {
        return null;
      }

      slow = head;
      while (slow != fast) {
        slow = slow.next;
        fast = fast.next;
      }
      return slow;
    }
  }
}
