package org.example.warehouse.observer;

import java.util.Map;

public class InventoryObserver<T> implements Observer<Map<T, Integer>> {
    @Override
    public void update(Map<T, Integer> data) {
       System.out.println("Inventory update received:");
       /* for (Map.Entry<T, Integer> entry : data.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }*/
    }
}