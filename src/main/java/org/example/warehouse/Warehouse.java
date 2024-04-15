package org.example.warehouse;

import org.example.warehouse.observer.Observer;
import org.example.warehouse.observer.Subject;

import javax.naming.InsufficientResourcesException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warehouse<T extends Material> implements WarehouseService<T>, Subject<Map<T, Integer>> {
    private Map<T, Integer> inventory;
    private List<Observer<Map<T, Integer>>> observers;
    private final int maxCapacity;

    public Warehouse(int maxCapacity) {
        this.inventory = new HashMap<>();
        this.observers = new ArrayList<>();
        this.maxCapacity = maxCapacity;

    }

    @Override
    public void addMaterial(T material, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        inventory.put(material, inventory.getOrDefault(material, 0) + quantity);
         if (quantity > maxCapacity) {
            throw new IllegalArgumentException("Cannot add material, warehouse capacity exceeded.");
        }
        System.out.println("Added " + quantity + " " + material.getName() + " to warehouse");
        notifyObservers(inventory);
    }

    @Override
    public void removeMaterial(T material, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        int currentQuantity = inventory.getOrDefault(material, 0);
        if (currentQuantity >= quantity) {
            inventory.put(material, currentQuantity - quantity);
            System.out.println("Removed " + quantity + " " + material.getName() + " from warehouse");
            notifyObservers(inventory);
        } else {
            throw new IllegalArgumentException("Insufficient quantity of " + material + " in warehouse");
        }
    }

    @Override
    public void moveMaterial(Warehouse<T> source, T material, int quantity) {
        String materialName = material.getName();
        int availableSpace = source.maxCapacity - source.getInventorySize();

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (quantity > availableSpace) {
            throw new IllegalArgumentException("Insufficient quantity of " + materialName + " in source warehouse.");
        }
        if (quantity > maxCapacity - getInventorySize()) {
            throw new IllegalArgumentException("Cannot move material, destination warehouse capacity exceeded.");
        }

        this.removeMaterial(material, quantity);
        source.addMaterial(material, quantity);
        System.out.println("Moved " + quantity + " " + materialName + " from Warehouse to Destination");
    }

    private int getInventorySize() {
        int totalItems = 0;
        for (int quantity : inventory.values()) {
            totalItems += quantity;
        }
        return totalItems;
    }

    @Override
    public int getMaterialQuantity(T material) {
        return inventory.getOrDefault(material, 0);
    }

    @Override
    public void printWarehouseData() {
        System.out.println("Warehouse Inventory:");
        for (Map.Entry<T, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    @Override
    public void registerObserver(Observer<Map<T, Integer>> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<Map<T, Integer>> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Map<T, Integer> data) {
        for (Observer<Map<T, Integer>> observer : observers) {
            observer.update(data);
        }
    }
}
