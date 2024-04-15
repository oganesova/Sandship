package org.example;
import org.example.warehouse.Material;
import org.example.warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.naming.InsufficientResourcesException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
class WarehouseTest {

    private Warehouse<Material> warehouse;
    private Warehouse<Material> sourceWarehouse;
    private Warehouse<Material> destinationWarehouse;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse<>(100);
        sourceWarehouse = new Warehouse<>(100);
        destinationWarehouse = new Warehouse<>(100);
    }

    @Test
    void moveMaterial_validMove() {
        Material material = new Material("Material A","Material A desc");
        sourceWarehouse.addMaterial(material, 50);

        sourceWarehouse.moveMaterial(destinationWarehouse, material, 30);

        assertEquals(20, sourceWarehouse.getMaterialQuantity(material));
        assertEquals(30, destinationWarehouse.getMaterialQuantity(material));
    }

    @Test
    void moveMaterial_insufficientSourceQuantity() {
        Material material = new Material("Material B","Material B desc");
        sourceWarehouse.addMaterial(material, 20);
assertThrows(IllegalArgumentException.class, () -> {
            sourceWarehouse.moveMaterial(destinationWarehouse, material, 30);
        });

        assertEquals(20, sourceWarehouse.getMaterialQuantity(material));
        assertEquals(0, destinationWarehouse.getMaterialQuantity(material));
    }
    @Test
    void moveMaterial_destinationFull() {
        Material material = new Material("Material C","Material C desc");
        sourceWarehouse.addMaterial(material, 50);
        destinationWarehouse.addMaterial(material, 100);

        sourceWarehouse.moveMaterial(destinationWarehouse, material, 30);

        assertEquals(50, sourceWarehouse.getMaterialQuantity(material));
        assertEquals(100, destinationWarehouse.getMaterialQuantity(material));
    }

    @Test
    void addMaterial_validQuantity() {
        Material material = new Material("Material A","Material A desc");
        warehouse.addMaterial(material, 50);
        assertEquals(50, warehouse.getMaterialQuantity(material));
    }

    @Test
    void addMaterial_invalidQuantity() {
        Material material = new Material("Material B","Material B desc");
        assertThrows(IllegalArgumentException.class, () -> warehouse.addMaterial(material, 0));
    }

    @Test
    void removeMaterial_validQuantity() {
        Material material = new Material("Material C","Material C desc");
        warehouse.addMaterial(material, 100);
        warehouse.removeMaterial(material, 50);
        assertEquals(50, warehouse.getMaterialQuantity(material));
    }

    @Test
    void removeMaterial_insufficientQuantity() {
        Material material = new Material("Material D","Material D desc");
        assertThrows(IllegalArgumentException.class, () -> warehouse.removeMaterial(material, 100));
    }
}