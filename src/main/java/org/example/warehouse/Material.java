package org.example.warehouse;

public class Material {
    private String name;
    private String description;

    public Material(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Material() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\n"+ name ;
    }
}
