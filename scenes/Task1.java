package discrete_structures.scenes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import discrete_structures.BinNumber;

public class Task1 implements Initializable {

    @FXML
    private Label label1;
    @FXML
    private ComboBox comboBox1;

    @FXML
    public void buttonClicked() {
        int number = Integer.parseInt((String) comboBox1.getValue());
        label1.setText(BinNumber.randBinNumberByVar(number).toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox1.setValue("1");
    }
}
