package com.pedro.PracticaMongoDB_05_PostgresSQL.exceptions;

public class ConnectionDbException extends RuntimeException {
    public ConnectionDbException(String message) {
        super(message);
    }
}
