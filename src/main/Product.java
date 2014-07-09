/*
 * A version of CombinedFunction that multiplies instead of adding
 */

package main;

import java.util.ArrayList;

/**
 *
 * @author Christopher Hittner
 */
public class Product extends CombinedFunction{
    
    public Product(ArrayList<Function> values){
        super(values);
    }
    
    @Override
    public String toString(){
        String str = "";
        for(Function f : values){
            if(!f.toString().equals((new Value(1)).toString()))
                str += f + " * ";
        }
        return str.substring(0, str.length() - 3);
    }
    
    @Override
    public Function buildRandomEquivalent(){
        int choice = (int)(2 * Math.random());
        
        ArrayList<Function> newValues = super.buildRandomEquivalent().getValues();
        
        //Combines like functions with this one
        for(int i = newValues.size() - 1; i >= 0; i++){
            Function f = newValues.get(i);
            if(f.getClass().equals(this.getClass())){
                newValues.addAll(((Product) f).values);
                newValues.remove(f);
            }
        }
        
        Product result;
        
        result = new Product(newValues);
        
        return result;
        
    }
}
