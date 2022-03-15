package discrete_structures;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SKNF extends NF {

    public SKNF(String[] masks) {
        super(masks);
    }

    public SKNF(ArrayList<char[]> masks) {
        super(masks);
    }

    public String toString() {
        return String.join("&", set.stream().map(e -> e.toString()).collect(Collectors.toList()));
    }
}