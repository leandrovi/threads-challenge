/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import entities.Product;
import jobs.AuditManager;
import java.time.Instant;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author leandro
 */
public class Program {
    public static void main(String[] args) throws InterruptedException {
        System.out.printf("%s - Start of main thread\n\n", Instant.now().toString());
        
        AuditManager.getInstance().activate();        
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Please, enter new product data:");        
        System.out.print("Name: ");
        String name = sc.nextLine();
        
        System.out.print("Price: ");
        double price = sc.nextDouble();
        
        Product product = new Product(name, price);
        AuditManager.getInstance().addAuditMessage("New product added: " + product);
        Thread.sleep(100);
        
        System.out.println();        
        System.out.print("Enter the number of products to be added in stock: ");
        int quantity = sc.nextInt();
        
        product.addProducts(quantity);
        AuditManager.getInstance().addAuditMessage("Quantity added to stock: " + product);
        Thread.sleep(100);
        
        System.out.println();
        System.out.print("Enter the number of products to be removed from stock: ");
        quantity = sc.nextInt();
        
        product.removeProducts(quantity);
        AuditManager.getInstance().addAuditMessage("Quantity removed from stock: " + product);
        Thread.sleep(100);

        System.out.println();        
        sc.close();
        
        try {            
            Thread.sleep(2000); 
        } finally {
            AuditManager.getInstance().deactivate();
        }
        
        System.out.printf("%s - End of main thread\n", Instant.now().toString());
    }
}
