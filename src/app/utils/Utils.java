package app.utils;

import app.classes.Product;
import app.main.Config;
import app.main.Main;
import app.classes.User;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Optional;

public class Utils {
    public static String sha256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            final StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                final String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public static <T> T fetchData(String str, User user) {
        T data;
        try {
            Main.sendObj.writeObject(str);
            if (user != null)
                Main.sendObj.writeObject(user);
            data = (T) Main.receiveObj.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public static <T> T fetchData(String str) {
        return fetchData(str, null);
    }
    public static boolean checkLogin(User userData, ObjectInputStream receiveObj, ObjectOutputStream sendObj) {
        try {
            // sending credentials
            System.out.println(" - Sending credentials");
            System.out.println(" - Requesting for login");
            System.out.println(" - Passwords Hash: " + userData.getPasswords());
            sendObj.writeObject("login");
            sendObj.writeObject(userData);

            // reading response
            String response = (String) receiveObj.readObject();
            System.out.println(" - Received response: " + response);
            if (response.equalsIgnoreCase("SUCCESS")) {
                System.out.println(" - Received logged user info from server");
                Main.user = (User) receiveObj.readObject();


                // fetching data
                System.out.println(" - Received product info from server");
                Main.allProducts = fetchData("getProductList");

                System.out.println(" - Received user cart info from server");
                Main.cart = fetchData("getCart", Main.user);

                System.out.println(" - Received user purchases info from server");
                Main.history = fetchData("getHistory", Main.user);
//                FileIO.writeObjToFile(Main.user, Config.userTempData);    // writing info to a temp file
                return true;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean removeFile(String path) {
        File f = new File(path);
        return f.delete();
    }

    public static void exit() {
        if (removeFile(Config.userTempData))
            System.out.println(" - Deleted temp user data " + Config.userTempData);
        System.out.println(" - Exiting from program");
        System.exit(0);
    }


    // alerts
    public static void alert(String title, String text, String type) {
        // Alert Type
        Alert alert;
        if (type.equalsIgnoreCase("error"))
            alert = new Alert(Alert.AlertType.ERROR);
        else if (type.equalsIgnoreCase("success"))
            alert = new Alert(Alert.AlertType.INFORMATION);
        else
            alert = new Alert(Alert.AlertType.WARNING);

        // Alert Window Settings
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);

        alert.showAndWait();
    }

    public static double inputAlert(String title, String query) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(query);

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();

        if (result.isEmpty()) {
            // action canceled
            return 0;
        }
        try {
            return Double.parseDouble(result.get());
        } catch (NumberFormatException e) {
            alert("Error!", "Invalid Input! Please try again!", "error");
        }
        return 0;
    }


    public static double inputAlert() {
        return inputAlert("Input", "Please Money to Add: ");
    }
}
