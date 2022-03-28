package discrete_structures.scenes;

import discrete_structures.BoolFunction;
import discrete_structures.GameLogic;
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

public class Task5 implements Initializable {
    GameLogic gameLogic;
    BoolFunction bn;

    @FXML
    private Button button1;
    @FXML
    private Label label2, label3, label4;
    @FXML
    private TextField textfield1, textfield2;

    @FXML
    public void buttonClicked() {
        String suc = "", fic = "", true_suc = "", true_fic = "", s1, s2;
        s1 = textfield1.getText();
        s2 = textfield2.getText();
        for (int i = 0; i < s1.length(); i++) {
            if ((s1.charAt(i) - 48 > 0) & (s1.charAt(i) - 48 <= 9)) suc += s1.charAt(i);
        }
        for (int i = 0; i < s2.length(); i++) {
            if ((s2.charAt(i) - 48 > 0) & (s2.charAt(i) - 48 <= 9)) fic += s2.charAt(i);
        }
        for (int i = 1; i <= bn.vars; i++) {
            if (bn.checkFictitiousness(i)) true_fic += i;
            else true_suc += i;
        }
        gameLogic.check(suc.equals(true_suc) & fic.equals(true_fic));
    }

    @FXML
    public void buttonReload() {
        bn = BoolFunction.randBoolFunction(GameLogic.getVars());
        label2.setText(bn.toOutput());

        gameLogic.reload();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameLogic = new GameLogic(button1, label3, label4);

        buttonReload();
    }
}
