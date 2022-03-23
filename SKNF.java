package discrete_structures;

import java.util.ArrayList;

public class SKNF extends NF {

    public SKNF(String[] masks, int _vars) {
        super(masks, _vars);
    }

    public SKNF(ArrayList<char[]> masks, int _vars) {
        super(masks, _vars);
    }

    public SKNF(BoolFunction boolFunction) {
        super(boolFunction.vars);
        int i = 0;
        for (int a : boolFunction.array) {
            if (a == 0) set.add(new BinNumber(i, boolFunction.vars));
            i++;
        }
        setDelimiter();
    }

    public void setDelimiter() {
        delimiter = ") & (";
    }

    public String toString() {
        return "(" + super.toString() + ")";
    }
}