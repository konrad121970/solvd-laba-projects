package com.solvd.laba.hw3.threading;

import java.util.concurrent.CompletionStage;

public class ConnectionRunnerAsync implements Runnable {
    private final ConnectionPool connectionPool;

    public ConnectionRunnerAsync(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void run() {
        CompletionStage<Connection> connectionFuture = connectionPool.getConnectionAsync();

        connectionFuture.thenAccept(connection -> {
            System.out.println(Thread.currentThread().getName() + " got connection: " + connection + " " + connectionPool);

            connection.incrementNumber();

            connectionPool.releaseConnection(connection);
            System.out.println(Thread.currentThread().getName() + " released connection: " + connection);
        }).exceptionally(e -> {
            throw new RuntimeException(e);
        });
    }
}

