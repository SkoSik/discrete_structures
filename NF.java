package discrete_structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class NF {
    Set<BinNumber> set;

    public NF(String[] masks) {
        set = new HashSet<>();
        for (String a : masks) {
            buildSDNFbyMask(a.toCharArray(), 0);
        }
    }

    public NF(ArrayList<char[]> masks) {
        set = new HashSet<>();
        for (char[] a : masks) {
            buildSDNFbyMask(a, 0);
        }
    }

    private void buildSDNFbyMask(char[] s, int i) {
        if (i == s.length) {
            set.add(new BinNumber(String.valueOf(s)));
            return;
        }
        if (s[i] == 'x') {
            for (char j = '0'; j < '2'; j++) {
                char[] s1 = Arrays.copyOf(s, s.length);
                s1[i] = j;
                buildSDNFbyMask(s1, i + 1);
            }
        } else buildSDNFbyMask(s, ++i);
    }

    public String toString() {
        return String.join(" ", set.stream().map(e -> e.toString()).collect(Collectors.toList()));
    }
}
