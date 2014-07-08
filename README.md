#FunctionConstructor
    A program that can take functions and use equivalence between functions to create new ones. 
    It comes with some Trigonometry functions to test it out. 

##About
    This program was written in Java 7, and is designed to be able to take a function, or a
    series of functions, and use the relation between them to create new functions.

##Statistics
    To check the run speed of the program, I had it attempt to create new versions of the
    Pythagorean identity of Trigonometry. When I ran it, it managed to create 359 new
    functions using the data that I provided it with. A graph comparing the number of
    functions created and the time in milliseconds [as measured by System.currentTimeMillis()]
    is included in the Repository.

##How to Use
    The program was written using Netbeans 8.0, so it is easiest to use in an IDE, such as
    Netbeans or Eclipse (the two that I've heard of). To put into Netbeans, just put it
    with your other projects. It already contains the files that Netbeans created when
    I first created the project, so it's one less thing to do. In the file, there is a
    variable called observed. This variable will choose which index in the functions
    ArrayList will be used to create more functions in the tester that I built, named
    FunctionBuilder.
