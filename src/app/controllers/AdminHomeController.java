package app.controllers;

import app.classes.Product;
import app.main.Main;
import app.classes.User;
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

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {


    @FXML
    private TableColumn<Product, String> colName;

    @FXML
    private TableColumn<Product, Double> colPrice;

    @FXML
    private TableColumn<Product, Integer> colQuantity;

    @FXML
    private TableView<Product> table;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPrice;

    @FXML
    private TextField tfQuantity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // table
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        new Thread(() -> {
            table.setItems(getProduct());
        }).start();
    }


    @FXML
    void btnAddAction(ActionEvent event) {
        // Verification
        String name  = tfName.getText();
        double price;
        int quantity;
        if (name.isEmpty()) {
            Utils.alert("Error!", "Name can not be empty!", "warning");
            return;
        }
        try {
            price = Double.parseDouble(tfPrice.getText());
            quantity = Integer.parseInt(tfQuantity.getText());
        } catch (NumberFormatException e) {
            Utils.alert("Error!", "Please input numbers only!", "warning");
            return;
        }

        // Adding
        Product p = new Product(name, price, quantity);
        try {
            Main.sendObj.writeObject("addProduct");
            Main.sendObj.writeObject(p);
            Main.allProducts.add(p);
            table.getItems().add(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnRemoveAction(ActionEvent event) {
        Product p = table.getSelectionModel().getSelectedItem();
        if (p != null) {
            try {
                Main.sendObj.writeObject("removeProduct");
                Main.sendObj.writeObject(p);
                Main.allProducts.remove(p);
                table.getItems().remove(p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else
            Utils.alert("Warning!", "Please add products first to remove!", "warning");
    }

    @FXML
    void btnLogoutAction(MouseEvent event) {
        Operations.logout();
    }
    
    public ObservableList<Product> getProduct() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.addAll(Main.allProducts);

        // sort by Line No.
        products.sort(Comparator.comparing(Product::getName));
        return products;
    }
}
