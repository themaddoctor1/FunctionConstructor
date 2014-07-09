/*
 * Holds a value, whether it is a number or a variable
 */

package main;

import java.util.ArrayList;

/**
 *
 * @author Christopher Hittner
 */
public class Value extends Function {
    
    Object value;
    
    public Value(Object value){
        this.value = value;
    }
    
    public Object getValue(){
        return value;
    }
    
    public ArrayList<Function> getValues(){
        ArrayList<Function> result = new ArrayList<>();
        result.add(this);
        return result;
    }
    
    public String toString(){
        return value.toString();
    }
    
    @Override
    public Function buildRandomEquivalent(){
        try{
            Function equiv = getEquivalent();
            
            if(value instanceof Integer){
                int number = (Integer) value;
                if(number < 0){
                    ArrayList<Function> product = new ArrayList<>();
                    product.add(new Value(-1));
                    product.add(new Value(-1 * number));
                    
                    return new Product(product); 
                }
            }
            if(equiv != this)
                return equiv.buildRandomEquivalent();
            else
                return this;
        } catch(Exception n){
            return this;
        }
    }
    
}
