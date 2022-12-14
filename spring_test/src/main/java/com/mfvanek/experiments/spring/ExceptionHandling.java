package com.mfvanek.experiments.spring;

public class ExceptionHandling {

    public static void main(String[] args) {
        try {
            doSomething();
        } catch (Throwable t) {
            System.out.println(t.getLocalizedMessage());
            for (Throwable initial : t.getSuppressed()) {
                System.out.println(initial.getLocalizedMessage());
            }
            if (t.getCause() != null) {
                System.out.println(t.getCause().getLocalizedMessage());
            }
        }
    }

    private static void doSomething() {
        Throwable throwable = null;
        try {
            doOnce();
        } catch(Throwable t) {
            throwable = t;
            throw t;
        } finally {
            try {
                closeResources();
            } catch (Throwable t) {
                if (throwable != null) {
                    t.addSuppressed(throwable);
                    throw t;
                }
            }
        }
    }

    private static void closeResources() {
        throw new UnsupportedOperationException("from closeResources");
    }

    private static void doOnce() {
        throw new RuntimeException("from doOnce");
    }
}
