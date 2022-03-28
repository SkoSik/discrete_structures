package discrete_structures.scenes;

import discrete_structures.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

public class Task11 implements Initializable {

    GameLogic gameLogic;
    BoolFunction[] binNumber;
    int number_of_funcs;

    @FXML
    private Button button1;
    @FXML
    private Label label3, label4;
    @FXML
    private CheckBox cb1, cb2, cb3, cb4, cb5;
    private CheckBox[] checkBoxes;
    @FXML
    private ComboBox cb_number_of_funcs;
    @FXML
    private TextArea textarea;

    public void setSetBoolFunc() {
        number_of_funcs = Integer.parseInt((String) cb_number_of_funcs.getValue());
        binNumber = new BoolFunction[number_of_funcs];
        for (int i = 0; i < number_of_funcs; i++) {
            binNumber[i] = BoolFunction.randBoolFunction(GameLogic.getVars());
        }
    }

    @FXML
    public void buttonReload() {
        setSetBoolFunc();
        String s = "{";
        for (int i = 0; i < number_of_funcs; i++) {
            s += binNumber[i].toString();
            if (i != number_of_funcs - 1) {
                s += "; ";
            }
        }
        s += "}";
        textarea.setText(s);
        for (CheckBox a : checkBoxes) a.setSelected(false);

        gameLogic.reload();
    }

    @FXML
    public void buttonCheck() {
        Boolean[] fullness = new Boolean[5];
        Arrays.fill(fullness, true);
        for (int i = 0; i < number_of_funcs; i++) {
            fullness[0] &= binNumber[i].isSaveZero();
            fullness[1] &= binNumber[i].isSaveOne();
            fullness[2] &= binNumber[i].isSelfDuality();
            fullness[3] &= binNumber[i].isMonotony();
            fullness[4] &= binNumber[i].isLinear();
        }

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
        cb_number_of_funcs.setValue("2");
        buttonReload();
    }
}
