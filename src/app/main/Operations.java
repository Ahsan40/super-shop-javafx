package app.main;

import app.classes.Cart;
import app.classes.Product;

import java.io.IOException;

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

    public static String addToCart(Product p) {
        // add to Main.cart as well
        return "";
    }

    public static String buy(Cart cart) {
        // add date when buy product
        return "";
    }
}
