package app.controllers;

import app.main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Text txtUserName;

    @FXML
    private Text txtUserType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // settings
        txtUserName.setText(Main.user.getName());
        txtUserType.setText(Main.user.getType());
    }
}