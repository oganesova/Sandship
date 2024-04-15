package org.example.warehouse.observer;

import org.example.warehouse.observer.Observer;

public interface Subject<T> {
    void registerObserver(Observer<T> observer);

    void removeObserver(Observer<T> observer);

    void notifyObservers(T data);
}
