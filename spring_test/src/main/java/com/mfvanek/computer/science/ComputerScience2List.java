package com.mfvanek.computer.science;

import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.Scanner;

public class ComputerScience2List {

  public static void main(String[] args) {
    final Scanner scanner = new Scanner(System.in);
    System.out.println("How many elements do yo want to add?..");
    final int count = scanner.nextInt();
    SimpleLinkedList list = new SimpleLinkedList();
    System.out.print("list = ");
    list.printAll();
    System.out.println();

    for (int i = 0; i < count; ++i) {
      int item = scanner.nextInt();
      list.add(Node.head(item));
    }

    System.out.print("list = ");
    list.printAll();
    System.out.println();
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
  }
}
