package app.controllers;

import app.classes.Product;
import app.main.Config;
import app.main.Main;
import app.main.Operations;
import app.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class HistoryController  implements Initializable {
    @FXML
    private Text txtCartItemsCount;

    @FXML
    private Text txtBalance;

    @FXML
    private Text txtUserName;

    @FXML
    private TableColumn<Product, String> colName;

    @FXML
    private TableColumn<Product, Double> colPrice;

    @FXML
    private TableColumn<Product, Integer> colQuantity;

    @FXML
    private TableColumn<Product, LocalDateTime> colDate;

    @FXML
    private TableView<Product> table;

    @FXML
    private TextField tfSearch;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Init
        txtUserName.setText(Main.user.getName());
        txtCartItemsCount.setText(Main.cart.getCartSize() + "");
        txtBalance.setText(Main.user.getBalance() + "");

        // Table
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        table.setItems(getProducts());
    }

    private ObservableList<Product> getProducts () {
        try {
            Main.sendObj.writeObject("getHistory");
            Main.sendObj.writeObject(Main.user);
            Main.history = (ArrayList<Product>) Main.receiveObj.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObservableList<Product> list = FXCollections.observableArrayList();
        list.addAll(Main.history);
        list.sort(Comparator.comparing(Product::getName));
        return list;
    }


    @FXML
    void btnSearchAction(ActionEvent event) {
        String str = tfSearch.getText();
        if (str.isEmpty())
            table.setItems(getProducts());
        else {
            ObservableList<Product> list = FXCollections.observableArrayList();
            for (Product p: Main.history) {
                if (p.getName().toLowerCase().contains(str.toLowerCase()))
                    list.add(p);
            }
            list.sort(Comparator.comparing(Product::getName));
            table.setItems(list);
        }
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
//        Main.sceneMan.open("history", Config.historyPage);
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
