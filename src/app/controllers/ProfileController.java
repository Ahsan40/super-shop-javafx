package app.controllers;

import app.main.Config;
import app.main.Main;
import app.main.Operations;
import app.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private Text txtCartItemsCount;

    @FXML
    private Text txtBalance;

    @FXML
    private Text txtEmail;

    @FXML
    private Text txtMobile;

    @FXML
    private Text txtName;

    @FXML
    private Text txtUserName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Init
        txtName.setText(Main.user.getName());
        txtUserName.setText(Main.user.getName());
        txtMobile.setText(Main.user.getMobile());
        txtEmail.setText(Main.user.getEmail());
        txtCartItemsCount.setText(Main.cart.getCartSize() + "");
        txtBalance.setText(Main.user.getBalance() + "");
    }

    @FXML
    void btnAddBalance(ActionEvent event) {
        double b = Utils.inputAlert();
        if (b < 0)
            Utils.alert("Error!", "Invalid amount!", "error");
        else {
            Operations.addBalance(b);
            txtBalance.setText(Main.user.getBalance() + "");
        }
    }





    /// Common Functions

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
        Operations.logout();
    }

    @FXML
    void btnProfileAction(MouseEvent event) {
//        Main.sceneMan.open("profile", Config.profilePage);
    }

    @FXML
    void btnShopAction(MouseEvent event) {
        Main.sceneMan.open("shop", Config.shopPage);
    }
}