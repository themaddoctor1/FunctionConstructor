/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.util.ArrayList;

/**
 *
 * @author Christopher Hittner
 */
public class SingleFunction extends Function{
    
    final String name;
    ArrayList<Function> parameter;
    
    public SingleFunction(String nm, Function value){
        name = nm;
        parameter = new ArrayList<>();
        parameter.add(new Value(value));
    }
    
    /**
     * Creates a SingleFunction
     * @param nm
     * @param value the list of parameters. "x" is always a constant
     */
    public SingleFunction(String nm, ArrayList<Function> value){
        name = nm;
        parameter = value;
    }
    
    public String getName(){
        return name;
    }
    
    public ArrayList<Function> getParameter(){
        return parameter;
    }
    
    public CombinedFunction getParameterFunction(){
        return new CombinedFunction(parameter);
    }
    
    public String toString(){
        String result = name + "(";
        for(Function f : parameter){
            result += f + " + ";
        }
        
        return (result.substring(0, result.length() - 3) + ")");
    }

    @Override
    public Function buildRandomEquivalent() {
        try{
            Function equivalent = this.getEquivalent();
            if(!equivalent.toString().equals(this.toString()))
                return equivalent.getEquivalent();
            else/*
                for(Function f : FunctionBuilder.getFunctionList()){
                    for(Function fun : f.getEquivalents()){
                        if(fun.toString().equals(this.toString()))
                            return fun;
                    }
                }*/
            return this;
        } catch(Exception e){
            return this;
        }
    }

    @Override
    public ArrayList<Function> getValues() {
        ArrayList<Function> f = new ArrayList<Function>();
        f.add(this);
        return f;
    }
    
}
