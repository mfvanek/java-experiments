package io.github.mfvanek.revolut.live.coding;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Revolut Live Coding Interview.
 */
public class RevolutLoadBalancerTask {

    /*
    We will ask you to analyse and implement one requirement at a time, so please focus on it only.

Implement a Load Balancer, which is a database of services, their instances and their locations.
In general, The service registry needs to be updated each time a new service comes online.

For this exercise, we'll build an imitation of a Load Balancer, which will be a simple object.
No network calls should be made. Data should be stored in-memory instead of a real database.

- It should be possible to register an instance, identified by an address
- Each address should be unique, it should not be possible to register the same address more than once
- Load Balancer should accept up to 10 addresses

Develop an algorithm that, when invoking the Load Balancer 's get() method multiple times, should return one backend-instance choosing between the registered ones randomly.
     */
    public static void main(String[] args) {
        System.out.print("Hello and welcome!");
    }

    static int calc(int a, int b) {
        return a + b;
    }

    public static class LoadBalancer {

        private final List<String> registry = new CopyOnWriteArrayList<>();
        private final Lock lock = new ReentrantLock();

        public RegisterResult register(String backend) {
            lock.lock();
            try {
                if (registry.size() < 10) {
                    Set<String> backends = new HashSet<>(registry);
                    if (backends.contains(backend)) {
                        return RegisterResult.ALREADY_REGISTERED;
                    }
                    registry.add(backend);
                    return RegisterResult.SUCCESS;
                }
                return RegisterResult.QUOTA_EXCEEDED;
            } finally {
                lock.unlock();
            }
        }

        public int size() {
            return registry.size();
        }

        public String get() {
            if (registry.isEmpty()) {
                throw new IllegalStateException("Registry is empty");
            }
            return registry.get(new Random().nextInt(registry.size())); // FIXME Inject random generator
        }

        public enum RegisterResult {
            SUCCESS,
            ALREADY_REGISTERED,
            QUOTA_EXCEEDED
        }
    }
}
