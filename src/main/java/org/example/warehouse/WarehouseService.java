package org.example.warehouse;

public interface WarehouseService<T extends Material> {
    void addMaterial(T material, int quantity);
    void removeMaterial(T material, int quantity);
    void moveMaterial(Warehouse<T> source, T material, int quantity);
    int getMaterialQuantity(T material);
    void printWarehouseData();
}
