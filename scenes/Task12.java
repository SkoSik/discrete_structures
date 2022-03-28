package discrete_structures.scenes;

import discrete_structures.BoolFunction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import discrete_structures.BinNumber;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Task12 implements Initializable {
    static int bestsz = 999;
    static Set<Integer> bestset = new HashSet<>();
    @FXML
    Label label;
    @FXML
    TextArea textarea;

    @FXML
    TextField textfield;

    @FXML
    public void btnClicked() {
        try {
            label.setText("");
            String s = textfield.getText();
            if (s.isEmpty())
                return;

            BoolFunction bool;
            if (s.charAt(0) == '%')
                bool = BoolFunction.randBoolFunction(Integer.parseInt(s.substring(1)));
            else {
                bool = new BoolFunction(s);
            }

            ArrayList<String> nabori = new ArrayList<>(), ans = new ArrayList<>();
            int eds = 0;
            for (int i = 0; i < bool.array.length; i++)
                if (bool.array[i] == 1) {
                    eds++;
                    nabori.add(BinNumber.parseIntToBinString(i, bool.vars));
                }

            if (eds == s.length()) {
                textarea.setText("¬x1 V x1");
                return;
            }
            termReduction(nabori, ans);
            String dnfStr = consume(ans, nabori);
            textarea.setText(dnfStr);
        }
        catch (Exception e) {
            label.setText(e.getMessage());
        }
    }

    public static class matrix {
        public int[][] table;
        public int high, width;
        public int[] highPrecalc;
        ArrayList<String> terms, sdnfs;

        public matrix(int _high, int _width) {
            width = _width;
            high = _high;
            table = new int[high][width];
        }

        public matrix(ArrayList<String> _terms, ArrayList<String> _sdnfs) {
            terms = new ArrayList<>(_terms);
            sdnfs = new ArrayList<>(_sdnfs);
            high = terms.size();
            width = sdnfs.size();
            table = new int[high][width];
            highPrecalc = new int[width];

            for (int i = 0; i < high; i++) {
                Set<String> set = new HashSet<>();
                set.add(_terms.get(i));
                for (int j = 0; j < width; j++) {
                    boolean check = true;
                    for (int k = 0; k < terms.get(0).length(); k++)
                        if (!(terms.get(i).charAt(k) == 'x' || terms.get(i).charAt(k) == sdnfs.get(j).charAt(k))) {
                            check = false;
                            break;
                        }
                    if (check) {
                        table[i][j] = 1;
                        highPrecalc[j]++;
                    }
                }
            }
        }

        public Set<String> takeOutCores(int[] xBlock, int[] yBlock) {
            Set<String> dnf = new HashSet<>();
            for (int i = 0; i < width; i++) {
                if (highPrecalc[i] == 1) {
                    int ind = 0;
                    for (int j = 0; j < high; j++)
                        if (table[j][i] == 1)
                            ind = j;
                    dnf.add(terms.get(ind));
                    xBlock[ind] = 1;
                    for (int j = 0; j < width; j++)
                        if (table[ind][j] == 1)
                            yBlock[j] = 1;
                }
            }
            return dnf;
        }
    }


    public static void termReduction(ArrayList<String> inp, ArrayList<String> ans) {
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < inp.size() - 1; i++) {
            for (int j = i + 1; j < inp.size(); j++) {
                String s1 = inp.get(i), s2 = inp.get(j);
                int ind = -1, same = 0;
                for (int k = 0; k < s1.length(); k++) {
                    if (s1.charAt(k) == '0' && s2.charAt(k) == '1' || s1.charAt(k) == '1' && s2.charAt(k) == '0')
                        ind = k;
                    else if (s1.charAt(k) == s2.charAt(k))
                        same++;
                }
                if (same == s1.length() - 1) {
                    StringBuilder tmp = new StringBuilder(s1);
                    tmp.setCharAt(ind, 'x');
                    arr.add(tmp.toString());
                }
            }
        }

        arr = (ArrayList<String>) arr.stream().distinct().collect(Collectors.toList());

        if (inp.size() > 0)
            termReduction(arr, ans);
        if (ans.size() == 0)
            ans.addAll(arr);

        for (String higher : inp) {
            boolean addElem = true;
            for (String deeper : ans) {
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

    public static String consume(ArrayList<String> terms, ArrayList<String> sdnfs) {

        int rowLen = sdnfs.size(), colLen = terms.size();
        int[] xBlock = new int[colLen], yBlock = new int[rowLen];

        matrix matr = new matrix(terms, sdnfs);
        Set<String> dnf = matr.takeOutCores(xBlock, yBlock);
        ArrayList<String> minusCores = new ArrayList<>(), minusSDNFS = new ArrayList<>();

        for (int i = 0; i < Math.max(xBlock.length, yBlock.length); i++) {
            if (i < xBlock.length && xBlock[i] != 1)
                minusCores.add(matr.terms.get(i));
            if (i < yBlock.length && yBlock[i] != 1)
                minusSDNFS.add(matr.sdnfs.get(i));
        }


        matrix matr2 = new matrix(minusCores, minusSDNFS);

        ArrayList<Set<Integer>> inds = new ArrayList<>();

        for (int i = 0; i < matr2.terms.size(); i++) {
            Set<Integer> s = new HashSet<>();
            s.add(i);
            inds.add(s);
        }
        overlapping(matr2, inds);
        for (int i : bestset)
            dnf.add(matr2.terms.get(i));
        return (String.join(" V ", dnfOutTerms(dnf)));
    }

    public static ArrayList<String> dnfOutTerms(Set<String> dnf) {
        ArrayList<String> dnfout = new ArrayList<>();
        for (String i : dnf) {
            String tmp = "";
            for (int j = 0; j < i.length(); j++)
                if (i.charAt(j) == '1')
                    tmp += "x" + (j + 1);
                else if (i.charAt(j) == '0')
                    tmp += "¬x" + (j + 1);
            dnfout.add(tmp);
        }
        return dnfout;
    }

    public static void overlapping(matrix matr, ArrayList<Set<Integer>> ar) {
        ArrayList<matrix> arr = new ArrayList<>();
        ArrayList<ArrayList<Set<Integer>>> inds = new ArrayList<>();

        for (int i = 0; i < matr.high - 1; i++) {
            int l = 0;
            ArrayList<Set<Integer>> tmp = new ArrayList<>();
            matrix matr2 = new matrix(matr.high - i - 1, matr.width);
            for (int j = i + 1; j < matr.high; j++) {
                Set<Integer> set = new HashSet<>();
                boolean b1 = false, b2 = false;
                for (int k = 0; k < matr.width; k++) {
                    if (matr.table[i][k] >= matr.table[j][k]) {
                        matr2.table[l][k] = matr.table[i][k];
                        b1 = true;
                    } else {
                        matr2.table[l][k] = matr.table[j][k];
                        b2 = true;
                    }
                }
                if (b1)
                    set.addAll(ar.get(i));
                if (b2)
                    set.addAll(ar.get(j));
                tmp.add(set);
                if (isFull(matr2.table[l]) && bestsz > set.size()) {
                    bestset = set;
                    bestsz = set.size();
                }
                l++;
            }
            inds.add(tmp);
            arr.add(matr2);
        }
        for (int i = 0; i < arr.size(); i++)
            overlapping(arr.get(i), inds.get(i));
    }

    public static boolean isFull(int[] a) {
        for (int i : a)
            if (i != 1)
                return false;
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textarea.setEditable(false);
    }
}
