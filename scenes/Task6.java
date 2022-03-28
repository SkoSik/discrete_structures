package discrete_structures.scenes;

import discrete_structures.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.*;

import javafx.scene.control.TextArea;

public class Task6 implements Initializable {

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
    private void buttonClick(ActionEvent e) {
        String id = ((Button) e.getSource()).getText();
        mask += (id.length() > 1) ? id.charAt(1) : id;
        textarea.setText(textarea.getText() + id);
    }

    @FXML
    private void buttonCheck() {
        try {
            SDNF sdnf = SDNF.build(mask, bin.vars);
            BoolFunction test = new BoolFunction(sdnf, bin.vars);
            gameLogic.check(test.equals(bin));
        } catch (IllegalArgumentException e) {
            label1.setText(e.getMessage());
        }
    }

    @FXML
    private void buttonDelete() {
        String knf = textarea.getText();
        if (!(knf.length() == 0)) {
            int last = mask.charAt(mask.length() - 1);
            last = (last > 48 && last < 54) ? 2 : 1;
            textarea.setText(knf.substring(0, knf.length() - last));
            mask = mask.substring(0, mask.length() - 1);
        }
    }

    @FXML
    private void buttonReload() {
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
