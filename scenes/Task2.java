package discrete_structures.scenes;

import discrete_structures.BoolFunction;
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
        error.setText("");
        label4.setText("");
        try {
            BoolFunction bn = new BoolFunction(textfield1.getText());

            int ost, var;
            ost = Integer.parseInt(combobox1.getValue().toString());
            var = Integer.parseInt(combobox2.getValue().toString());

            label4.setText(bn.getResidual(ost, var).toString());
        } catch (Exception e) {
            error.setText(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combobox1.setValue(0);
        combobox2.setValue(1);
    }
}
