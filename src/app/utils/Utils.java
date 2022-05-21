package app.utils;

import app.main.Config;
import app.main.Main;
import app.main.User;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

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
            if (response.contains("SUCCESS")) {
                System.out.println(" - Received logged user info from server");
                Main.user = (User) receiveObj.readObject();
                System.out.println(" - Saving user info for later use");
                FileIO.writeObjToFile(Main.user, Config.userTempData);    // writing info to a temp file
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
}
