/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author leandro
 */
public class Product {
    String name;
    private double price;
    private int quantity;
    
    public Product() { }
    
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
    
    public double totalValueInStock() {
        return price * quantity;
    }
    
    public void addProducts(int quantity) {
        this.quantity += quantity;
    }
    
    public void removeProducts(int quantity) {
        this.quantity -= quantity;
    }
    
    @Override
    public String toString() {
        return name
            + ", $ " + String.format("%.2f", price)
            + ", " + quantity + " units"
            + ", Total: $ " + String.format("%.2f", totalValueInStock());
    }
}
