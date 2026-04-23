package com.pluralsight;

import java.util.List;

public class OnlineStoreApp {
    public static void main(String[] args) {

        List<Product> inventory = com.pluralsight.ProductLoader.loadProducts("src/main/resources/products.csv");

        StoreApp store = new StoreApp(inventory);
        store.showHomeScreen();

    }
}





