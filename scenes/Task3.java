package discrete_structures.scenes;

import discrete_structures.BoolFunction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.TextField;

public class Task3 implements Initializable {

    @FXML
    private TextField textfield1, textfield2;
    @FXML
    private Label anslabel, exception;
    @FXML
    private ComboBox comboBox1;

    @FXML
    public void button1Clicked() {
        anslabel.setText("");
        exception.setText("");
        try {
            BoolFunction nullost = new BoolFunction(textfield1.getText());
            BoolFunction edost = new BoolFunction(textfield2.getText());
            int arg = Integer.parseInt((String) comboBox1.getValue());
            BoolFunction vecFunc = new BoolFunction(nullost, edost, arg);
            anslabel.setText(vecFunc.toString());
        } catch (Exception e) {
            exception.setText(e.getMessage());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}