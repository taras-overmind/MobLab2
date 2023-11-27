package com.taras_overmind.myapplication;

public class CarModel {

    private int id;
    private String brand;
    private String bodyType;
    private String color;
    private double engineCapacity;
    private double price;

    public CarModel(int id, String brand, String bodyType, String color, double engineCapacity, double price) {
        this.id = id;
        this.brand = brand;
        this.bodyType = bodyType;
        this.color = color;
        this.engineCapacity = engineCapacity;
        this.price = price;
    }

    public CarModel(String brand, String bodyType, String color, double engineCapacity, double price) {
        this.brand = brand;
        this.bodyType = bodyType;
        this.color = color;
        this.engineCapacity = engineCapacity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return
                brand + "   " +
                bodyType + '\n' +
                color + '\n' +
                engineCapacity + "cc   "+
                price +'$' ;

    }
}
