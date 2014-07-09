/*
 * Stores a series of functions that are added together
 */

package main;

import java.util.ArrayList;

/**
 *
 * @author Christopher Hittner
 */
public class CombinedFunction extends Function{
    
    protected final ArrayList<Function> values;
    
    public CombinedFunction(){
        values = new ArrayList<>();
    }
    
    public CombinedFunction(ArrayList<Function> val){
        values = val;
    }
    
    public void addValue(Function f){
        values.add(f);
    }
    
    public ArrayList<Function> getValues(){
        return values;
    }
    
    public String toString(){
        String str = "";
        for(Function f : values){
            str += f + " + ";
        }
        return str.substring(0, str.length() - 3);
        
    }
    
    @Override
    public Function buildRandomEquivalent(){
        //Then proceed as usual
        ArrayList<Function> newValues = new ArrayList<>();
        for(Function f : values){
            newValues.add(f);
        }
        
        //Combines like functions with this one
        for(int i = newValues.size() - 1; i >= 0; i++){
            Function f = newValues.get(i);
            if(f.getClass().equals(this.getClass())){
                newValues.addAll(((Product) f).values);
                newValues.remove(f);
            }
        }
        
        CombinedFunction f = new CombinedFunction(newValues);
        if(values.size() == 0){
            return getEquivalent().buildRandomEquivalent();
        }
        int index = (int)(Math.random() * f.getValues().size());
        Function equivalent = f.getValues().get(index).buildRandomEquivalent();
        f.getValues().remove(index);
        f.getValues().add(index, equivalent);
        
        return f;
    }
    
    
}
