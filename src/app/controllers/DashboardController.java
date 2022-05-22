package app.controllers;

import app.main.Config;
import app.main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Text txtCartItemsCount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void btnHomeAction(MouseEvent event) {
        Main.sceneMan.open("home", Config.dashboardPage);
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
