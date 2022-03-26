package discrete_structures.scenes;

import discrete_structures.*;
import discrete_structures.SKNF;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

public class Task7 implements Initializable {

    GameLogic gameLogic;

    BoolFunction bin;
    String mask;

    @FXML
    Button b1, b2, b3, b4, b5, bCheck;

    @FXML
    TextArea textarea;

    @FXML
    Label label1, label2, label3;

    @FXML
    public void buttonX(ActionEvent e) {
        String id = ((Button) e.getSource()).getText();
        mask += id.charAt(1);
        textarea.setText(textarea.getText() + id);
    }

    @FXML
    public void buttonOperation(ActionEvent e) {
        String id = ((Button) e.getSource()).getText();
        mask += id;
        textarea.setText(textarea.getText() + id);
    }

    @FXML
    public void buttonCheck() {
        try {
            SKNF sknf = SKNF.build(mask, bin.vars);
            BoolFunction test = new BoolFunction(sknf, bin.vars);
            gameLogic.check(test.equals(bin));
        } catch (IllegalArgumentException e) {
            label1.setText(e.getMessage());
        }
    }

    @FXML
    public void buttonDelete() {
        String knf = textarea.getText();
        if (!(knf.length() == 0)) {
            int last = mask.charAt(mask.length() - 1);
            last = (last > 48 && last < 54) ? 2 : 1;
            textarea.setText(knf.substring(0, knf.length() - last));
            mask = mask.substring(0, mask.length() - 1);
        }
    }

    @FXML
    public void buttonReload() {
        textarea.setText("");
        mask = "";

        bin = BoolFunction.randBoolFunction(GameLogic.getVars());
        label2.setText(bin.toOutput());

        Button[] b = {b1, b2, b3, b4, b5};
        for (int i = 0; i < 5; i++)
            b[i].setVisible(i < bin.vars);

        gameLogic.reload();
    }

    public void initialize(URL location, ResourceBundle resources) {
        gameLogic = new GameLogic(bCheck, label1, label3);
        buttonReload();
    }
}
