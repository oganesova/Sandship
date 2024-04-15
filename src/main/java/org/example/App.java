package org.example;

import org.example.warehouse.Material;
import org.example.warehouse.Warehouse;

public class App {
    public static void main(String[] args) {

        Material wood = new Material("Wood", "This is wood");
        Material steel = new Material("Steel", "This is steel");
        Material plastic = new Material("Plastic", "This is plastic");

        Warehouse<Material> warehouse1 = new Warehouse<>(200);
        Warehouse<Material> warehouse2 = new Warehouse<>(150);
        Warehouse<Material> warehouse3 = new Warehouse<>(300);

        warehouse1.addMaterial(wood, 100);
        warehouse1.addMaterial(steel, 50);

        warehouse2.addMaterial(plastic, 80);

        System.out.println("Warehouse 1 Inventory:");
        warehouse1.printWarehouseData();

        System.out.println("\nWarehouse 2 Inventory:");
        warehouse2.printWarehouseData();

        warehouse1.moveMaterial(warehouse2, wood, 40);
        warehouse1.moveMaterial(warehouse2, steel, 25);

        System.out.println("\nWarehouse 1 Inventory After Move:");
        warehouse1.printWarehouseData();

        System.out.println("\nWarehouse 2 Inventory After Move:");
        warehouse2.printWarehouseData();

        warehouse1.removeMaterial(wood, 20);
        warehouse1.removeMaterial(steel, 15);

        warehouse3.addMaterial(wood, 30);
        warehouse3.addMaterial(plastic, 50);

        System.out.println("\nWarehouse 1 Inventory After Removal:");
        warehouse1.printWarehouseData();

        System.out.println("\nWarehouse 3 Inventory:");
        warehouse3.printWarehouseData();
    }
}
