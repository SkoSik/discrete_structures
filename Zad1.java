package discrete_structures;

import java.util.Date;

public class Zad1 {

    public static int rand(int max){
        return (int)(new Date()).getTime() % max;
    }

    public static int randInt(int max){
        return (int) Math.floor(Math.random()*max);
    }

    public static int pow(int a, int b){
        return (int) Math.pow(a,b);
    }



    public static String toBinaryString(int a){
        int tmp;
        String str="";
        while (a>0){
            tmp = a%2;
            str= Integer.toString(tmp) + str;
            a/=2;
        }
        return str;
    }

    public static void main(String[] args) {
        //  int n=3;
        //  int a=randInt(pow(2,n));
        //  System.out.println(a);
        //  System.out.println(toBinaryString(a));

       // BinNumber test = new BinNumber("10000000");
      //  System.out.println(test.parseToInt());
        BinNumber test2 = BinNumber.randBinNumberByVar(3);
        System.out.println(test2);
    }

}
