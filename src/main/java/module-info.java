module com.game.droidbattle {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires static lombok;
    requires javafx.base;

    opens com.game.controller to javafx.fxml;

    opens com.game to javafx.fxml;
    exports com.game;
    exports com.game.controller;
    exports com.game.droids;
}