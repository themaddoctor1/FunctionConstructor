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
            if(equiv != this)
                return equiv.buildRandomEquivalent();
            return this;
        } catch(NullPointerException n){
            return this;
        }
    }
    
}
