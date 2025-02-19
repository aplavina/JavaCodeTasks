package com.aplavina.concurrency;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class ComplexTask {
    private final String taskName;
    private final CyclicBarrier cyclicBarrier;

    public void execute() {
        try {
            System.out.println(taskName + " started");
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 6000));
            System.out.println(taskName + " is complete");
            cyclicBarrier.await();
            System.out.println(taskName + " is synchronized with other tasks");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            throw new IllegalStateException(e);
        }
    }
}
