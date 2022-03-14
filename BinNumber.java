package discrete_structures;

import java.util.Arrays;
import java.util.Objects;

public class BinNumber {
    public int vars;
    public int[] array;

    public BinNumber(int number) {
        setArray(parseIntToBin(number));
        setVars(log2(array.length));
    }

    public BinNumber(int[] _array) {
        setArray(_array);
        setVars(log2(array.length));
    }

    public BinNumber(String _array) {
        setArray(_array);
        setVars(log2(array.length));
    }

    public BinNumber(int _vars, int[] _array) {
        setArray(_array);
        setVars(_vars);
    }

    public BinNumber(int _vars, String _array) {
        setArray(_array);
        setVars(_vars);
    }

    public BinNumber(BinNumber ost0, BinNumber ost1, int arg) {
        int S0 = ost0.array.length;
        int S1 = ost1.array.length;

        if (S0 != S1) throw new IllegalArgumentException("Остаточные не соотвествуют одной функции");
        if ((S0 & (S0 - 1)) != 0 && (S1 & (S1 - 1)) != 0)
            throw new IllegalArgumentException("Заданных остаточных не существует");
        if (ost0.vars + 1 < arg)
            throw new IllegalArgumentException("У функции с такими остаточными нет заданной переменной");
        array = new int[ost0.array.length * 2];
        int period = 1 << arg;
        for (int i = 0, x = 0, y = 0; i < array.length; i++) {
            int ost = i % period / (period / 2);
            if (ost == 0) array[i] = ost0.array[x++];
            else array[i] = ost1.array[y++];
        }
        vars = ost0.vars + 1;
    }

    public int parseToInt() {
        int b = pow(2, array.length - 1), sum = 0;
        for (int a : array) {
            if (b == 0) b = 1;
            sum += a * b;
            b /= 2;
        }
        return sum;
    }

    public BinNumber getResidual(int ost, int var) {
        if (var > vars) throw new IllegalArgumentException("Данной переменной нет в функции");
        int[] _array = new int[array.length / 2];
        int period = 1 << (vars - var + 1), j = 0;
        for (int i = 0; i < array.length; i++) {
            if (i % period / (period / 2) == ost) {
                _array[j++] = array[i];
            }
        }
        return new BinNumber(vars - 1, _array);
    }

    public boolean checkFictitiousness(int var) {
        return getResidual(0, var).equals(getResidual(1, var));
    }

    public static BinNumber randBinNumber(int max) {
        return new BinNumber(randInt(max));
    }

    public static BinNumber randBinNumberByVar(int _vars) {
        return new BinNumber(_vars, parseIntToBin(randInt(pow(2, pow(2, _vars))), _vars));
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

    public void setVars(int _vars) {
        if (_vars < 1 || _vars > 5)
            throw new IllegalArgumentException("Количество переменных должно быть больше нуля и не превышать 5");
        vars = _vars;
    }

    public void setArray(String _array) {
        array = new int[_array.length()];
        if (array.length == 0) throw new IllegalArgumentException("Данная строка не является булевой функцией");
        for (int i = 0; i < _array.length(); i++) {
            if (_array.charAt(i) != '0' && _array.charAt(i) != '1')
                throw new IllegalArgumentException("Данная строка не является булевой функцией");
            array[i] = _array.charAt(i) - '0';
        }
    }

    public void setArray(int[] _array) {
        for (int a : _array) {
            if (a != 0 && a != 1) throw new IllegalArgumentException("Данный массив не является булевой функцией");
        }
        array = _array;
    }

    public static int randInt(int max) {
        return (int) Math.floor(Math.random() * max);
    }

    public static int pow(int a, int b) {
        return (int) Math.pow(a, b);
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

    public String toString() {
        String s = "";
        int tmp = 1;
        for (int a : array) {
            s += Integer.toString(a);
            if (tmp % 4 == 0) s += " ";
            tmp++;
        }
        return s;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinNumber binNumber = (BinNumber) o;
        return vars == binNumber.vars && Arrays.equals(array, binNumber.array);
    }

    public int hashCode() {
        int result = Objects.hash(vars);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}
