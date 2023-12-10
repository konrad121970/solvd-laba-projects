package com.solvd.laba.hw3;

import com.solvd.laba.hw3.threading.Connection;
import com.solvd.laba.hw3.threading.ConnectionPool;
import com.solvd.laba.hw3.threading.ConnectionRunner;
import com.solvd.laba.hw3.threading.ConnectionRunnerAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThreadingMain {

    public static void main(String[] args) {

        runThreads();

        System.out.println("ASYNC");
        runThreadsAsync();

    }

    public static void runThreads() {

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            Thread thread = new Thread(new ConnectionRunner(ConnectionPool.getInstance(5)));
            thread.run();
            threads.add(thread);
        }

        // Wait to end each thread.
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });

        System.out.println("Final counter value: " + Connection.getNumber());

    }

    public static void runThreadsAsync() {

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);

        for (int i = 0; i < 7; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                ConnectionRunnerAsync connectionRunner = new ConnectionRunnerAsync(connectionPool);
                connectionRunner.run();
            });

            futures.add(future);
        }

        // Wait to end each thread.
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        try {
            allOf.get(); // Wait for all CompletableFuture to complete
            System.out.println("Final counter value: " + Connection.getNumber());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }

    }

}
