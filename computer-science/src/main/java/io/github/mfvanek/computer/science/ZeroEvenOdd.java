package io.github.mfvanek.computer.science;

import java.util.function.IntConsumer;

class ZeroEvenOdd {
  private final int n;
  private volatile int cur = 0;
  private volatile boolean pz = true;

  public ZeroEvenOdd(int n) {
    this.n = n;
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void zero(IntConsumer printNumber) throws InterruptedException {
    synchronized (this) {
      while (cur <= n) {
        if (!pz) {
          wait();
        } else {
          if (cur < n) {
            printNumber.accept(0);
          }
          cur = cur + 1;
          pz = false;
          notifyAll();
        }
      }
    }
  }

  public void even(IntConsumer printNumber) throws InterruptedException {
    synchronized (this) {
      while (cur <= n) {
        if (cur > n) {
          notifyAll();
          return;
        }
        if (pz) {
          wait();
        } else {
          if (cur % 2 == 0) {
            printNumber.accept(cur);
            pz = true;
            notifyAll();
          } else {
            wait();
          }
        }
      }
    }
  }

  public void odd(IntConsumer printNumber) throws InterruptedException {
    synchronized (this) {
      while (cur <= n) {
        if (cur > n) {
          notifyAll();
          return;
        }
        if (pz) {
          wait();
        } else {
          if (cur % 2 != 0) {
            printNumber.accept(cur);
            pz = true;
            notifyAll();
          } else {
            wait();
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    final ZeroEvenOdd e = new ZeroEvenOdd(7);
    Thread zero = new Thread(() -> {
      try {
        e.zero(System.out::println);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    });

    Thread even = new Thread(() -> {
      try {
        e.even(System.out::println);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    });

    Thread odd = new Thread(() -> {
      try {
        e.odd(System.out::println);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    });

    odd.start();
    even.start();
    zero.start();
  }
}
