package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoreApp {

    private final List<Product> inventory;
    private final List<Product> cart;
     Scanner theScanner = new Scanner(System.in);

    public StoreApp(List<Product> inventory) {
        this.inventory = inventory;
        this.cart = new ArrayList<>();
        this.theScanner = new Scanner(System.in);
    }


    //  SCREEN 1 – HOME SCREEN

    public void showHomeScreen() {
        boolean running = true;

        while (running) {
            System.out.println("\n─────────────────────────────────");
            System.out.println("      WELCOME TO THE ONLINE STORE");
            System.out.println("─────────────────────────────────");
            System.out.println("1) Display Products");
            System.out.println("2) Display Cart (" + cart.size() + " item(s))");
            System.out.println("3) Exit");
            System.out.println("─────────────────────────────────");
            System.out.print("Choose an option: ");

            String choice = theScanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showProductsScreen();
                    break;
                case "2":
                    showCartScreen();
                    break;
                case "3":
                    System.out.println("\nThanks for shopping with us. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, or 3.");
            }
        }
    }


    //  SCREEN 2 – DISPLAY PRODUCTS

    private void showProductsScreen() {
        boolean onProductsScreen = true;
        List<Product> displayList = new ArrayList<>(inventory);

        while (onProductsScreen) {
            System.out.println("\n─────────────────────────────────────────────────────────────────");
            System.out.println("  PRODUCTS");
            System.out.println("─────────────────────────────────────────────────────────────────");
            System.out.printf("  %-8s | %-38s | %-8s | %s%n", "SKU", "Name", "Price", "Department");
            System.out.println("─────────────────────────────────────────────────────────────────");
            for (Product p : displayList) {
                System.out.println("  " + p);
            }
            System.out.println("─────────────────────────────────────────────────────────────────");
            System.out.println("S) Search products");
            System.out.println("A) Add product to cart");
            System.out.println("B) Back to Home");
            System.out.println("─────────────────────────────────────────────────────────────────");
            System.out.print("Choose an option: ");

            String choice = theScanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "S":
                    displayList = searchProducts();
                    break;
                case "A":
                    addProductToCart(displayList);
                    break;
                case "B":
                    displayList = new ArrayList<>(inventory);
                    onProductsScreen = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private List<Product> searchProducts() {
        System.out.println("\n─────────────────────────────────");
        System.out.println("  SEARCH BY:");
        System.out.println("  1) Product Name");
        System.out.println("  2) Price (max price)");
        System.out.println("  3) Department");
        System.out.println("  4) Show all products");
        System.out.println("─────────────────────────────────");
        System.out.print("Choose an option: ");

        String choice = theScanner.nextLine().trim();
        List<Product> results = new ArrayList<>();

        switch (choice) {
            case "1":
                System.out.print("Enter product name to search: ");
                String name = theScanner.nextLine().trim().toLowerCase();
                for (Product p : inventory) {
                    if (p.getName().toLowerCase().contains(name)) {
                        results.add(p);
                    }
                }
                System.out.println("Found " + results.size() + " result(s).");
                break;

            case "2":
                System.out.print("Enter maximum price: $");
                try {
                    double maxPrice = Double.parseDouble(theScanner.nextLine().trim());
                    for (Product p : inventory) {
                        if (p.getPrice() <= maxPrice) {
                            results.add(p);
                        }
                    }
                    System.out.printf("Found %d result(s) under $%.2f.%n", results.size(), maxPrice);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price. Showing all products.");
                    results = new ArrayList<>(inventory);
                }
                break;

            case "3":
                System.out.print("Enter department: ");
                String dept = theScanner.nextLine().trim().toLowerCase();
                for (Product p : inventory) {
                    if (p.getDepartment().toLowerCase().contains(dept)) {
                        results.add(p);
                    }
                }
                System.out.println("Found " + results.size() + " result(s).");
                break;

            case "4":
            default:
                results = new ArrayList<>(inventory);
                System.out.println("Showing all products.");
                break;
        }

        return results;
    }

    private void addProductToCart(List<Product> displayList) {
        System.out.print("Enter the SKU of the product to add: ");
        String sku = theScanner.nextLine().trim().toUpperCase();

        for (Product p : displayList) {
            if (p.getSku().equalsIgnoreCase(sku)) {
                cart.add(p);
                System.out.println("\"" + p.getName() + "\" added to your cart!");
                return;
            }
        }
        System.out.println("SKU not found. Please try again.");
    }

    // ─────────────────────────────────────────────
    //  SCREEN 3 – CART
    // ─────────────────────────────────────────────
    private void showCartScreen() {
        boolean onCartScreen = true;

        while (onCartScreen) {
            System.out.println("\n─────────────────────────────────────────────────────────────────");
            System.out.println("  YOUR CART");
            System.out.println("─────────────────────────────────────────────────────────────────");

            if (cart.isEmpty()) {
                System.out.println("  Your cart is empty.");
            } else {
                System.out.printf("  %-8s | %-38s | %s%n", "SKU", "Name", "Price");
                System.out.println("─────────────────────────────────────────────────────────────────");
                for (Product p : cart) {
                    System.out.println("  " + p);
                }
                System.out.println("─────────────────────────────────────────────────────────────────");
                System.out.printf("  TOTAL: $%.2f%n", getCartTotal());
            }

            System.out.println("─────────────────────────────────────────────────────────────────");
            System.out.println("C) Check Out");
            System.out.println("R) Remove a product");
            System.out.println("B) Back to Home");
            System.out.println("─────────────────────────────────────────────────────────────────");
            System.out.print("Choose an option: ");

            String choice = theScanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "C":
                    checkOut();
                    onCartScreen = false;
                    break;
                case "R":
                    removeProductFromCart();
                    break;
                case "B":
                    onCartScreen = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void removeProductFromCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is already empty.");
            return;
        }
        System.out.print("Enter the SKU of the product to remove: ");
        String sku = theScanner.nextLine().trim().toUpperCase();

        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getSku().equalsIgnoreCase(sku)) {
                Product removed = cart.remove(i);
                System.out.println("\"" + removed.getName() + "\" removed from your cart.");
                return;
            }
        }
        System.out.println("SKU not found in your cart.");
    }

    private void checkOut() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to check out.");
            return;
        }
        System.out.println("\n─────────────────────────────────────────────────────────────────");
        System.out.println("  ORDER SUMMARY");
        System.out.println("─────────────────────────────────────────────────────────────────");
        for (Product p : cart) {
            System.out.println("  " + p);
        }
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.printf("  ORDER TOTAL: $%.2f%n", getCartTotal());
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.println("  Thank you for your purchase! Your order has been placed.");
        System.out.println("─────────────────────────────────────────────────────────────────");
        cart.clear();
    }

    private double getCartTotal() {
        double total = 0;
        for (Product p : cart) {
            total += p.getPrice();
        }
        return total;
    }
}
