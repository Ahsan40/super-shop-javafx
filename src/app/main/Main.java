package app.main;

import app.classes.Cart;
import app.classes.Product;
import app.classes.User;
import app.utils.SceneManager;
import app.utils.Size;
import app.utils.Utils;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Main extends Application {
    public static User user;
    public static Cart cart;
    public static ArrayList<Product> history;
    public static ArrayList<Product> allProducts;
    public static SceneManager sceneMan;
    public static Socket sc;
    public static ObjectOutputStream sendObj;
    public static ObjectInputStream receiveObj;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws URISyntaxException {
        // Scene Configs
        stage.setTitle(Config.title + " " + Config.version);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource(Config.icon)).toURI().toString()));
        sceneMan = new SceneManager(stage);
        sceneMan.setDefaultSize(new Size(Config.defaultHeight, Config.defaultWeight));

        // Configure
        try {
            sc = new Socket(Config.server, Config.port);
            OutputStream oo = sc.getOutputStream();
            sendObj = new ObjectOutputStream(oo);

            InputStream inputStream = sc.getInputStream();
            receiveObj = new ObjectInputStream(inputStream);

            // Determining Page to Open
            sceneMan.open("home", Config.homePage, 400, 650);
        } catch (Exception e) {
            sceneMan.open("error", Config.errorPage, 400, 650);
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
        Utils.exit();
    }
}