package org.example.warehouse.observer;

public interface Observer<T> {
    void update(T data);
}
