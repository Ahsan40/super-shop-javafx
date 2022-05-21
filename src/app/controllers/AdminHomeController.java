package app.controllers;

import app.main.Main;
import app.main.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Comparator;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {

    @FXML
    private TableColumn<User, String> colEmail;

    @FXML
    private TableColumn<User, String> colMobile;

    @FXML
    private TableColumn<User, String> colName;

    @FXML
    private TableColumn<User, String> colType;

    @FXML
    private TableView<User> table;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // table
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        new Thread(() -> {
            table.setItems(getUsers());
        }).start();
    }

    public ObservableList<User> getUsers() {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        for (Map.Entry<String, User> u : Main.users.entrySet()) {
            allUsers.addAll(u.getValue());
        }

        // sort by Line No.
        allUsers.sort(Comparator.comparing(User::getName));
        return allUsers;
    }
}
