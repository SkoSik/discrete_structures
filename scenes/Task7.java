package discrete_structures.scenes;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import discrete_structures.*;
import discrete_structures.SKNF;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.*;

import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

public class Task7 implements Initializable {

    BoolFunction bin;
    Mask mask;
    boolean brackets;

    @FXML
    Button btn1, btn2, btn3, btn4, btn5, btnNot, btnOr, megabtn, btnDelete;

    @FXML
    TextArea textarea;

    @FXML
    Label label, label2;
    String KNF = "";

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
        if (KNF.length() != 0 && KNF.charAt(KNF.length() - 1) == '¬')
            KNF = KNF.substring(0, KNF.length() - 1);
        else if (KNF.charAt(KNF.length() - 1) == '(' || KNF.charAt(KNF.length() - 1) == 'V')
            KNF += "¬";
        textarea.setText(KNF);
    }

    @FXML
    public void btnOrClicked() {
        label.setText("");
        try {
            if (!oneToFive())
                throw new Exception("ставьте V после переменных");

            KNF += "V";
            textarea.setText(KNF);
        } catch (Exception e) {
            message(false, e.getMessage());
        }
    }

    @FXML
    public void megabtnClicked() {
        label.setText("");
        try {
            if (isEdVec()) {
                message(true, "правильно");
                return;
            }
            if (KNF.length() != 0 && KNF.charAt(KNF.length() - 1) != ')')
                throw new Exception("закончите выражение");
            SKNF solution = new SKNF(mask.getVarState(), bin.vars);
            BoolFunction b = new BoolFunction(solution, bin.vars);
            if (b.equals(bin)) {
                message(true, "правильно");
            } else {
                message(false, "неправильно");
            }
        } catch (Exception e) {
            message(false, e.getMessage());
        }
    }

    @FXML
    public void btnNextClicked() {
        label.setText("");
        KNF = "";
        textarea.setText("");
        initNewTask();
        mask = null;
    }

    @FXML
    public void btnDeleteClicked() {
        label.setText("");
        int KNFsz = KNF.length();
        if (KNFsz == 0)
            return;

        char Symb = KNF.charAt(KNFsz - 1);
        int ind = Symb - '0' - 1;

        if (oneToFive()) {
            mask.decreaseEx(ind);
            if (mask.checkEx(ind) == 0)
                mask.changeState(ind, 'x');
            eraseSymb();
            eraseSymb();
        } else if (Symb == ')') {
            brackets = true;
            eraseSymb();
        } else if (Symb == '(') {
            mask.deleteOneMask();
            brackets = false;
            eraseSymb();
        } else if (Symb == '∧') {
            eraseSymb();
        } else
            eraseSymb();

        textarea.setText(KNF);
    }

    @FXML
    public void btnAndClicked() {
        label.setText("");
        try {
            if (KNF.length() == 0 || KNF.charAt(KNF.length() - 1) != ')')
                throw new Exception("Ставьте ∧ после закрытия скобок");

            KNF += '∧';
            textarea.setText(KNF);
        } catch (Exception e) {
            message(false, e.getMessage());
        }
    }

    @FXML
    public void openBracketClicked() {
        label.setText("");
        try {
            if (brackets)
                throw new Exception("Закончите скобочную последовательность");
            else if (KNF.length() != 0 && KNF.charAt(KNF.length() - 1) == ')')
                throw new Exception("После скобок ставьте ∧");

            if (mask == null)
                mask = new Mask(bin.vars);
            else
                mask.newMask();
            brackets = true;
            KNF += '(';
            textarea.setText(KNF);
        } catch (Exception e) {
            message(false, e.getMessage());
        }
    }

    @FXML
    public void closeBracketClicked() {
        label.setText("");
        try {
            if (!oneToFive())
                throw new Exception("закрывайте скобку после переменной");
            brackets = false;
            KNF += ')';
            textarea.setText(KNF);
        } catch (Exception e) {
            message(false, e.getMessage());
        }
    }

    public void btnClick(int i) {
        label.setText("");
        try {
            if (!brackets)
                throw new Exception("Откройте скобку");

            char Symb = KNF.charAt(KNF.length() - 1);
            if (Symb != '(' && Symb != 'V' && Symb != '¬')
                throw new Exception("Закройте скобку или добавьте V");

            if (checkNot() && mask.checkState(i) != '0')
                mask.changeState(i, '1');
            else
                mask.changeState(i, '0');

            mask.increaseEx(i);
            String str = "x" + String.valueOf(i + 1);
            KNF += str;
            textarea.setText(KNF);
        } catch (Exception e) {
            message(false, e.getMessage());
        }
    }

    public boolean checkNot() {
        if (KNF.length() == 0) return false;
        return KNF.charAt(KNF.length() - 1) == '¬';
    }

    public void eraseSymb() {
        KNF = KNF.substring(0, KNF.length() - 1);
    }

    public boolean oneToFive() {
        if (KNF.length() == 0) return false;
        char symb = KNF.charAt(KNF.length() - 1);
        return symb >= '1' && symb <= '5';
    }

    public boolean isEdVec() {
        for (int i = 0; i < KNF.length(); i++)
            if (KNF.charAt(i) != '1')
                return false;
        return true;
    }

    public void message(boolean bool, String s) {
        if (bool)
            label.setTextFill(Color.color(0, 0.7, 0));
        else
            label.setTextFill(Color.color(0.7, 0, 0));
        label.setText(s);
    }

    public void initNewTask() {
        brackets = false;
        label.setTextFill(Color.color(0.7, 0, 0));
        label.setText("");
        textarea.setText("");
        bin = BoolFunction.randBoolFunction(BinNumber.randInt(4) + 1);
        label2.setText(bin.toString());

        Button[] b = {btn1, btn2, btn3, btn4, btn5};
        for (int i = 0; i < 5; i++)
            b[i].setVisible(i < bin.vars);
    }

    public void initialize(URL location, ResourceBundle resources) {
        initNewTask();
    }
}
