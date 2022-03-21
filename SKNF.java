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

    public String toString() {
        return "(" + String.join(") & (", set.stream()
                .map(BinNumber::toNFVars)
                .collect(Collectors.toList())
        ) + ")";
    }
}