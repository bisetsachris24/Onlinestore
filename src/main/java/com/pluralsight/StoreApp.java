package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoreApp {

    private List<Product> inventory;
    private List<Product> cart;
    private Scanner scanner;

    public StoreApp(List<Product> inventory) {
        this.inventory = inventory;
        this.cart = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // ─────────────────────────────────────────────
    //  SCREEN 1 – HOME SCREEN
    // ─────────────────────────────────────────────
    public void showHomeScreen() {
        boolean running = true;

        while (running) {
            System.out.println("\n─────────────────────────────────");
            System.out.println("      WELCOME TO THE ONLINE STORE");
            System.out.println("─────────────────────────────────");
            System.out.println("1) Display Products");
            System.out.println("2) Display Cart");
            System.out.println("3) Exit");
            System.out.println("─────────────────────────────────");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showProductsScreen();
                    break;
                case "2":
                    System.out.println("(Cart screen coming next...)");
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

    // ─────────────────────────────────────────────
    //  SCREEN 2 – DISPLAY PRODUCTS
    // ─────────────────────────────────────────────
    private void showProductsScreen() {
        boolean onProductsScreen = true;
        List<Product> displayList = new ArrayList<>(inventory);

        while (onProductsScreen) {
            System.out.println("\n─");
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

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "S":
                    displayList = searchProducts();
                    break;
                case "A":
                    addProductToCart(displayList);
                    break;
                case "B":
                    displayList = new ArrayList<>(inventory); // reset search
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

        String choice = scanner.nextLine().trim();
        List<Product> results = new ArrayList<>();

        switch (choice) {
            case "1":
                System.out.print("Enter product name to search: ");
                String name = scanner.nextLine().trim().toLowerCase();
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
                    double maxPrice = Double.parseDouble(scanner.nextLine().trim());
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
                String dept = scanner.nextLine().trim().toLowerCase();
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
        String sku = scanner.nextLine().trim().toUpperCase();

        for (Product p : displayList) {
            if (p.getSku().equalsIgnoreCase(sku)) {
                cart.add(p);
                System.out.println("\"" + p.getName() + "\" added to your cart!");
                return;
            }
        }
        System.out.println("SKU not found. Please try again.");
    }
}
