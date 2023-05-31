module jjdcontracting.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens jjdcontracting.finalproject to javafx.fxml;
    exports jjdcontracting.finalproject;
}
