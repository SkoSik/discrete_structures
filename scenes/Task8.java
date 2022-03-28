package discrete_structures.scenes;

import discrete_structures.BoolFunction;
import discrete_structures.SDNF;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Task8 implements Initializable {
    BoolFunction bool;

    @FXML
    Label label;
    @FXML
    TextField textfield;

    @FXML
    public void btnSDNFClicked() {
        try {
            label.setTextFill(Color.color(0, 0, 0));
            bool = new BoolFunction(textfield.getText());
            SDNF sdnf = new SDNF(bool);
            label.setText(sdnf.toString());
        } catch (IllegalArgumentException e) {
            label.setTextFill(Color.color(0.7, 0, 0));
            label.setText(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
