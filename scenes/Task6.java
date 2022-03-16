package discrete_structures.scenes;

import discrete_structures.SDNF;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

import discrete_structures.BinNumber;

import static discrete_structures.BinNumber.log2;

public class Task6 implements Initializable {
    BinNumber bin;

    ArrayList<char[]> mask = new ArrayList<>();
    ArrayList<int[]> varExistence = new ArrayList<>();
    int curmask = 0;

    @FXML
    Button btn1, btn2, btn3, btn4, btn5, btnNot, btnOr, megabtn, btnDelete;

    @FXML
    TextArea textarea;

    @FXML
    Label exception, label2;

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
            if(DNF.charAt(DNF.length()-1) == 'V')
                return;

            curmask++;
            mask.add(new char[bin.vars]);
            varExistence.add(new int[bin.vars]);

            for(int i = 0; i < bin.vars; i++) {
                mask.get(curmask)[i] = 'x';
                varExistence.get(curmask)[i] = 0;
            }

            DNF += "V";
            textarea.setText(DNF);
        }
        catch (Exception e){
            exception.setText(e.getMessage());
        }
    }

    @FXML
    public void megabtnClicked() {
        try {
            if(DNF.length() == 0) return;
            char symb = DNF.charAt(DNF.length() - 1);
            if (symb == 'V' || symb == '¬') throw new Exception("закончите выражение");
            for (char[] i : mask) {
                for (int j = 0; j < bin.vars; j++)
                    System.out.print(i[j]);
                System.out.print(" ");
            }
            System.out.println();
            SDNF solution = new SDNF(mask);
        }
        catch (Exception e){
            exception.setText(e.getMessage());
        }
    }
    @FXML
    public void btnNextClicked(){
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
            if(--varExistence.get(curmask)[ind] == 0)
                mask.get(curmask)[ind] = 'x';
            DNF = DNF.substring(0, DNFsz - 2);
        }
        else if(Symb == 'V') {
            mask.remove(curmask);
            varExistence.remove(curmask);
            curmask--;
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
        if(checkNot())
            mask.get(curmask)[i] = '0';
        else if(mask.get(curmask)[i] != 0)
            mask.get(curmask)[i] = '1';

        varExistence.get(curmask)[i]++;
        String str = "x" + String.valueOf(i+1);
        DNF += str;
        textarea.setText(DNF);
    }
    public void initNewTask(){
        bin = BinNumber.randBinNumberByVar(BinNumber.randInt(5)+1);
        mask.clear();
        varExistence.clear();
        mask.add(new char[bin.vars]);
        varExistence.add(new int[bin.vars]);
        for(int i = 0; i < bin.vars; i++) {
            varExistence.get(curmask)[i] = 0;
            mask.get(curmask)[i] = 'x';
        }
        Button[] b = {btn1, btn2, btn3, btn4, btn5};
        for(int i = 0; i < 5; i++) {
            b[i].setVisible(i < bin.vars);
        }
        label2.setText(bin.toString());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initNewTask();
    }
}