/*
 * Holds a fraction with a numerator and a denominator
 */

package main;

import java.util.ArrayList;

/**
 *
 * @author Christopher Hittner
 */
public class Fraction extends Function{
    private final ArrayList<Function> numerator;
    private final ArrayList<Function> denominator;
    
    public Fraction(ArrayList<Function> num, ArrayList<Function> den){
        numerator = num;
        denominator = den;
    }
    
    public Fraction(Function num, ArrayList<Function> den){
        numerator = new ArrayList<>();
        numerator.add(num);
        denominator = den;
    }
    
    public Fraction(ArrayList<Function> num, Function den){
        numerator = num;
        denominator = new ArrayList<>();
        denominator.add(den);
    }
    
    public Fraction(Function num, Function den){
        numerator = new ArrayList<>();
        numerator.add(num);
        denominator = new ArrayList<>();
        denominator.add(den);
    }
    
    public Fraction(){
        numerator = new ArrayList<>();
        denominator = new ArrayList<>();
    }
    
    public ArrayList<Function> getNumerator(){
        return numerator;
    }
    
    public void addToNumerator(Function f){
        numerator.add(f);
    }
    
    public ArrayList<Function> getValues(){
        ArrayList<Function> f = new ArrayList<>();
        f.add(this);
        return f;
    }
    
    public ArrayList<Function> getDenominator(){
        return denominator;
    }
    
    public void addToDenominator(Function f){
        denominator.add(f);
    }
    
    @Override
    public String toString() {
        String num = new CombinedFunction(numerator).toString();
        if(num.indexOf("+") != -1 || num.indexOf("/") != -1)
            num = "(" + num + ")";
        
        String den = new CombinedFunction(denominator).toString();
        if(den.indexOf("+") != -1 || den.indexOf("/") != -1)
            den = "(" + den + ")";
        
        String result = num + "/" + den;
        
        return result;
        
    }
    
    @Override
    public Function buildRandomEquivalent(){
        double option = (Math.random() * 2.0);
        boolean up = (option >= 1);
        Function result;
        if(up){
            Function num;
            if(numerator.size() > 1)
                num = new CombinedFunction(numerator).buildRandomEquivalent();
            else
                num = numerator.get(0);
            result = new Fraction(num, denominator);
        } else {
            Function den;
            if(denominator.size() > 1)
                den = new CombinedFunction(denominator).buildRandomEquivalent();
            else
                den = denominator.get(0).buildRandomEquivalent();
            result = new Fraction(numerator, den);
        }
        
        for(Function f : getEquivalents()){
            if(f.toString().equals(result.toString()))
                result = f.buildRandomEquivalent();
        }
        
        //System.out.println(this + " = " + result);
        
        return result;
    }
    
}
