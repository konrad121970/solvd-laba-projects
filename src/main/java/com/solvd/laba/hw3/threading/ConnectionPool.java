package com.solvd.laba.hw3.threading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;
    private final BlockingQueue<Connection> pool;

    private ConnectionPool(int poolSize) {
        this.pool = new LinkedBlockingQueue<>(poolSize);

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
        return pool.take();
    }

    /**
     * Get connection using ICompletionStage
     *
     * @returns CompletableFuture object
     */
    public CompletionStage<Connection> getConnectionAsync() {
        CompletableFuture<Connection> future = new CompletableFuture<>();
        try {
            Connection connection = pool.take();
            future.complete(connection);
        } catch (InterruptedException e) {
            future.completeExceptionally(e);
        }
        return future;
    }

    public void releaseConnection(Connection connection) {
        pool.offer(connection);
    }

}
