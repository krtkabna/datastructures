package com.wasp.datastructures.list;

public class IteratingEmptyListException extends RuntimeException {

    public IteratingEmptyListException() {
        super("Trying to iterate on empty list");
    }

    public IteratingEmptyListException(String message) {
        super(message);
    }
}
