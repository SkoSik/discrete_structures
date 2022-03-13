package discrete_structures.task2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import discrete_structures.BinNumber;

public class SceneMain implements Initializable {

    HashMap<String,Integer> vectors = new HashMap<>();
    @FXML
    private Button button1;
    @FXML
    private Label label1, label2;
    @FXML
    private ComboBox<String> comboBox1;

    @FXML
    public void buttonClicked() {
        label2.setText(comboBox1.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox1.getItems().setAll("Дизъюнкция", "Конъюкция", "Эквивалентность");
        comboBox1.setValue("Дизъюнкция");
        label2.setText("Дизъюнкция");
    }
}
