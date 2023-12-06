package com.solvd.laba.hw3;

import com.solvd.laba.hw3.threading.Connection;
import com.solvd.laba.hw3.threading.ConnectionPool;
import com.solvd.laba.hw3.threading.ConnectionRunner;

import java.util.ArrayList;
import java.util.List;

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

        System.out.println("Final counter value: " + Connection.getNumber());

    }

}
