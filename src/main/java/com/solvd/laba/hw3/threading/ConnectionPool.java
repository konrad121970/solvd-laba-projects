package com.solvd.laba.hw3.threading;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;
    private final Queue<Connection> pool;
    private final int poolSize;

    private ConnectionPool(int poolSize) {
        this.pool = new LinkedList<>(); // Linked List implements Queue
        this.poolSize = poolSize;

        for (int i = 0; i < poolSize; i++) {
            pool.add(new Connection());
        }
    }

    /*Lazy init*/
    public static synchronized ConnectionPool getInstance(int poolSize) {
        if (instance == null) {
            instance = new ConnectionPool(poolSize);
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        synchronized (pool){
            while(pool.isEmpty()){
                pool.wait();
            }
        }
        return pool.poll();
    }

    /**
     * Get connection using ICompletionStage
     *
     * @return CompletableFuture object
     */
    public CompletionStage<Connection> getConnectionAsync() {
        CompletableFuture<Connection> future = new CompletableFuture<>();
        Connection connection = pool.poll();
        future.complete(connection);
        return future;
    }

    public void releaseConnection(Connection connection) {
        synchronized (pool){
            pool.offer(connection);
            pool.notify(); // Notify threads waiting on monitor
        }
    }

}
