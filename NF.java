package discrete_structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class NF {
    int vars;
    public
    Set<BinNumber> set = new HashSet<>();
    public static String delimiter;

    public NF(int _vars){
        vars = _vars;
        setDelimiter();
    }

    public NF(String[] masks, int _vars) {
        vars = _vars;
        for (String a : masks) {
            buildNFbyMask(a.toCharArray(), 0);
        }
        setDelimiter();
    }

    public NF(ArrayList<char[]> masks, int _vars) {
        vars = _vars;
        for (char[] a : masks) {
            buildNFbyMask(a, 0);
        }
        setDelimiter();
    }

    private void buildNFbyMask(char[] s, int i) {
        if (i == s.length) {
            set.add(new BoolFunction(String.valueOf(s),vars));
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

    public abstract void setDelimiter();

    public String toString() {
        return String.join(delimiter, set.stream()
                .map(BinNumber::toNFVars)
                .collect(Collectors.toList())
        );
    }
}