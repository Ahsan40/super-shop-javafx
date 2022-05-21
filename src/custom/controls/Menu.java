package custom.controls;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class Menu extends AnchorPane {
    public Menu() {
        super();
        try {
            Parent n = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
            this.getChildren().add(n);
        } catch (IOException ignored) {}
    }
}
