package discrete_structures;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
            if (a == 1) set.add(new BoolFunction(i, boolFunction.vars));
            i++;
        }
    }

    public String toString() {
        return String.join(" V ", set.stream()
                .map(BinNumber::toNFVars)
                .collect(Collectors.toList())
        );
    }
}
