package discrete_structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class NF {
    int vars;
    Set<BinNumber> set;

    public NF(String[] masks, int _vars) {
        set = new HashSet<>();
        vars = _vars;
        for (String a : masks) {
            buildNFbyMask(a.toCharArray(), 0);
        }
    }

    public NF(ArrayList<char[]> masks, int _vars) {
        set = new HashSet<>();
        vars = _vars;
        for (char[] a : masks) {
            buildNFbyMask(a, 0);
        }
    }

    private void buildNFbyMask(char[] s, int i) {
        if (i == s.length) {
            set.add(new BinNumber(vars, String.valueOf(s)));
            return;
        }
        if (s[i] == 'x') {
            for (char j = '0'; j < '2'; j++) {
                char[] s1 = Arrays.copyOf(s, s.length);
                s1[i] = j;
                buildNFbyMask(s1, i + 1);
            }
        } else buildNFbyMask(s, ++i);
    }

    public String toString() {
        return String.join(" ", set.stream()
                .map(BinNumber::toNFVars)
                .collect(Collectors.toList())
        );
    }
}