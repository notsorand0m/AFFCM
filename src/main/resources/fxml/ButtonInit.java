package main.resources.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static javafx.application.Application.launch;

public class ButtonInit extends Button{

    public static Button button = new Button();

    public ButtonInit(String text){
        button.setText(getText());
        setText(text);
        setGraphic(button);
    }

    public static void setButton(String text) throws Exception{
        button.setText(text);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
