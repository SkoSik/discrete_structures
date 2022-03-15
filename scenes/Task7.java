package discrete_structures.scenes;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import discrete_structures.SDNF;
import discrete_structures.SKNF;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.*;

import discrete_structures.BinNumber;
import javafx.scene.paint.Color;

public class Task7 implements Initializable {

    Map<String, BinNumber> vectors = new HashMap<>();
    BinNumber binNumber;

    @FXML
    private Button button1;
    @FXML
    private Label label2, label3;
    @FXML
    private ComboBox<String> comboBox1;

    @FXML
    public void buttonCheck() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SKNF sknf = new SKNF(new String[]{"1xx", "000"});
        BinNumber bn = new BinNumber(sknf, 3);
        System.out.println(bn);
    }
}
