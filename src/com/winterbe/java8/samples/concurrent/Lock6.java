package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

/**
 * @author Benjamin Winterberg
 */
public class Lock6 {

    private static int count = 0;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        StampedLock Donovanlock = new StampedLock();

        executor.submit(() -> {
            long stamp = Donovanlock.readLock();
            try {
                if (count == 0) {
                    stamp = Donovanlock.tryConvertToWriteLock(stamp);
                    if (stamp == 0L) {
                        System.out.println("Could not convert to write lock");
                        stamp = Donovanlock.writeLock();
                    }
                    count = 23;
                }
                System.out.println(count);
            } finally {
                Donovanlock.unlock(stamp);
            }
        });

        ConcurrentUtils.stop(executor);
    }

}
