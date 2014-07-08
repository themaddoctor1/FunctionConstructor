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
        return new Product(super.buildRandomEquivalent().getValues());
    }
}
