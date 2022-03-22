package discrete_structures;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

public class BinNumber {
    public int length;
    public int[] array;

    public BinNumber() {
    }

    public BinNumber(int[] _array) {
        setArray(_array);
        length = _array.length;
    }

    public BinNumber(String _array) {
        setArray(_array);
        length = _array.length();
    }

    public BinNumber(int number) {
        setArray(parseIntToBin(number));
        length = array.length;
    }

    public int parseToInt() {
        int b = pow(2, array.length - 1), sum = 0;
        for (int a : array) {
            sum += a * b;
            b /= 2;
        }
        return sum;
    }

    public static BinNumber randBinNumber() {
        return new BinNumber(randInt(pow(2, App.VARS)));
    }

    public static String parseIntToBin(int number) {
        String array = "";
        if(number == 0)
            return "0";
        while (number > 0) {
            array += number % 2;
            number /= 2;
        }
        String reverseArr = "";
        for(int i = array.length()-1; i >= 0; i--)
            reverseArr += array.charAt(i);
        return reverseArr;
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
        for (int a : array) {
            s += Integer.toString(a);
        }
        return s;
    }

    public String toOutput() {
        String s = "";
        int tmp = 1;
        for (int a : array) {
            s += Integer.toString(a);
            if (tmp % 4 == 0) s += " ";
            tmp++;
        }
        return s;
    }

    public String toNFVars() {
        String s = "";
        int i = 1;
        for (int a : array) {
            s += ((a == 1) ? "" : "¬") + "x" + i;
            i++;
        }
        return s;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinNumber binNumber = (BinNumber) o;
        return length == binNumber.length && Arrays.equals(array, binNumber.array);
    }

    public int hashCode() {
        int result = Objects.hash(length);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}
