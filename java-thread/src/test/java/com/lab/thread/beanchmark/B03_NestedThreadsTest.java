package com.lab.thread.beanchmark;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.lab.thread.beanchmark.B00_ConcurrencySupport.*;

@Slf4j
public class B03_NestedThreadsTest {

    @Test
    public void shouldExecuteIterationsConcurrently() throws InterruptedException {
        start();

        List<Thread> threads = new ArrayList<>();
        for (int user = 1; user <= USERS; user++) {
            Thread thread = new Thread(new UserFlow(user));
            thread.start();
            threads.add(thread);
        }

        // Not the most optimal but gets the work done
        for (Thread thread : threads) {
            thread.join();
        }

        stop(USERS + USERS * PERSISTENCE_FORK_FACTOR,
                SERVICE_A_LATENCY + SERVICE_B_LATENCY + PERSISTENCE_LATENCY);
    }

    static class UserFlow implements Runnable {

        private final int user;

        UserFlow(int user) {
            this.user = user;
        }

        @SneakyThrows
        @Override
        public void run() {
            String serviceA = serviceA(user);
            String serviceB = serviceB(user);

            List<Thread> threads = new ArrayList<>();
            for (int i = 1; i <= PERSISTENCE_FORK_FACTOR; i++) {
                Thread thread = new Thread(new Persistence(i, serviceA, serviceB));
                thread.start();
                threads.add(thread);
            }

            // Stop Condition - Not the most optimal but gets the work done
            for (Thread thread : threads) {
                thread.join();
            }
        }
    }

    static class Persistence implements Runnable {

        private final int fork;
        private final String serviceA;
        private final String serviceB;

        Persistence(int fork, String serviceA, String serviceB) {
            this.fork = fork;
            this.serviceA = serviceA;
            this.serviceB = serviceB;
        }

        @Override
        public void run() {
            persistence(fork, serviceA, serviceB);
        }
    }
}