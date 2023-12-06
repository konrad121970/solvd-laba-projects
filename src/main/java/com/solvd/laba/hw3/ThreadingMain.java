package com.solvd.laba.hw3;

import com.solvd.laba.hw3.threading.ConnectionPool;
import com.solvd.laba.hw3.threading.ConnectionRunner;

import java.util.ArrayList;
import java.util.List;

import static com.solvd.laba.hw3.threading.Connection.number;

public class ThreadingMain {

    public static void main(String[] args) {

        runThreads();

    }

    public static void runThreads() {

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            Thread thread = new Thread(new ConnectionRunner(ConnectionPool.getInstance(5)));
            thread.start();
            threads.add(thread);
        }

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Final counter value: " + number);

    }

   /* public static void runCompletableThreads() {

        ConnectionPool connectionPool = ConnectionPool.getInstance(5);

        ExecutorService threadPool = Executors.newFixedThreadPool(7);

        // Submit 5 tasks to obtain connections
        for (int i = 0; i < 7; i++) {
            threadPool.submit(() -> {
                try {
                    Connection connection = connectionPool.getConnection();
                    System.out.println("Thread " + Thread.currentThread().getId() +
                            " obtained connection: " + connection);

                    CompletableFuture<Void> workFuture = CompletableFuture
                            .supplyAsync(() -> {
                                connection.doWork1();
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                return connection;
                            })
                            .thenApply(conn -> {
                                conn.doWork2DependendOnWork1();
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                return conn;
                            })
                            .thenAccept(conn -> {
                                connectionPool.releaseConnection(conn);
                               *//* System.out.println("Thread " + Thread.currentThread().getId() +
                                        " released connection: " + connection);*//*
                            })
                            .exceptionally(ex -> {
                                System.err.println("Error performing work: " + ex.getMessage());
                                return null;
                            });
                    workFuture.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        }

    }
*/

}
