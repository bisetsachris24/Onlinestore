package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class StoreApp {

        private List<Product> inventory;
        private Scanner scanner;

        public StoreApp(List<Product> inventory) {
            this.inventory = inventory;
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
                        System.out.println("(Products screen coming next...)");
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
    }


