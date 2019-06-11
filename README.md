# Java_calculator_with_GUI
A Gui which takes a user input for buttons and attempts to calculations.

Calculator.jar is the combined class files into a runnable jar. You can execute it and a GUI will be made available for use.

calcFrame.java is where the methods and constructors are defined. All of the operands such as addition, subtraction, multiplication, etc are defined in this class. 

The gui is constructed using grid layout for the number pad buttons and a screen to return the output.

 A reset, backspace, and calculate button is also inputted, though results will still return upon the use of an operand. Each button press generates an event which is separated between number/decimal and operation press. 

Exception handling includes back-to-back operands, which in this case erases the operation and undoes the last calculation performed. If the user attempts to divide by zero, an error is thrown for divide by zero and a new line is printed. If multiple decimals are detected, an error is printed and the last result is printed on a new line to be continued.
