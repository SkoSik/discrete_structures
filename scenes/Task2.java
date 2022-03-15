package discrete_structures.scenes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import discrete_structures.BinNumber;
import javafx.scene.control.TextField;

public class Task2 implements Initializable {

    @FXML
    private Label label4, error;
    @FXML
    private TextField textfield1;
    @FXML
    private ComboBox combobox1, combobox2;

    @FXML
    public void buttonClicked() {
        BinNumber bn = new BinNumber(textfield1.getText());
        int ost, var;
        ost = Integer.parseInt((String) combobox1.getValue());
        var = Integer.parseInt((String) combobox2.getValue());
        try {
            String s = bn.getResidual(ost, var).toString();
            label4.setText(s);
        } catch (Exception e) {
            error.setText(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
