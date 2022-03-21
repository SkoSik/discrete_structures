package discrete_structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SKNF extends NF {

    public SKNF(String[] masks, int _vars) {
        super(masks, _vars);
    }

    public SKNF(ArrayList<char[]> masks, int _vars) {
        super(masks, _vars);
    }

    public SKNF(BinNumber binNumber) {
        super(binNumber.vars);
        Arrays.stream(binNumber.array).forEach(a -> {
            if (a == 0) set.add(new BinNumber(a));
        });
    }

    public String toString() {
        return "(" + String.join(") & (", set.stream()
                .map(BinNumber::toNFVars)
                .collect(Collectors.toList())
        ) + ")";
    }
}