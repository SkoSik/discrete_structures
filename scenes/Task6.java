package discrete_structures.scenes;

import discrete_structures.Mask;
import discrete_structures.SDNF;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

import discrete_structures.BinNumber;
import javafx.scene.paint.Color;


public class Task6 implements Initializable {

    BinNumber bin;
    Mask mask;

    @FXML
    Button btn1, btn2, btn3, btn4, btn5, btnNot, btnOr, megabtn, btnDelete;

    @FXML
    TextArea textarea;

    @FXML
    Label label, label2;
    String DNF = "";

    @FXML
    public void btn1Clicked() {
        btnClick(0);
    }

    @FXML
    public void btn2Clicked() {
        btnClick(1);
    }

    @FXML
    public void btn3Clicked() {
        btnClick(2);
    }

    @FXML
    public void btn4Clicked() {
        btnClick(3);
    }

    @FXML
    public void btn5Clicked() {
        btnClick(4);
    }

    @FXML
    public void btnNotClicked() {
        label.setText("");
        if(DNF.length() != 0 && DNF.charAt(DNF.length()-1) == '¬')
            DNF = DNF.substring(0,DNF.length()-1);
        else
            DNF += "¬";

        textarea.setText(DNF);
    }

    @FXML
    public void btnOrClicked() {
        label.setText("");
        try{
            if(DNF.length() == 0)
                throw new Exception("нельзя ставить V в самом начале");
            if(DNF.charAt(DNF.length()-1) == '¬')
                throw new Exception("нельзя ставить V после ¬");
            if(DNF.charAt(DNF.length()-1) == 'V')
                return;

            mask.newMask();

            DNF += "V";
            textarea.setText(DNF);
        }
        catch (Exception e){
            message(false, e.getMessage());
        }
    }

    @FXML
    public void megabtnClicked() {
        label.setText("");
        try {
            if (DNF.length() != 0 && (DNF.charAt(DNF.length() - 1) == 'V' || DNF.charAt(DNF.length() - 1) == '¬'))
                throw new Exception("закончите выражение");
            SDNF solution = new SDNF(mask.getVarState(), bin.vars);
            BinNumber b = new BinNumber(solution, bin.vars);
            if((!DNF.isEmpty() && b.equals(bin)) || (DNF.isEmpty() && bin.parseToInt() == 0)) {
                message(true, "Правильно");
            }
            else {
                message(false, "Неправильно");
            }
        }
        catch (Exception e){
            message(false, e.getMessage());
        }
    }
    @FXML
    public void btnNextClicked(){
        label.setText("");
        DNF = "";
        textarea.setText("");
        initNewTask();
    }

    @FXML
    public void btnDeleteClicked(){
        int DNFsz = DNF.length();
        if(DNFsz == 0)
            return;
        char Symb = DNF.charAt(DNFsz - 1);
        int ind = Symb - '0' - 1;
        if(Symb >= '1' && Symb <= '9') {
            mask.decreaseEx(ind);
            if(mask.checkEx(ind) == 0)
                mask.changeState(ind, 'x');
            DNF = DNF.substring(0, DNFsz - 2);
        }
        else if(Symb == 'V') {
            mask.deleteOneMask();
            DNF = DNF.substring(0, DNFsz - 1);
        }
        else
            DNF = DNF.substring(0, DNFsz - 1);

        textarea.setText(DNF);
    }

    public boolean checkNot(){
        if(DNF.length() == 0) return false;
        return DNF.charAt(DNF.length() - 1) == '¬';
    }

    public void btnClick(int i){
        label.setText("");
        if(checkNot())
            mask.changeState(i, '0');
        else if(mask.checkState(i) != '0')
            mask.changeState(i, '1');

        mask.increaseEx(i);
        String str = "x" + String.valueOf(i+1);
        DNF += str;
        textarea.setText(DNF);
    }
    public void initNewTask(){
        label.setTextFill(Color.color(0.7, 0, 0));
        label.setText("");
        textarea.setText("");
        bin = BinNumber.randBinNumberByVar(BinNumber.randInt(5)+1);
//        bin = new BinNumber("00");
        mask = new Mask(bin.vars);
        label2.setText(bin.toString());
        Button[] b = {btn1, btn2, btn3, btn4, btn5};
        for(int i = 0; i < 5; i++)
            b[i].setVisible(i < bin.vars);

        mask = new Mask(bin.vars);
        for(char[] i : mask.getVarState())
            System.out.println(i);
    }
    public void message(boolean bool, String s){
        if(bool)
            label.setTextFill(Color.color(0, 0.7, 0));
        else
            label.setTextFill(Color.color(0.7, 0, 0));
        label.setText(s);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initNewTask();
    }
}