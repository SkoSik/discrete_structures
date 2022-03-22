package discrete_structures;

import java.util.ArrayList;

public class Mask {
    ArrayList<char[]> varState = new ArrayList<>();
    ArrayList<int[]> varExistence = new ArrayList<>();
    int curmask = 0, vars = 0;
    public Mask(){
        vars = curmask = 0;
        varExistence = new ArrayList<>();
        varState = new ArrayList<>();
    }
    public Mask(int _vars){
        vars = _vars;
        curmask = 0;
        initNewMask();
    }
    public void newMask(){
        curmask++;
        initNewMask();
    }
    public void initNewMask(){
        varState.add(new char[vars]);
        varExistence.add(new int[vars]);
        for(int i = 0; i < vars; i++){
            varState.get(curmask)[i] = 'x';
            varExistence.get(curmask)[i] = 0;
        }
    }
    public void changeState(int i, char c){
        varState.get(curmask)[i] = c;
    }
    public char checkState(int i){
        return varState.get(curmask)[i];
    }
    public int checkEx(int i){
        return varExistence.get(curmask)[i];
    }
    public void increaseEx(int i){
        varExistence.get(curmask)[i]++;
    }
    public void decreaseEx(int i){
        varExistence.get(curmask)[i]--;
    }
    public void deleteOneMask(){
        varState.remove(curmask);
        varExistence.remove(curmask);
        curmask--;
    }
    public ArrayList<char[]> getVarState(){
        return varState;
    }
    public int getCurmask(){
        return curmask;
    }
}
