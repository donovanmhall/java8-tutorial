package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Benjamin Winterberg
 */
public class CompletableFuture1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> donovanfuture = new CompletableFuture<>();

        donovanfuture.complete("42");

        donovanfuture
                .thenAccept(System.out::println)
                .thenAccept(v -> System.out.println("done"));

    }

}
