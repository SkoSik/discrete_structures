package discrete_structures.scenes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import discrete_structures.BinNumber;

public class Task6 implements Initializable {

    @FXML
    Button btn1, btn2, btn3, btn4, btn5, btnNot, btnOr, megabtn;

    @FXML
    TextArea textarea;

    @FXML
    Label exception, label2;

    String DNF = "";

    @FXML
    public void btn1Clicked() {
        DNF += "x";
        textarea.setText(DNF);
    }
    @FXML
    public void btn2Clicked() {
        DNF += "y";
        textarea.setText(DNF);
    }
    @FXML
    public void btn3Clicked() {
        DNF += "z";
        textarea.setText(DNF);
    }
    @FXML
    public void btn4Clicked() {
        DNF += "e";
        textarea.setText(DNF);
    }
    @FXML
    public void btn5Clicked() {
        DNF += "l";
        textarea.setText(DNF);
    }
    @FXML
    public void btnNotClicked() {
        if(DNF.length() != 0 && DNF.charAt(DNF.length()-1) == '¬')
            DNF = DNF.substring(0,DNF.length()-1);
        else
            DNF += "¬";
        textarea.setText(DNF);
    }
    @FXML
    public void btnOrClicked() {
        try{
            if(DNF.length() == 0)
                throw new Exception("нельзя ставить V в самом начале");
            if(DNF.charAt(DNF.length()-1) == '¬')
                throw new Exception("нельзя ставить V после ¬");
            DNF += "V";
            textarea.setText(DNF);
        }
        catch (Exception e){
            exception.setText(e.getMessage());
        }
    }
    public boolean isRight(){
        String answer = textarea.getText();
        return false;
    }
    @FXML
    public void megabtnClicked() {
        BinNumber n = new BinNumber("01010101");
        System.out.println(n.getResidual(0,1));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BinNumber bin = BinNumber.randBinNumberByVar(BinNumber.randInt(4)+1);
        label2.setText(bin.toString());
    }
}