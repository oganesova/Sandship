package org.example.warehouse;

public class MaterialType extends Material {
private String icon;
private int maxCapacity;

public MaterialType(String name, String description, String icon, int maxCapacity) {
        super(name, description);
        this.icon = icon;
        this.maxCapacity = maxCapacity;
        }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return "\nicon = '" + icon + '\'' +
                ", maxCapacity = " + maxCapacity;
    }
}
