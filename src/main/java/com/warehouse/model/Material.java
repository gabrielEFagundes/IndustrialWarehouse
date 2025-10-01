package com.warehouse.model;

public class Material {

    private int id;
    private String name;
    private String measurement;
    private double storage;

    public Material(int id, String name, String measurement, double storage) {
        this.id = id;
        this.name = name;
        this.measurement = measurement;
        this.storage = storage;
    }

    public Material(String name, String measurement, double storage) {
        this.name = name;
        this.measurement = measurement;
        this.storage = storage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public double getStorage() {
        return storage;
    }

    public void setStorage(double storage) {
        this.storage = storage;
    }
}
