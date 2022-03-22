package discrete_structures.scenes;

import discrete_structures.BoolFunction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import discrete_structures.BinNumber;
import javafx.scene.control.TextField;

import javax.swing.*;

public class Task3 implements Initializable {

    @FXML
    private Button button1;

    @FXML
    private TextField textfield1, textfield2;

    @FXML
    private Label anslabel, exception;

    @FXML
    private ComboBox comboBox1;

    @FXML
    public void button1Clicked() {
        try {
            BoolFunction nullost = new BoolFunction(textfield1.getText());
            BoolFunction edost = new BoolFunction(textfield2.getText());
            int arg = Integer.parseInt((String) comboBox1.getValue());
            BoolFunction vecFunc = new BoolFunction(nullost, edost, arg);
            anslabel.setText(vecFunc.toString());
            exception.setText("");
        } catch (Exception e) {
            exception.setText(e.getMessage());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}