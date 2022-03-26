package discrete_structures;

import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;
import sun.awt.image.PixelConverter;

import java.awt.event.HierarchyBoundsAdapter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class dnfRealization {
    public static class matrix{
        public int[][] table;
        public int high, width;
        public int[] highPrecalc;
        ArrayList<String> terms, sdnfs;
        ArrayList<Set<String>> pokrterm;
        public matrix(){
            table = null;
            high = width = 0;
            highPrecalc = null;
            terms = sdnfs = null;
        }
        public matrix(int _high, int _width){
            width = _width;
            high = _high;
            table = new int[high][width];
            pokrterm = new ArrayList<>();
        }
        public matrix(ArrayList<String> _terms, ArrayList<String> _sdnfs){
            pokrterm = new ArrayList<>();
            terms = new ArrayList<>(_terms);
            sdnfs = new ArrayList<>(_sdnfs);
            high = terms.size();
            width = sdnfs.size();
            table = new int[high][width];
            highPrecalc = new int[width];

            for(int i = 0; i < high; i++) {
                Set<String> set = new HashSet<>();
                set.add(_terms.get(i));
                pokrterm.add(set);
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
        public void matrixout(){
            for(int[] i : table) {
                for (int j : i)
                    System.out.print(j);
                System.out.println();
            }
        }
        public Set<String> takeOutCores(int[] xBlock, int[] yBlock){
            Set<String> dnf = new HashSet<>();
            System.out.println(high + " " + width);
            for(int i = 0; i < width; i++) {
                if(highPrecalc[i] == 1){
                    int ind = 0;
                    for(int j = 0; j < high; j++)
                        if(table[j][i] == 1)
                            ind = j;
                    dnf.add(terms.get(ind));
                    xBlock[ind] = 1;
                    for(int j = 0; j < width; j++)
                        if(table[ind][j] == 1)
                            yBlock[j] = 1;
                }
            }
            return dnf;
        }
    }
    static int bestind = 999, bestsz = 999;
    static Set<Integer> bestset = new HashSet<>();

    public static void pokr(matrix matr, ArrayList<Set<Integer>> ar, Set<Integer> answer){
        ArrayList<matrix> arr = new ArrayList<>();
        ArrayList<ArrayList<Set<Integer>>> inds = new ArrayList<>();
        ArrayList<Set<Integer>> sds = new ArrayList<>();
        for(int i = 0; i < matr.high-1; i++) {
            int l = 0;
            ArrayList<Set<Integer>> tmp = new ArrayList<>();
            matrix matr2 = new matrix(matr.high-i-1, matr.width);
            for (int j = i + 1; j < matr.high; j++) {
                Set<Integer> set = new HashSet<>();
                set.addAll(ar.get(i));
                set.addAll(ar.get(j));
                tmp.add(set);
                matr2.pokrterm.add(matr.pokrterm.get(j));
                for (int k = 0; k < matr.width; k++) {
                    matr2.table[l][k] = Math.max(matr.table[i][k], matr.table[j][k]);
                }
                if(isFull(matr2.table[l]) && bestsz > set.size()) {
                    bestset = set;
                    bestsz = set.size();
                    System.out.println(set);
                }
                l++;
            }
            inds.add(tmp);
            arr.add(matr2);
        }
        for(int i = 0; i < arr.size(); i++)
            pokr(arr.get(i), inds.get(i), answer);
    }

    public static boolean isFull(int[] a){
        for(int i : a)
            if(i != 1)
                return false;
        return true;
    }
    public static void func(ArrayList<String> inp, ArrayList<String> ans, int cnt) {
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
            func(arr, ans, ++cnt);

        if(ans.size() == 0)
            ans.addAll(arr);

        ArrayList<String> left = new ArrayList<>();
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

    public static void func2(ArrayList<String> ans, ArrayList<String> nabori, Set<Integer> answer){

        int rowLen = nabori.size(), colLen = ans.size();
        int[][] table = new int[colLen][rowLen];
        int[] xBlock = new int[colLen], yBlock = new int[rowLen], colPrecalc = new int[rowLen];
        Set<String> dnf = new HashSet<>();

        matrix matr = new matrix(ans, nabori);
        dnf = matr.takeOutCores(xBlock, yBlock);
        System.out.println(dnf);
        System.out.println(Arrays.toString(xBlock));
        System.out.println(Arrays.toString(yBlock));

        ArrayList<String> minusCores = new ArrayList<>(), minusSDNFS = new ArrayList<>();
        for(int i = 0; i < xBlock.length; i++)
            if(xBlock[i] != 1)
                minusCores.add(matr.terms.get(i));
        for(int i = 0; i < yBlock.length; i++)
            if(yBlock[i] != 1)
                minusSDNFS.add(matr.sdnfs.get(i));

        matrix matr2 = new matrix(minusCores, minusSDNFS);
        for(Set<String> i : matr2.pokrterm)
            System.out.println(i.toString());
        ArrayList<Set<Integer>> inds = new ArrayList<>();
        for(int i = 0; i < matr2.terms.size(); i++) {
            Set<Integer> s = new HashSet<>();
            s.add(i);
            inds.add(s);
        }
        pokr(matr2, inds, answer);
        for(int i : bestset){
            dnf.add(matr2.terms.get(i));
        }
        ArrayList<String> dnfout = new ArrayList<>();
        for(String i : dnf){
            String tmp = "";
            for(int j = 0; j < i.length(); j++)
                if(i.charAt(j) == '1')
                    tmp += "x" + (j + 1);
                else if(i.charAt(j) == '0')
                    tmp += "¬x" + (j + 1);
            dnfout.add(tmp);
        }
        System.out.println(String.join(" V ",dnfout));
    }

    public static void main(String[] args) {
        BoolFunction bool = new BoolFunction("0001110101011100");
//        BoolFunction bool = new BoolFunction("1110001000000011");

        ArrayList<String> nabori = new ArrayList<>(), ans = new ArrayList<>(), nabori2, ans2 = new ArrayList<>();
        int eds = 0;
        for(int i = 0; i < bool.array.length; i++){
            if(bool.array[i] == 1){
                eds++;
                nabori.add(BinNumber.parseIntToBinString(i, bool.vars));
            }
        }
        Set<Integer> answer = new HashSet<>();
        int cnt = 0, cnt2 = 0;
        func(nabori, ans, cnt);

//        for(String i : ans)
//            System.out.println(i);
        func2(ans,nabori, answer);
//        System.out.println(bestset.toString());
//        System.out.println(func2(ans, nabori).toString());
    }
}
