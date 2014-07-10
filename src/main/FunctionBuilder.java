/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 *
 * @author Christopher
 */
public class FunctionBuilder {
    static final ArrayList<Function> functions = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        loadTrigFunctions();
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("Welcome to the Function Constructor!");
        
        while(true){
            
            System.out.println();
            
            System.out.print(">");
            String command = in.nextLine().toLowerCase();
            
            ArrayList<String> pieces = new ArrayList<>();
            
            while(true){
                try {
                pieces.add(command.substring(0,command.indexOf(" ")));
                command = command.substring(command.indexOf(" ") + 1);
                } catch(Exception e){
                    if(!command.equals("") && !command.equals(" "))
                        pieces.add(command);
                    break;
                }
            }
            
            try {
                
                if(pieces.get(0).equals("load")){
                    if(pieces.get(1).equals("trig"))
                        loadTrigFunctions();
                    else if(pieces.get(1).equals("all")){
                        loadTrigFunctions();
                        System.out.println("All functions available have been loaded.");
                    }else
                        System.out.println("That data package was not recognized. Try another query.");
                } else if(pieces.get(0).equals("run")){
                    if(pieces.get(1).equals("learning_cycle"))
                        prepareLearningFunction();
                    else
                        System.out.println("That process was not recognized. Try another query.");
                } else if(pieces.get(0).equals("reset")){
                    if(pieces.get(1).equals("memory")){
                        functions.removeAll(getFunctionList());
                        System.out.println("All known functions have been wiped from memory.");
                    }
                    else
                        System.out.println("That item either cannot be reset or does not exist. Try another query.");
                } else if(pieces.get(0).equals("display"))
                    if(pieces.get(1).equals("memory")){
                        System.out.println(getFunctionList().size() +  " functions memorized:");
                        for(Function f : getFunctionList()){
                            System.out.println("    " + f);
                        }
                    } else
                        System.out.println("That item either does not exist or cannot be displayed. Try another query.");
                    
                else if(pieces.get(0).equals("exit") || pieces.get(0).equals("quit")){
                    System.out.println("Goodybe.");
                    System.exit(0);
                } else if(pieces.get(0).equals("help")|| pieces.get(0).equals("?")){
                    
                    if(pieces.size() == 1){
                        System.out.println("List of available functions:");
                        System.out.println("    display - Displays data for the requested item\n    load - Loads a data package\n    run - Runs a specified process\n    reset - Resets a specified item");
                        System.out.println("    help/exit - Exits the program");
                    } else if(pieces.get(1).equals("display")){
                        System.out.println("Displayed the requested item if it is allowed.");
                        System.out.println("Available options:\n    memory - Contains every function currently stored in memory.");
                    } else if(pieces.get(1).equals("load")){
                        System.out.println("Loads a series of functions to memory.");
                        System.out.println("Available packages:\n    all - Simple option that loads every single package at once\n    trig - A series of Trigonometric Functions\n");
                    } else if(pieces.get(1).equals("reset")){
                        System.out.println("Resets the requested item if it is allowed.");
                        System.out.println("Available options:\n    memory - Contains every function it is currently storing. Does not\n             include loadable function packages.");
                    } else if(pieces.get(1).equals("run")){
                        System.out.println("Runs the requested process if it is available.");
                        System.out.println("Available processes:\n    learning_cycle - Runs the cycle program that is used to create new functions");
                    } else if(pieces.get(1).equals("exit") || pieces.get(1).equals("quit")){
                        System.out.println("Exits the program.");
                    } else
                        System.out.println("That function does not exist.");
                } else
                    System.out.println("Unable to complete requested command. Try another query");
            } catch (Exception e){
                System.out.println("Unable to complete requested command. Try another query");
            }

        }
    }
    
    /**
     * Adds a series of Trigonometric functions to the functions list.
     */
    public static void loadTrigFunctions(){
        tryToAddFunctions(getTrigonometricFunctions());
        System.out.println("Loaded Trigonometric Functions");
    }
    
    /**
     * A user interface method that accesses the executeLearningFunction method
     */
    public static void prepareLearningFunction(){
        int observed;
        int desiredCycles;

        Scanner in = new Scanner(System.in);
        System.out.print("Input a number of cycles greater than zero: ");

           desiredCycles = in.nextInt();

        while(desiredCycles <= 0){
            System.err.println("ERROR: Invalid input!");
            System.out.print("Input a number of cycles greater than zero: ");

            desiredCycles = in.nextInt();
        }

        System.out.print("Input an index that is non-negative and less than " + functions.size() + ", or -1 for the list of known functions: ");
        
        observed = in.nextInt();

        while(observed < 0 || observed >= functions.size()){
            
            System.out.println();
            
            if(observed < -1)
                System.err.println("ERROR: Invalid input!");
            else
                for(int i = 0; i < functions.size(); i++){
                    System.out.println("Index " + i + ": " + functions.get(i));
                }
            System.out.println();
            System.out.print("Input an index greater than 0 and less than " + functions.size() + ": ");

            observed = in.nextInt();
        }

        System.out.println();

        executeLearningFunction(observed, desiredCycles);
    }
    
    public static void executeLearningFunction(int observed, int desiredCycles){

        System.out.println("Observed function: " + functions.get(observed));
        System.out.println();
        int initialRelations = 0;
        System.out.println("Known before: ");
        for(Function equal : functions)
            for(Function e : equal.getEquivalents()){
                System.out.println(equal + " = " + e);
                initialRelations++;
            }
        System.out.println();

        System.out.println("Learning Log:");

        int offset = 0;
        int initialSize = functions.get(observed).getEquivalents().size();
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < desiredCycles; i++){
            Function newEquivalent = (functions.get(observed).buildRandomEquivalent());
            functions.get(observed).addEquivalent(newEquivalent);
            try {
                String equation = (functions.get(observed).getEquivalents().get(i - offset + initialSize)).toString();
                if(i+1 < 10){
                    equation = "  " + equation;
                } else if(i+1 < 100){
                    equation = " " + equation;
                }
                //The division by 10.0 is based on my computer's run speed. The reccomended value may fluctuate.
                System.out.println("Created on cycle " + (i+1) + " after " + (System.currentTimeMillis() - startTime)/1000.0 + " seconds of runtime: " + equation);
                //System.out.println((System.currentTimeMillis() - startTime) + "," + (i - offset + initialSize));
                functions.add(newEquivalent);
            } catch(Exception e){
                offset++;
            }
        }


        System.out.println();

        int finalRelations = 0;
        
        //Counts the number of functions and stores the new ones
        System.out.println("Known after: ");
        for(int i = 0; i < functions.size(); i++){
            Function f = functions.get(i);
            for(Function related : f.getEquivalents()){
                System.out.println(f + " = " + related);
                finalRelations++;
                tryToAddFunction(related);
            }
        }
        System.out.println();

        if((finalRelations - initialRelations) > 0)
            System.out.println("The program learned " + (finalRelations - initialRelations) + " new relations using the given data.");
        else
            System.out.println("The program didn't learn any new relations using the given data.");

        int newFunctions = (functions.get(observed).getEquivalents().size() - initialSize);

        if(newFunctions > 0)
            System.out.println("Using the data available, " + newFunctions + " new functions were successfully created and stored.");
        else
            System.out.println("The program didn't create any new functions using the given data.");
            
            
    }

    private static ArrayList<Function> getTrigonometricFunctions() {
        ArrayList<Function> list = new ArrayList<>();
        //Creates the Trigonometric functions
        Value X = new Value("x");
        Function sin = new SingleFunction("sin", X);
        Function cos = new SingleFunction("cos", X);
        Function tan = new SingleFunction("tan", X);
        Function csc = new SingleFunction("csc", X);
        Function sec = new SingleFunction("sec", X);
        Function cot = new SingleFunction("cot", X);
        Function ONE = new Value(1);
        
        //sin(2@)
        ArrayList<Function> sin2xfncs = new ArrayList<>(); sin2xfncs.add(new Value(2)); sin2xfncs.add(X);
        Function sin2x = new SingleFunction("sin", new Product(sin2xfncs));
        
        ArrayList<Function> sin2xequiv = new ArrayList<>();
        sin2xequiv.add(new Value(2));
        sin2xequiv.add(sin);
        sin2xequiv.add(cos);
        sin2x.addEquivalent(new Product(sin2xequiv));
        
        //The inverse relations
        sin.addEquivalent(new Fraction(new Value(1),csc));
        cos.addEquivalent(new Fraction(new Value(1),sec));
        tan.addEquivalent(new Fraction(new Value(1),cot));
        
        csc.addEquivalent(new Fraction(new Value(1),sin));
        sec.addEquivalent(new Fraction(new Value(1),cos));
        cot.addEquivalent(new Fraction(new Value(1),tan));
        
        //   sin/cos = tan      cos/sin = cot
        tan.addEquivalent(new Fraction(sin,cos));
        cot.addEquivalent(new Fraction(cos,sin));
        
        //sinx^2 + cosx^2 = 1
        Exponent sineSquare = new Exponent(sin, new Value(2));
        Exponent cosineSquare = new Exponent(cos, new Value(2));
        ArrayList<Function> sincos2 = new ArrayList<>();
        sincos2.add(sineSquare); sincos2.add(cosineSquare);
        ONE.addEquivalent(new CombinedFunction(sincos2));
        
        //Negative Trig Functions
        
        
        ArrayList<Function> negVals = new ArrayList<>(); negVals.add(new Value(-1)); negVals.add(X);
        Product negPar = new Product(negVals);
        //    cos(-x) = cos(x)
        cos.addEquivalent(new SingleFunction("cos", negPar));
        //    cos(x) = 1/sec(-x)
        cos.addEquivalent(new Fraction(ONE, new SingleFunction("sec", negPar)));
        //    sin(x) = -sin(-x)
        ArrayList<Function> negSin = new ArrayList<Function>(); negSin.add(new Value(-1)); negSin.add(new SingleFunction("sin", negPar));
        sin.addEquivalent(new Product(negSin));
        //    sin(x) = -1/csc(-x)
        sin.addEquivalent(new Fraction(new Value(-1), new SingleFunction("csc", negPar)));
        
        
        
        list.add(sin);
        list.add(cos);
        list.add(tan);
        list.add(csc);
        list.add(sec);
        list.add(cot);
        list.add(ONE);
        list.add(sin2x);
        
        return list;
    }

    public static ArrayList<Function> getFunctionList() {
        return functions;
    }
    
    public static void tryToAddFunction(Function f){
        for(Function fun : functions){
            if(fun.toString().equals(f.toString())){
                return;
            }
        }
        functions.add(f);
    }
    
    public static void tryToAddFunctions(ArrayList<Function> f){
        for(Function fun: f)
            tryToAddFunction(fun);
    }
    
    
}
