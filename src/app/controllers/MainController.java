package app.controllers;

import app.main.Config;
import app.main.Main;
import app.utils.Size;
import app.utils.Utils;
import javafx.event.ActionEvent;

public class MainController {
    public void switchToSignIn(ActionEvent event) {
        Main.sceneMan.open("login", Config.signInPage, 400, 650);
    }

    public void switchToSignUp(ActionEvent e) {
        Main.sceneMan.open("registration", Config.registrationPage, new Size(460, 630));
    }
}