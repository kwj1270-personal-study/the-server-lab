package com.lab.thread.beanchmark;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import static com.lab.thread.beanchmark.B00_ConcurrencySupport.*;

@Slf4j
public class B07_CompletableFutureTest {

    private static final ForkJoinPool commonPool = new ForkJoinPool(2000);

    @Test
    public void shouldExecuteIterationsConcurrently() throws InterruptedException, ExecutionException {
        start();

        CompletableFuture.allOf(IntStream.rangeClosed(1, USERS)
                .boxed()
                .map(this::userFlow)
                .toArray(CompletableFuture[]::new)
        ).get();

        stop();
    }

    @SneakyThrows
    private CompletableFuture<String> userFlow(int user) {
        return CompletableFuture.supplyAsync(() -> serviceA(user), commonPool)
                .thenCombine(CompletableFuture.supplyAsync(() -> serviceB(user), commonPool), this::persist);
    }

    @SneakyThrows
    private String persist(String serviceA, String serviceB) {
        CompletableFuture.allOf(IntStream.rangeClosed(1, PERSISTENCE_FORK_FACTOR)
                .boxed()
                .map(iteration -> CompletableFuture.runAsync(() -> persistence(iteration, serviceA, serviceB), commonPool))
                .toArray(CompletableFuture[]::new)
        ).join();
        return "";
    }
}