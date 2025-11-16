module AFFCM {
    requires com.google.gson; // imports com.google.gson to project
    requires javafx.base; // imports javafx.base to project
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.desktop;
    requires llama;
    requires org.slf4j;
    requires ai.djl.api;

//   opens com.example to javafx.fxml; // Let java.fxml use reflection (see private fields, call methods, needed for FXML)
    exports main.java.com.affcm; // let others import com.example.Main
    exports main.resources.fxml;

    opens main.resources.fxml to javafx.fxml;
}