package com.aplavina.concurrency;

public class MyBlockingQueue<T> {
    private T[] queue;
    private int size;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked")
    public MyBlockingQueue(int maxSize) {
        this.queue = (T[]) new Object[maxSize];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    public synchronized void enqueue(T element) {
        while (size == queue.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue[tail] = element;
        tail = (tail + 1) % queue.length;
        size++;
        notifyAll();
    }

    public synchronized T dequeue() {
        while (size == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        T elemet = queue[head];
        head = (head + 1) % queue.length;
        size--;
        notifyAll();
        return elemet;
    }

    public synchronized int size() {
        return size;
    }

    public static void main(String[] args) {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(2);
        Thread consumer = new Thread(() -> {
            System.out.println("Consumer trying to dequeue...");
            System.out.println("Dequeued: " + queue.dequeue());
        });
        consumer.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        queue.enqueue(100);
        System.out.println("Enqueued: 100");
    }
}
