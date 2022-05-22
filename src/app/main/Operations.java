package app.main;

import app.classes.Cart;
import app.classes.Product;

import java.io.IOException;
import java.util.ArrayList;

public class Operations {
    public static void addBalance(double amount) {
        try {
            Main.sendObj.writeObject("addBalance");
            Main.sendObj.writeObject(Main.user);
            Main.sendObj.writeObject(amount);
            String res  =  (String) Main.receiveObj.readObject();
            if (res.equalsIgnoreCase("SUCCESS")) {
                Main.user.addBalance(amount);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addToCart(Product p) {
        // add to Main.cart as well
        try {
            Main.sendObj.writeObject("addToCart");
            Main.sendObj.writeObject(Main.user);
            Main.sendObj.writeObject(p);
            String res  =  (String) Main.receiveObj.readObject();
            if (res.equalsIgnoreCase("SUCCESS")) {
                Main.cart.add(p);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void buy(Cart cart) {
        // add date when buy product
        try {
            Main.sendObj.writeObject("buy");
            Main.sendObj.writeObject(Main.user);
            Main.sendObj.writeObject(cart);
            String res  =  (String) Main.receiveObj.readObject();
            if (res.equalsIgnoreCase("SUCCESS")) {
                Main.history = (ArrayList<Product>) Main.receiveObj.readObject();
                Main.user.reduceBalance(Main.cart.getTotal());
                Main.cart.clear();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logout() {
        Main.user = null;
        Main.allProducts = null;
        Main.cart = null;
        Main.history = null;
        Main.sceneMan.open("login", Config.signInPage, 400, 650);
    }
}
