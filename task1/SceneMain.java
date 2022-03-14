package discrete_structures.task1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import discrete_structures.BinNumber;

public class SceneMain implements Initializable {

    @FXML
    private Label label1;
    @FXML
    private ComboBox comboBox1;

    @FXML
    public void buttonClicked() {
        int number = Integer.parseInt((String) comboBox1.getValue());
        String vector = BinNumber.randBinNumberByVar(number).toString();
        label1.setText(vector);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox1.setValue("1");
    }
}
