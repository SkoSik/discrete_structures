package discrete_structures.task1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import discrete_structures.BinNumber;
import javafx.scene.control.TextField;

public class SceneMain implements Initializable {

    @FXML
    private Label label1,label2;
    @FXML
    private TextField textField1;

    @FXML
    public void buttonClicked() {
        try {
            int number = Integer.parseInt(textField1.getText());
            String vector = BinNumber.randBinNumberByVar(number).toString();
            label1.setText(vector);
            label2.setText("");
        } catch (IllegalArgumentException e) {
            if(e instanceof NumberFormatException) label2.setText("Некорректное значение переменной");
            else label2.setText(e.getMessage());
            label1.setText("");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
