package discrete_structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SDNF extends NF {

    public SDNF(String[] masks) {
        super(masks);
    }

    public SDNF(ArrayList<char[]> masks) {
        super(masks);
    }

    public String toString() {
        return String.join("V", set.stream().map(e -> e.toString()).collect(Collectors.toList()));
    }
}
