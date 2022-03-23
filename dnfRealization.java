package discrete_structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class dnfRealization {
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
    public static void main(String[] args) {
//        ArrayList<String> sb = new ArrayList<>();
//        sb.add("001");
//        sb.add("011");
//        sb.add("100");
//        sb.add("111");
//        ArrayList<String> ans = new ArrayList<>();
//        func(sb, ans);
//        for(String i : ans)
//            System.out.println(i);


        System.out.println(BoolFunction.parseIntToBinString(3, 3));
//        String s = "1111";
//        int sz = BinNumber.log2(s.length());
//        ArrayList<String> nabori = new ArrayList<>();
//        for(int i = 0; i < s.length(); i++){
//            if(s.charAt(i) == '1')
//                nabori.add(BinNumber.parseIntToBinString(i, sz));
//        }
//        for (String i : nabori)
//            System.out.println(i);
    }
}
