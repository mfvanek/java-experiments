package io.github.mfvanek.jvm.internals;

public class FullStackFrameProgram {

    public static void main(String[] args) {
        // Create 1000 threads with full stack
        for (int counter = 0; counter < 1000; ++counter) {
            new FullStackFrameThread().start();
        }
    }

    static class FullStackFrameThread extends Thread {

        public void run() {
            try {
                int x = 0;
                simpleMethod(x);
            } catch (Exception ignored) {
            }
        }

        // Loop for 10,000 times and then sleep. So that stack will be filled up.
        private void simpleMethod(int x) throws Exception {

            // Creating local variables to fill up the stack.
            float y = 1.2f * x;
            double z = 1.289898d * x;

            // Looping for 10,000 iterations to fill up the stack.
            if (x < 10000) {
                simpleMethod(++x);
            }

            // After 10,000 iterations, sleep forever
            while (true) {
                Thread.sleep(10000);
            }
        }
    }
}
