package com.guild.demo.model;

public class Artifact {
    private int id;
    private String name;
    private String element;
    private int powerLevel;

    public Artifact() {}

    public Artifact(int id, String name, String element, int powerLevel) {
        this.id = id;
        this.name = name;
        this.element = element;
        this.powerLevel = powerLevel;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getElement() { return element; }
    public void setElement(String element) { this.element = element; }

    public int getPowerLevel() { return powerLevel; }
    public void setPowerLevel(int powerLevel) { this.powerLevel = powerLevel; }
}
