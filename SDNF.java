package discrete_structures;

import java.util.ArrayList;

public class SDNF extends NF {

    public SDNF(String[] masks, int _vars) {
        super(masks, _vars);
    }

    public SDNF(ArrayList<char[]> masks, int _vars) {
        super(masks, _vars);
    }

    public SDNF(BoolFunction boolFunction) {
        super(boolFunction.vars);
        int i = 0;
        for (int a : boolFunction.array) {
            if (a == 1) set.add(new BinNumber(i));
            i++;
        }
        setDelimiter();
    }

    public void setDelimiter() {
        delimiter = " V ";
    }
}
