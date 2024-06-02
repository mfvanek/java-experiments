package io.github.mfvanek.computer.science;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

// 1117. Building H2O
// https://leetcode.com/problems/building-h2o/description/
public class H2O {

    private static final boolean DEBUG = false;

    private static final Runnable releaseHydrogen = () -> {
        if (DEBUG) {
            System.out.println('H');
        } else {
            System.out.print('H');
        }
    };
    private static final Runnable releaseOxygen = () -> {
        if (DEBUG) {
            System.out.println('O');
        } else {
            System.out.print('O');
        }
    };

    private static final Semaphore hydrogenSemaphore = new Semaphore(2);
    private static final Semaphore oxygenSemaphore = new Semaphore(1);
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
        if (DEBUG) {
            System.out.println("cyclicBarrier passed");
        }
    });

    public static void main(String[] args) {
        final String water = "OOHHHHHOH";
        int oCounter = 0;
        int hCounter = 0;
        for (char c : water.toCharArray()) {
            if (c == 'H') {
                new Thread(() -> {
                    try {
                        new H2O().hydrogen(releaseHydrogen);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }, "h-thread-" + ++hCounter).start();
            } else {
                new Thread(() -> {
                    try {
                        new H2O().oxygen(releaseOxygen);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }, "o-thread-" + ++oCounter).start();
            }
        }
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        if (DEBUG) {
            System.out.println(Thread.currentThread().getName() + ": hydrogen");
        }
        hydrogenSemaphore.acquire();
        if (DEBUG) {
            System.out.println(Thread.currentThread().getName() + ": hydrogen semaphore acquired");
        }

        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        releaseHydrogen.run();

        hydrogenSemaphore.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        if (DEBUG) {
            System.out.println(Thread.currentThread().getName() + ": oxygen");
        }
        oxygenSemaphore.acquire();
        if (DEBUG) {
            System.out.println(Thread.currentThread().getName() + ": oxygen semaphore acquired");
        }

        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        releaseOxygen.run();

        oxygenSemaphore.release();
    }
}
