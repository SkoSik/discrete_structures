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
        int b = pow(2, array.length - 1), sum = 0;
        for (int a : array) {
            if (b == 0) b = 1;
            sum += a * b;
            b /= 2;
        }
        return sum;
    }

    public static int log2(int a) {
        if (a <= 0) throw new ArithmeticException("Параметр логарифма должен быть больше 0");
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
        if(_vars<1 || _vars>5) throw new IllegalArgumentException("Количество переменных должно быть больше нуля и не превышать 5");
        return new BinNumber(_vars, parseIntToBin(randInt(pow(2, pow(2, _vars))), _vars));
    }

    public static int randInt(int max) {
        return (int) Math.floor(Math.random() * max);
    }

    public static int pow(int a, int b) {
        return (int) Math.pow(a, b);
    }

    public static int[] parseIntToBin(int number) {
        int tmp;
        try {
            tmp = log2(number) + 1;
        } catch (ArithmeticException e) {
            tmp = 0;
        }
        int new_size = 1;
        while (new_size < tmp) new_size *= 2;
        int[] array = new int[new_size];

        for (int i = new_size - 1; i >= 0; i--) {
            array[i] = number % 2;
            number /= 2;
        }
        return array;
    }

    public static int[] parseIntToBin(int number, int vars) {
        int new_size = pow(2, vars);
        int[] array = new int[new_size];

        for (int i = new_size - 1; i >= 0; i--) {
            array[i] = number % 2;
            number /= 2;
        }
        return array;
    }

    public String toString() {
        String s = "";
        int tmp=1;
        for (int a : array) {
            s += Integer.toString(a);
            if(tmp%4==0) s+=" ";
            tmp++;
        }
        return s;
    }
}
