package app.controllers;

import app.main.Config;
import app.main.Main;
import app.classes.User;
import app.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RegistrationController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPass;

    @FXML
    private TextField tfPass2;

    @FXML
    private TextField tfPhone;

    @FXML
    private RadioButton rdoAdmin;

    @FXML
    private RadioButton rdoUser;

    @FXML
    private Text txtError;

    @FXML
    void rdoToggle(MouseEvent event) {
        if (((RadioButton) event.getSource()).getText().equals("Admin")) {
            rdoAdmin.setSelected(true);
            rdoUser.setSelected(false);
        } else {
            rdoUser.setSelected(true);
            rdoAdmin.setSelected(false);
        }
    }

    @FXML
    private void btnCancelAction(ActionEvent event) {
        Main.sceneMan.activate("home");
    }

    @FXML
    private void btnSubmitAction(ActionEvent event) {
        try {
            ObjectOutputStream sendObj = Main.sendObj;
            ObjectInputStream receiveObj = Main.receiveObj;

            // sending registration data
            System.out.println(" - Sending credentials");
            System.out.println(" - Requesting for registration");
            sendObj.writeObject("registration");
            if (tfPass.getText().equals(tfPass2.getText())) {
                User user = new User(tfName.getText(), tfEmail.getText(), tfPhone.getText(), Utils.sha256(tfPass.getText()), rdoUser.isSelected() ? "User" : "Admin");
                sendObj.writeObject(user);

                // reading response
                String response = (String) receiveObj.readObject();
                System.out.println(" - Received response: " + response);
                if (response.contains("SUCCESS")) {
                    System.out.println(" - Registration Successful!");
                    Main.sceneMan.open("login", Config.signInPage, 400, 650);
                } else
                    txtError.setText("User with same email already exist!");
            } else
                txtError.setText("Confirm Password doesn't match!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
