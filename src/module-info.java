module AFFCM {
    requires com.google.gson;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.desktop;
    requires llama;
    requires org.slf4j;
    requires com.github.oshi;
    requires com.sun.jna;

    exports com.affcm;
    exports com.affcm.controller;
    exports com.affcm.service;

    opens com.affcm.service to com.google.gson, javafx.fxml;
    opens com.affcm to javafx.fxml;
    opens com.affcm.controller to javafx.fxml;
}
