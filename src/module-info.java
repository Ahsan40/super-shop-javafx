module superShop.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    exports app.controllers;
    opens app.controllers to javafx.fxml;
    exports app.main;
    opens app.main to javafx.fxml;
    exports app.utils;
    opens app.utils to javafx.fxml;
    exports custom.controls;
    opens custom.controls to javafx.fxml;
    exports app.classes;
    opens app.classes to javafx.fxml;
}