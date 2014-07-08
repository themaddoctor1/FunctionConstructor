/*
 * The basis for all of the types of functions
 */

package main;

import java.util.ArrayList;

/**
 *
 * @author Christopher
 */
public abstract class Function {
    
    //Holds all of the equivalent values
    private final ArrayList<Function> equivalents = new ArrayList<>();
    
    /*
     * Each function type has a unique way of outputting values
     */
    public abstract String toString();
    
    /**
     * Tests and adds a new equivalent Function
     * @param f The value to try to add
     */
    public void addEquivalent(Function f){
        //System.out.println("Testing " + f + "...");
        //System.out.println(equivalents);
        for(Function fun: this.equivalents){
            //System.out.println("Found " + fun);
            if(f.toString().equals(fun.toString())){
                //System.out.println("It was there already; " + f + " = " + fun);
                fun = f;
                return;
            }
        }
        //System.out.println(f + " = " + this);
        if(!f.toString().equals(this.toString())){
            //System.out.println("Added " + f);
            equivalents.add(f);
            //System.out.println(equivalents.size() == length);
            for(Function fun : equivalents){
                boolean proceed = true;
                for(Function test : fun.getEquivalents())
                    if(fun.equals(test))
                        proceed = false;
                if(proceed)
                    fun.addEquivalent(f);
            }
        }
        //System.out.println();
        
    }
    
    public abstract Function buildRandomEquivalent();
    public abstract ArrayList<Function> getValues();
    
    public Function getEquivalent(int index){
        return equivalents.get(index);
    }
    
    public Function getEquivalent(){
        try{
            return getEquivalent((int)(equivalents.size() * Math.random()));
        } catch (Exception e){
            return this;
        }
    }
    
    public ArrayList<Function> getEquivalents(){
        return equivalents;
    }
    
    
}
