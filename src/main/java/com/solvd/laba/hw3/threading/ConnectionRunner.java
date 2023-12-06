package com.solvd.laba.hw3.threading;

public class ConnectionRunner implements Runnable {

    private final ConnectionPool connectionPool;

    public ConnectionRunner(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void run() {
        try {
            Connection connection = connectionPool.getConnection();
            System.out.println(Thread.currentThread().getName() + " got connection: " + connection + " " + connectionPool);

            connection.incrementNumber();

            //Thread.sleep(2000); // Delay to simulate work

            connectionPool.releaseConnection(connection);
            System.out.println(Thread.currentThread().getName() + " released connection: " + connection);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
