package discrete_structures;

import java.util.ArrayList;

public class SDNF extends NF {

    public SDNF(String[] masks, int _vars) {
        super(masks, _vars);
    }

    public SDNF(ArrayList<char[]> masks, int _vars) {
        super(masks, _vars);
    }

    public static SDNF build(String sdnfString, int vars) {
        if (sdnfString.length() == 0) {
            return new SDNF(new BoolFunction(String.format("%" + BinNumber.pow(2, vars) + "s").replace(' ', '0')));
        }
        String[] sdnf = sdnfString.split("V");
        ArrayList<char[]> mask = new ArrayList<>();

        for (int i = 0; i < sdnf.length; i++) {
            char[] maskElem = new char[vars];
            for (int j = 0; j < vars; j++) maskElem[j] = 'x';
            boolean neg = false;
            for (int j = 0; j < sdnf[i].length(); j++) {
                char a = sdnf[i].charAt(j);
                if (a == '¬') {
                    neg = (neg == true) ? false : true;
                } else {
                    maskElem[a - 49] = neg ? '0' : '1';
                }
            }
            mask.add(maskElem);
        }

        return new SDNF(mask, vars);
    }

    public SDNF(BoolFunction boolFunction) {
        super(boolFunction.vars);
        int i = 0;
        for (int a : boolFunction.array) {
            if (a == 1) set.add(new BinNumber(i, boolFunction.vars));
            i++;
        }
        setDelimiter();
    }

    public void setDelimiter() {
        delimiter = " V ";
    }
}
