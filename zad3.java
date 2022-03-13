import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class zad1 implements Initializable {

    @FXML
    private Button button1;

    @FXML
    private TextField textfield1, textfield2, textfield3;

    @FXML
    private Label anslabel, exception;
    @FXML
    public void button1Clicked() {
        try{
            if(textfield1.getText().isEmpty() || textfield2.getText().isEmpty() || textfield3.getText().isEmpty())
                throw new IOException("Не все поля заполнены");

            String nullost = textfield1.getText(), edost = textfield2.getText();
            int arg = Integer.parseInt(textfield3.getText());
            int size = edost.length()*2;
            int period = 1 << arg;

            //степень ли двойки размер вектора
            if((size & (size-1)) != 0)
                throw new Exception();

            for(int i = 0; i < nullost.length(); i++)
                if((nullost.charAt(i) != '0' && nullost.charAt(i) != '1') || (edost.charAt(i) != '0' && edost.charAt(i) != '1'))
                    throw new Exception();


            char[] vecfunc = new char[size];

            for(int i = 0, x = 0, y = 0; i < size; i++){
                int ost = i%period / (period/2);
                if(ost == 0)
                    vecfunc[i] = nullost.charAt(x++);
                else
                    vecfunc[i] = edost.charAt(y++);
            }
            anslabel.setText(new String(vecfunc));
            exception.setText("");
        }
        catch (IOException e){
            exception.setText(e.getMessage());
        }
        catch (Exception e){
            exception.setText("Не правильно введены данные");
        }
//        String nullost = textfield1.getText(), edost = textfield2.getText();
//        int size = edost.length()*2, arg = Integer.parseInt(textfield3.getText());
//        int period = 1 << arg;
//        char[] vecfunc = new char[size];
//
//        for(int i = 0, x = 0, y = 0; i < size; i++){
//            int ost = i%period / (period/2);
//            if(ost == 0)
//                vecfunc[i] = nullost.charAt(x++);
//            else
//                vecfunc[i] = edost.charAt(y++);
//        }
//        anslabel.setText(new String(vecfunc));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        String nullost = textfield1.getText(), edost = textfield2.getText();
//        int n = edost.length(), k = Integer.parseInt(textField3.getText());
//        System.out.println(n);
//        System.out.println(k);
    }
}