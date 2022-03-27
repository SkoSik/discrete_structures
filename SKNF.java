package discrete_structures;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

public class SKNF extends NF {

    public SKNF(String[] masks, int _vars) {
        super(masks, _vars);
    }

    public SKNF(ArrayList<char[]> masks, int _vars) {
        super(masks, _vars);
    }

    public static SKNF build(String sknfString, int vars) {
        if (sknfString.length() == 0) {
            return new SKNF(new BoolFunction(BinNumber.pow(2, BinNumber.pow(2, vars)) - 1, vars));
        }
        String[] sknf = sknfString.split("∧");
        ArrayList<char[]> mask = new ArrayList<>();

        String bracket = "";

        for (int i = 0; i < sknf.length; i++) {
            if (sknf[i].length() == 0) throw new IllegalArgumentException("Некорректный КНФ");

            char[] maskElem = new char[vars];
            for (int j = 0; j < vars; j++) maskElem[j] = 'x';
            boolean isNeg = false, isVar = false, isBrackets = false;
            int usedVars = 0;

            for (int j = 0; j < sknf[i].length(); j++) {
                char a = sknf[i].charAt(j);
                if (a > 48 && a < 48 + App.MAX_VARS) {
                    maskElem[a - 49] = isNeg ? '0' : '1';
                    isNeg = false;
                    isVar = true;
                    usedVars++;
                } else {
                    if (a == 'V') {
                        if (!isVar) throw new IllegalArgumentException("Некорректный КНФ");
                    } else if (a == '¬') isNeg = (isNeg == true) ? false : true;
                    else {
                        bracket += a;
                        isBrackets = true;
                    }
                    isVar = false;
                }
            }
            if (!isBrackets && ((sknf.length > 1 && usedVars > 1) || usedVars > 1))
                throw new IllegalArgumentException("Некорректный КНФ");

            mask.add(maskElem);
        }

        Stack<Character> brackets = new Stack<>();
        try {
            for (int i = 0; i < bracket.length(); i++) {
                if (')' == bracket.charAt(i) && '(' == brackets.peek()) brackets.pop();
                else brackets.push(bracket.charAt(i));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Некорректный КНФ");
        }

        if (!brackets.isEmpty()) throw new IllegalArgumentException("Некорректный КНФ");

        return new SKNF(mask, vars);
    }

    public SKNF(BoolFunction boolFunction) {
        super(boolFunction.vars);
        int i = 0;
        for (int a : boolFunction.array) {
            if (a == 0) set.add(new BinNumber(i, boolFunction.vars));
            i++;
        }
        setDelimiter();
    }

    public void setDelimiter() {
        delimiter = ") & (";
    }

    public String toString() {
        return "(" + super.toString() + ")";
    }
}