package discrete_structures.scenes;

import discrete_structures.BoolFunction;
import discrete_structures.SDNF;
import discrete_structures.SKNF;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import discrete_structures.BinNumber;
import javafx.scene.control.TextField;

public class Task9 implements Initializable {
    BoolFunction bin;

    @FXML
    Label label;

    @FXML
    TextField textfield;

    @FXML
    public void btnSKNFClicked(){
        bin = new BoolFunction(textfield.getText());
        System.out.println(bin.vars);
        SDNF sdnf = new SDNF(bin);
//        for (BinNumber i : sdnf.set) {
//            for(int j : i.array)
//                System.out.print(j);
//            System.out.println();
//        }
//        for(BinNumber i : sdnf.set){
//            System.out.println(i.toString());
//        }
//        int[] q = BinNumber.parseIntToBin(7,1);
//        for(int i : q)
//            System.out.print(i);
    }

    public void initNew(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initNew();
    }
}
