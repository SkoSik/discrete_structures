package discrete_structures;

public class BinNumber {
    int vars;
    int[] array;

    public BinNumber(int number) {
        array = parseIntToBin(number);
        vars = log2(array.length);
    }

    public BinNumber(int[] _array) {
        array = _array;
        vars = log2(array.length);
    }

    public BinNumber(String _array) {
        array = new int[_array.length()];
        for (int i = 0; i < _array.length(); i++) {
            array[i] = _array.charAt(i) - '0';
        }
        vars = log2(_array.length());
    }

    public BinNumber(int _vars, int[] _array) {
        array = _array;
        vars = _vars;
    }

    public BinNumber(int _vars, String _array) {
        array = new int[_array.length()];
        for (int i = 0; i < _array.length(); i++) {
            array[i] = _array.charAt(i) - '0';
        }
        vars = _vars;
    }

    int parseToInt() {
        int b = (int) Math.pow(2, array.length - 1), sum = 0;
        for (int a : array) {
            if (b == 0) b = 1;
            sum += a * b;
            b /= 2;
        }
        return sum;
    }

    public static int log2(int a) {
        if (a <= 0) throw new ArithmeticException("The logarithm parameter must be greater than 0");
        int n = 0;
        while (a != 1) {
            a /= 2;
            n++;
        }
        return n;
    }

    public static BinNumber randBinNumber(int max) {
        return new BinNumber(randInt(max));
    }

    public static BinNumber randBinNumberByVar(int _vars) {
        return new BinNumber(_vars, parseIntToBin(randInt(pow(2, _vars))));
    }

    public static int randInt(int max) {
        return (int) Math.floor(Math.random() * max) + 1;
    }

    public static int pow(int a, int b) {
        return (int) Math.pow(a, b);
    }

    public static int[] parseIntToBin(int number) {
        int tmp = log2(number) + 1;
        int new_size = 1, vars = 0;
        while (new_size < tmp) {
            vars++;
            new_size *= 2;
        }
        int[] array = new int[new_size];

        for (int i = new_size - 1; i >= 0; i--) {
            array[i] = number % 2;
            number /= 2;
        }
        return array;
    }

    public String toString() {
        String s = "";
        for (int a : array) s += (a == 0 ? '0' : '1');
        return s;
    }
}
