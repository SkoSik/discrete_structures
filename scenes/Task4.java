package discrete_structures.scenes;

import discrete_structures.BinNumber;
import discrete_structures.BoolFunction;
import discrete_structures.GameLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.*;

public class Task4 implements Initializable {

    private GameLogic gameLogic;
    Map<String, BinNumber> vectors = new HashMap<>();
    BinNumber binNumber;

    @FXML
    private Button button1;
    @FXML
    private Label label2, label3, label4;
    @FXML
    private ComboBox<String> comboBox1;

    @FXML
    public void buttonReload() {
        List<String> list = new ArrayList<>(vectors.keySet());
        Collections.shuffle(list);
        comboBox1.getItems().setAll(list);
        comboBox1.setValue(list.get(0));

        binNumber = BoolFunction.randBoolFunction(2);
        label2.setText(binNumber.toOutput());

        gameLogic.reload();
    }

    @FXML
    public void buttonCheck() {
        gameLogic.check(binNumber.equals(vectors.get(comboBox1.getValue())));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameLogic = new GameLogic(button1, label3, label4);
        String[] temp = new String[]{"Ноль", "Конъюкция", "Отрицание импликации", "X", "Отрицание обратной импликации", "Y", "Сложение по модулю 2", "Диъюнкция",
                "Стрелка Пирса", "Эквивалентность", "Отрицание Y", "Обратная импликация", "Отрицание X", "Импликация", "Штрих Шеффера", "Единица"};
        for (int i = 0; i < 16; i++) {
            vectors.put(temp[i], new BoolFunction(i, 2));
        }
        buttonReload();
    }
}
