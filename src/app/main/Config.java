package app.main;

public class Config {
    // App Info
    public static String title = "SuperShop";
    public static String version = "v1.0";
    public static String icon = "/res/img/logo.png";
    public static String userTempData = "temp.ser";
    public static double defaultHeight = 460;
    public static double defaultWeight = 750;


    // Server
    public static String server = "localhost";
    public static int port = 6611;
    public static String userDB = "usersDB.ser";

    // Pages
    public static String homePage = "/res/fxml/Home.fxml";
    public static String profilePage = "/res/fxml/Profile.fxml";
    public static String cartPage = "/res/fxml/Cart.fxml";
    public static String shopPage = "/res/fxml/Shop.fxml";
    public static String aboutPage = "/res/fxml/About.fxml";
    public static String historyPage = "/res/fxml/History.fxml";
    public static String errorPage = "/res/fxml/Error.fxml";
    public static String dashboardPage = "/res/fxml/Dashboard.fxml";
    public static String signInPage = "/res/fxml/SignIn.fxml";
    public static String registrationPage = "/res/fxml/Registration.fxml";
    public static String adminHomePage = "/res/fxml/AdminHome.fxml";
}
