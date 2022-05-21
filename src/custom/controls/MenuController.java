package custom.controls;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    void aboutAction(MouseEvent event) {
        System.out.println("Clicked About.");
    }

    @FXML
    void homeAction(MouseEvent event) {
        System.out.println("Clicked Home.");
    }

    @FXML
    void settingsAction(MouseEvent event) {
        System.out.println("Clicked Settings.");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
