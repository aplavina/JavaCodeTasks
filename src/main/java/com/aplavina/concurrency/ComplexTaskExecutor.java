package com.aplavina.concurrency;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class ComplexTaskExecutor {
    private final ExecutorService pool;

    public void executeTasks(int numberOfTasks) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(numberOfTasks);
        IntStream.rangeClosed(1, numberOfTasks).forEach(
                taskNumber -> pool.submit(
                        () -> new ComplexTask("task " + taskNumber, cyclicBarrier).execute()
                ));
    }

    public static void main(String[] args) {
        ComplexTaskExecutor taskExecutor = new ComplexTaskExecutor(Executors.newCachedThreadPool());
        taskExecutor.executeTasks(30);
    }
}
