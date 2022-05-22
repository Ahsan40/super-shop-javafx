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

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class CartController implements Initializable {
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
    private TableView<Product> table;

    @FXML
    private Text txtTotalAmount;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Init
        txtUserName.setText(Main.user.getName());
        txtCartItemsCount.setText(Main.cart.getCartSize() + "");
        txtBalance.setText(Main.user.getBalance() + "");
        txtTotalAmount.setText(Main.cart.getTotal() + "");

        // Table
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        table.setItems(getProducts());
    }
    private ObservableList<Product> getProducts () {
        ObservableList<Product> list = FXCollections.observableArrayList();
        list.addAll(Main.cart.getCartList());
        list.sort(Comparator.comparing(Product::getName));
        return list;
    }


    @FXML
    void btnBuyAction(ActionEvent event) {
        if (Main.cart.getCartSize() == 0) {
            Utils.alert("Warning!", "Please add products first to buy!", "warning");
            return;
        }

        if (Main.user.getBalance() < Main.cart.getTotal())
            Utils.alert("Warning!", "Insufficient Balance!", "warning");
        else {
            Operations.buy(Main.cart);
            table.getItems().clear();
            txtBalance.setText(Main.user.getBalance() + "");
            txtCartItemsCount.setText(Main.cart.getCartSize() + "");
            txtTotalAmount.setText(Main.cart.getTotal() + "");
        }
    }


    @FXML
    void btnRemoveAction(ActionEvent event) {
        Product p = table.getSelectionModel().getSelectedItem();
        if (p != null) {
            Main.cart.remove(p);
            table.getItems().remove(p);
            txtTotalAmount.setText(Main.cart.getTotal() + "");
        } else
            Utils.alert("Warning!", "Please select and item first!", "warning");
    }






    @FXML
    void btnHomeAction(MouseEvent event) {
        Main.sceneMan.reload("home");
        Main.sceneMan.open("home", Config.dashboardPage);
    }

    @FXML
    void btnAboutAction(MouseEvent event) {
        Main.sceneMan.open("about", Config.aboutPage);
    }

    @FXML
    void btnCartAction(MouseEvent event) {
//        Main.sceneMan.open("cart", Config.cartPage);
    }

    @FXML
    void btnHistoryAction(MouseEvent event) {
        Main.sceneMan.reload("history");
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
