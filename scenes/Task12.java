package discrete_structures.scenes;

import com.sun.xml.internal.messaging.saaj.util.TeeInputStream;
import discrete_structures.BoolFunction;
import discrete_structures.SDNF;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import discrete_structures.BinNumber;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Task12 implements Initializable {

    @FXML
    Label label;

    @FXML
    TextField textfield;

    @FXML
    public void btnClicked(){
        try {
            label.setTextFill(Color.color(0, 0, 0));
            String s = textfield.getText();
            if(s.isEmpty())
                return;
            BoolFunction bool = new BoolFunction(s);
            ArrayList<String> nabori = new ArrayList<>(), ans = new ArrayList<>();
            int eds = 0;
//            for(int i = 0; i < s.length(); i++){
//                if(s.charAt(i) == '1') {
//                    eds++;
//                    nabori.add(BoolFunction.parseIntToBinNumOfBoolFuncString(i, s.length()));
//                }
////                else if(s.charAt(i) != '0')
////                    throw new Exception();
//            }
            for(int i = 0; i < bool.array.length; i++){
                if(bool.array[i] == 1){
                    eds++;
                    nabori.add(BinNumber.parseIntToBinString(i, bool.vars));
                }
            }
            if(eds == s.length()) {
                label.setText("¬x1 V x1");
                return;
            }

            func(nabori, ans);
            ArrayList<String> out = new ArrayList<>();

            for(String i : ans){
                String tmp = "";
                for(int j = 0; j < i.length(); j++)
                    if(i.charAt(j) == '1')
                        tmp += "x" + (j + 1);
                    else if(i.charAt(j) == '0')
                        tmp += "¬x" + (j + 1);
                out.add(tmp);
            }
            label.setText(String.join(" V ", out));
        }
        catch (Exception e){
            label.setTextFill(Color.color(0.7, 0, 0));
            label.setText(e.getMessage());
        }
    }

    public static void func(ArrayList<String> inp, ArrayList<String> ans) {
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < inp.size() - 1; i++)
        {
            for (int j = i + 1; j < inp.size(); j++)
            {
                String s1 = inp.get(i), s2 = inp.get(j);
                int ind = -1, same = 0;
                for(int k = 0; k < s1.length(); k++)
                {
                    if(s1.charAt(k) == '0' && s2.charAt(k) == '1' || s1.charAt(k) == '1' && s2.charAt(k) == '0')
                        ind = k;
                    else if(s1.charAt(k) == s2.charAt(k))
                        same++;
                }
                if(same == s1.length()-1) {
                    StringBuilder tmp = new StringBuilder(s1);
                    tmp.setCharAt(ind, 'x');
                    arr.add(tmp.toString());
                }
            }
        }

        arr = (ArrayList<String>) arr.stream().distinct().collect(Collectors.toList());
        if(inp.size() > 0)
            func(arr, ans);

        if(ans.size() == 0)
            ans.addAll(arr);

        for (String higher : inp)
        {
            boolean addElem = true;
            for (String deeper : ans)
            {
                int same = 0;
                for (int k = 0; k < deeper.length(); k++)
                    if (deeper.charAt(k) == 'x' || deeper.charAt(k) == higher.charAt(k))
                        same++;

                if (same == deeper.length()) {
                    addElem = false;
                    break;
                }
            }
            if (addElem)
                ans.add(higher);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
