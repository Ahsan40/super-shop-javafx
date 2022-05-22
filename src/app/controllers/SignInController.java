package app.controllers;

import app.main.Config;
import app.main.Main;
import app.classes.User;
import app.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SignInController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSignIn;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfPass;

    @FXML
    private Text txtError;

    @FXML
    void btnCancelAction(ActionEvent event) {
        Main.sceneMan.activate("home");
    }

    @FXML
    void btnSignInAction(ActionEvent event) {
        boolean result = Utils.checkLogin(new User(tfEmail.getText(), Utils.sha256(tfPass.getText())), Main.receiveObj, Main.sendObj);
        if (result) {
                System.out.println(" - Logging in to User Control Panel.");
                if (Main.user.getType().equalsIgnoreCase("admin"))
                    Main.sceneMan.open("admin", Config.adminHomePage);
                else
                    Main.sceneMan.open("home", Config.dashboardPage);
        } else {
            txtError.setText("Login Failed!");
        }
    }
}