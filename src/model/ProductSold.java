package model;

/**
 * Clase que representa un producto dentro del sistema de ventas.
 * Cada producto tiene un ID, nombre, cantidad en stock, precio por unidad y cantidad vendida.
 * 
 */

public class ProductSold {
    private String id; // Identificador único del producto
    private String name; // Nombre del producto
    private double price;
    private int quantity;

    public ProductSold(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int qty) {
        this.quantity += qty;
    }
}
