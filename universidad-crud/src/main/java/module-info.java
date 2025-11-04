module co.edu.uniquindio.universidad {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens co.edu.uniquindio.universidad to javafx.fxml;
    opens co.edu.uniquindio.universidad.viewcontroller to javafx.fxml;

    exports co.edu.uniquindio.universidad;
    exports co.edu.uniquindio.universidad.model;
    exports co.edu.uniquindio.universidad.factory;
    exports co.edu.uniquindio.universidad.viewcontroller;
    exports co.edu.uniquindio.universidad.controller;
}

