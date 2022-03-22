package discrete_structures;

import java.util.Arrays;

public class BoolFunction extends BinNumber {
    public int vars;

    public BoolFunction(int[] _array) {
        super(_array);
    }

    public BoolFunction(String _array) {
        super(_array);
    }

    public BoolFunction(int number) {
        super(number);
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
        int period = 1 << arg;
        for (int i = 0, x = 0, y = 0; i < array.length; i++) {
            int ost = i % period / (period / 2);
            if (ost == 0) array[i] = ost0.array[x++];
            else array[i] = ost1.array[y++];
        }
        vars = ost0.vars + 1;
    }

    public BoolFunction(SDNF sdnf, int _vars) {
        setVars(_vars);
        array = new int[pow(2, vars)];
        for (BinNumber a : sdnf.set) {
            array[a.parseToInt()] = 1;
        }
        length = array.length;
    }

    public BoolFunction(SKNF sknf, int _vars) {
        setVars(_vars);
        array = new int[pow(2, vars)];
        Arrays.fill(array, 1);
        for (BinNumber a : sknf.set) {
            array[a.parseToInt()] = 0;
        }
        length = array.length;
    }

    public static BoolFunction randBoolFunction(int _vars) {
        return new BoolFunction(parseIntToBin(randInt(pow(2, pow(2, _vars))), _vars), _vars);
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

    public int getValue(int id) {
        if (id >= array.length)
            throw new IllegalArgumentException("Для данного набора не существует значения в функции");
        return array[id];
    }

    public int getValue(String vector) {
        BinNumber binNumber = new BinNumber(vector);
        return getValue(binNumber.parseToInt());
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

    public void setVars(int _vars) {
        if (_vars < 1 || _vars > App.MAX_VARS)
            throw new IllegalArgumentException("Количество переменных должно быть больше нуля и не превышать " + App.MAX_VARS);
        vars = _vars;
    }
}
