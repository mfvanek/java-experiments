package io.github.mfvanek.jvm.internals;

public class EmptyStackFrameProgram {

    public static void main(String[] args) {
        // Create 1000 threads
        for (int counter = 0; counter < 1000; ++counter) {
            new EmptyStackFrameThread().start();
        }
    }

    static class EmptyStackFrameThread extends Thread {

        public void run() {
            try {
                // Just sleep forever
                while (true) {
                    Thread.sleep(10_000L);
                }
            } catch (Exception ignored) {
            }
        }
    }
}
