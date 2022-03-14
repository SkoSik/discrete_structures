package discrete_structures.task5;

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

public class SceneMain implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private Label label2, label3, label4, label5, error;
    @FXML
    private TextField textfield1, textfield2;

    @FXML
    public void buttonClicked()
    {
        String suc = "", fic = "", true_suc = "", true_fic = "", s1, s2;
        s1 = textfield1.getText();
        s2 = textfield2.getText();
        for (int i = 0; i < s1.length(); i++)
        {
            if ((s1.charAt(i) - 48 > 0) & (s1.charAt(i) - 48 <= 9)) suc += s1.charAt(i);
        }
        for (int i = 0; i < s2.length(); i++)
        {
            if ((s2.charAt(i) - 48 > 0) & (s2.charAt(i) - 48 <= 9)) fic += s2.charAt(i);
        }
        for (int i = 1; i <= bn.vars; i++)
        {
            if (bn.checkFictitiousness(i)) true_fic += i;
            else true_suc += i;
        }
        if (suc.equals(true_suc) & fic.equals(true_fic))
        {
            label5.setText("Ответ правильный\nСущественные переменные: " + true_suc + "\nФиктивные переменные: " + true_fic);
        }
        else
        {
            label5.setText("Ответ неправильный\nСущественные переменные: " + true_suc + "\nФиктивные переменные: " + true_fic);
        }
        button1.setDisable(true);
    }

    @FXML
    public void buttonClicked1()
    {
        new_vector();
        button1.setDisable(false);
    }

    BinNumber bn;

    public void new_vector()
    {
        bn = BinNumber.randBinNumberByVar(BinNumber.randInt(4) + 1);
        label2.setText(bn.toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        new_vector();
    }
}
