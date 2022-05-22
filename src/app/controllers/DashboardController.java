package app.controllers;

import app.classes.Product;
import app.main.Config;
import app.main.Main;
import app.main.Operations;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Text txtCartItemsCount;

    @FXML
    private Text txtBalance;

    @FXML
    private Text txtUserName;

    @FXML
    private Text txtTotalItemCount;

    @FXML
    private Text txtTotalSpend;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Init
        txtUserName.setText(Main.user.getName());
        txtCartItemsCount.setText(Main.cart.getCartSize() + "");
        txtBalance.setText(Main.user.getBalance() + "");
        txtTotalSpend.setText(getAllPrice() + "");
        txtTotalItemCount.setText(Main.history.size() + "");
    }

    private double getAllPrice() {
        double sum = 0;
        for (Product p: Main.history)
            sum += p.getPrice();
        return sum;
    }

    @FXML
    void btnHomeAction(MouseEvent event) {
//        Main.sceneMan.open("home", Config.dashboardPage);
    }
    @FXML
    void btnAboutAction(MouseEvent event) {
        Main.sceneMan.open("about", Config.aboutPage);
    }

    @FXML
    void btnCartAction(MouseEvent event) {
        Main.sceneMan.open("cart", Config.cartPage);
    }

    @FXML
    void btnHistoryAction(MouseEvent event) {
        Main.sceneMan.open("history", Config.historyPage);
    }

    @FXML
    void btnLogoutAction(MouseEvent event) {
        Operations.logout();
    }

    @FXML
    void btnProfileAction(MouseEvent event) {
        Main.sceneMan.open("profile", Config.profilePage);
    }

    @FXML
    void btnShopAction(MouseEvent event) {
        Main.sceneMan.open("shop", Config.shopPage);
    }
}
