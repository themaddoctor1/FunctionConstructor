/*
 * Stores an exponent with a base and an exponent
 */

package main;

import java.util.ArrayList;

/**
 *
 * @author Christopher Hittner
 */
public class Exponent extends Function{
    private final ArrayList<Function> base, exponent;
    
    public Exponent(ArrayList<Function> bs,  ArrayList<Function> ex){
        base = bs;
        exponent = ex;
    }
    
    public Exponent(Function bs,  Function ex){
        base = new ArrayList<>();
        base.add(bs);
        exponent = new ArrayList<>();
        exponent.add(ex);
    }
    
    
    @Override
    public String toString() {
        String result = "";
        
        if(base.size() > 1){
            result += "(";
            for(Function f : base)
                result += f + " + ";
            result = result.substring(0, result.length() - 3);
            result += ")";
        } else
            result += base.get(0);
        
        result += " ^ ";
            
        if(exponent.size() > 1){
            result += "(";
            for(Function f : exponent)
                result += f + " + ";
            result = result.substring(0, result.length() - 3);
            result += ")";
        } else
            result += exponent.get(0);
        
        return result;
    }

    @Override
    public Function buildRandomEquivalent() {
        Function result = toProduct();
        int choice = (int)(Math.random() * 2);
        
        if(result != null)
            return result;
        else if(choice == 0){
            Function bs = new CombinedFunction(base).buildRandomEquivalent();
            result = new Exponent(bs.getValues(), exponent);
        } else {
            Function ex = new CombinedFunction(exponent).buildRandomEquivalent();
            result = new Exponent(base, ex.getValues());
        }
        
        return result;
        
    }
    
    public Product toProduct(){
        ArrayList<Function> product = new ArrayList<>();
        if(exponent.size() == 1){
            int value;
            try{
                if((((Value)(exponent.get(0))).getValue()) instanceof Integer)
                    value = (Integer)(((Value)(exponent.get(0))).getValue());
                else
                    return new Product(product);
            } catch(Exception e){
                return new Product(product);
            }
            for(int i = 0; i < value; i++){
                product.add(new CombinedFunction(base));
            }
        }
        return new Product(product);
    }
    
    
    @Override
    public ArrayList<Function> getValues() {
        ArrayList<Function> f = new ArrayList<Function>();
        f.add(this);
        return f;
    }
}
