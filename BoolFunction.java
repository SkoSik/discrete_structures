package discrete_structures;

import java.util.Arrays;

public class BoolFunction extends BinNumber {
    public int vars;

    public BoolFunction(int[] _array) {
        super(_array);
        setVars(log2(_array.length));
    }

    public BoolFunction(String _array) {
        super(_array);
        setVars(log2(_array.length()));
    }

    public BoolFunction(int number) {
        super(number);
        setVars(log2(number));
    }

    public BoolFunction(int number, int _vars) {
        setArray(parseIntToBin(number, _vars));
        setVars(_vars);
    }

    public BoolFunction(int[] _array, int _vars) {
        setArray(_array);
        setVars(_vars);
    }

    public BoolFunction(String _array, int _vars) {
        setArray(_array);
        setVars(_vars);
    }

    public BoolFunction(BoolFunction ost0, BoolFunction ost1, int arg) {
        int S0 = ost0.array.length;
        int S1 = ost1.array.length;


        if (S0 != S1) throw new IllegalArgumentException("Остаточные не соотвествуют одной функции");
        if ((S0 & (S0 - 1)) != 0 && (S1 & (S1 - 1)) != 0)
            throw new IllegalArgumentException("Заданных остаточных не существует");
        if (ost0.vars + 1 < arg)
            throw new IllegalArgumentException("У функции с такими остаточными нет заданной переменной");
        array = new int[ost0.array.length * 2];
        vars = ost0.vars + 1;
        int period = 1 << (vars - arg + 1);
        for (int i = 0, x = 0, y = 0; i < array.length; i++) {
            int ost = i % period / (period / 2);
            if (ost == 0) array[i] = ost0.array[x++];
            else array[i] = ost1.array[y++];
        }
    }

    public BoolFunction(SDNF sdnf, int _vars) {
        if (_vars < 1 || _vars > App.MAX_VARS)
            throw new IllegalArgumentException("Количество переменных должно быть больше нуля и не превышать " + App.MAX_VARS);
        vars = _vars;
        array = new int[pow(2, vars)];
        for (BinNumber a : sdnf.set) {
            array[a.parseToInt()] = 1;
        }
        length = array.length;
    }

    public BoolFunction(SKNF sknf, int _vars) {
        if (_vars < 1 || _vars > App.MAX_VARS)
            throw new IllegalArgumentException("Количество переменных должно быть больше нуля и не превышать " + App.MAX_VARS);
        vars = _vars;
        array = new int[pow(2, vars)];
        Arrays.fill(array, 1);
        for (BinNumber a : sknf.set) {
            array[a.parseToInt()] = 0;
        }
        length = array.length;
    }

    public static BoolFunction randBoolFunction(int _vars) {
        if (_vars > 4) {
            int[] array = new int[pow(2, _vars)];
            for (int i = 0; i < array.length; i++) {
                array[i] = randInt(2);
            }
            return new BoolFunction(array, _vars);
        }
        return new BoolFunction(parseIntToBin(randInt(pow(2, pow(2, _vars))), _vars), _vars);
    }

    public static String parseIntToBin(int number, int vars) {
        String array = "";

        do {
            array = number % 2 + array;
            number /= 2;
        } while (number > 0);

        return String.format("%" + pow(2, vars) + "s", array).replace(' ', '0');
    }

    public BoolFunction getResidual(int ost, int var) {
        if (var > vars) throw new IllegalArgumentException("Данной переменной нет в функции");
        int[] _array = new int[array.length / 2];
        int period = 1 << (vars - var + 1), j = 0;
        for (int i = 0; i < array.length; i++) {
            if (i % period / (period / 2) == ost) {
                _array[j++] = array[i];
            }
        }
        return new BoolFunction(_array, vars - 1);
    }

    public boolean checkFictitiousness(int var) {
        return getResidual(0, var).equals(getResidual(1, var));
    }

    public boolean isSaveZero() {
        return (array[0] == 0);
    }

    public boolean isSaveOne() {
        return (array[length - 1] == 1);
    }

    public boolean isSelfDuality() {
        for (int i = 0; i < length; i++) {
            if (array[i] == array[length - 1 - i]) return false;
        }
        return true;
    }

    public boolean isMonotony() {
        BinNumber[] binNumbers = getSets();
        for (int i = 0; i < length; i++) {
            int wi = binNumbers[i].getWeight();
            for (int j = i + 1; j < length; j++) {
                int wj = binNumbers[j].getWeight();
                if ((wi - wj == 1 && getValue(i) < getValue(j)) || (wj - wi == 1 && getValue(j) < getValue(i)))
                    return false;
            }
        }
        return true;
    }

    public boolean isLinear() {
        int[][] polynom = new int[length][length];
        for (int i = 0; i < length; i++) {
            polynom[i][0] = array[i];
        }

        for (int i = 1; i < length; i++) {
            for (int j = 0; j < length - i; j++) {
                polynom[j][i] = sum2(polynom[j][i - 1], polynom[j + 1][i - 1]);
            }
        }

        for (int i = 0; i < length; i++) {
            if (polynom[length - 1 - i][i] == 1 && (new BinNumber(i).getWeight() > 1)) return false;
        }
        return true;
    }

    public boolean[] getClasses() {
        boolean[] classes = new boolean[5];

        classes[0] = isSaveZero();
        classes[1] = isSaveOne();
        classes[2] = isSelfDuality();
        classes[3] = isMonotony();
        classes[4] = isLinear();

        return classes;
    }

    public void setVars(int _vars) {
        if ((array.length & (array.length - 1)) != 0)
            throw new IllegalArgumentException("Некорректная длина булевой функции");
        if (_vars < 1 || _vars > App.MAX_VARS)
            throw new IllegalArgumentException("Количество переменных должно быть больше нуля и не превышать " + App.MAX_VARS);
        vars = _vars;
    }
}