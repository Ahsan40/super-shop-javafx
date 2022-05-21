package app.main;

public class Config {
    // App Info
    public static String title = "SuperShop";
    public static String version = "v1.0";
    public static String icon = "/res/logo.png";
    public static String userTempData = "temp.ser";
    public static double defaultHeight = 400;
    public static double defaultWeight = 650;


    // Server
    public static String server = "localhost";
    public static int port = 6611;
    public static String userDB = "usersDB.ser";

    // Pages
    public static String homePage = "/res/Home.fxml";
    public static String errorPage = "/res/Error.fxml";
    public static String dashboardPage = "/res/Dashboard.fxml";
    public static String signInPage = "/res/SignIn.fxml";
    public static String registrationPage = "/res/Registration.fxml";
    public static String adminHomePage = "/res/AdminHome.fxml";
}
