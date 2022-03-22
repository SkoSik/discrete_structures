package discrete_structures;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
            if (a == 0) set.add(new BoolFunction(i, boolFunction.vars));
            i++;
        }
    }

    public String toString() {
        return "(" + String.join(") & (", set.stream()
                .map(BinNumber::toNFVars)
                .collect(Collectors.toList())
        ) + ")";
    }
}