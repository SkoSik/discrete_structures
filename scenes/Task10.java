package discrete_structures.scenes;

import discrete_structures.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.*;

public class Task10 implements Initializable {

    GameLogic gameLogic;
    BoolFunction binNumber;
    private CheckBox[] checkBoxes;

    @FXML
    private Button button1;
    @FXML
    private Label label2, label3, label4;
    @FXML
    private CheckBox cb1, cb2, cb3, cb4, cb5;

    @FXML
    public void buttonReload() {
        binNumber = BoolFunction.randBoolFunction(GameLogic.getVars());
        label2.setText(binNumber.toOutput());
        for (CheckBox a : checkBoxes) a.setSelected(false);

        gameLogic.reload();
    }

    @FXML
    public void buttonCheck() {
        boolean[] fullness = binNumber.getClasses();

        boolean ans = true;
        for (int i = 0; i < 5; i++) {
            if (!fullness[i] == checkBoxes[i].isSelected()) ans = false;
        }

        gameLogic.check(ans);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameLogic = new GameLogic(button1, label3, label4);
        checkBoxes = new CheckBox[]{cb1, cb2, cb3, cb4, cb5};
        buttonReload();
    }
}
