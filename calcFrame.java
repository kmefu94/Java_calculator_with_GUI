import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class calcFrame extends JFrame
{
	//initialize instance variables with starting dimensions
	public static final int FRAME_WIDTH = 300;
	public static final int FRAME_HEIGHT = 425;
	//initialize instance variables for use in class
	private JTextArea textArea = new JTextArea(15,10);
	private JPanel clearPanel;
	private JPanel num;
	private JScrollPane scroll;
	private int counter = 0;
	private boolean calc = false;
	private double d1 = 0;
	private double d2 = 0;
	private double result = 0;
	private boolean add = false;
	private boolean sub = false;
	private boolean div = false;
	private boolean mult = false;
	private boolean equal = false;
	private String zeroerror = "\nCannot divide with zero, clear all";
	private boolean zerror = false;
	private String decerror = "\nNo multiple decimals, clear last or all";
	private boolean derror = false;
	private String opererror = "\nNo multiple operators, last operand ignored";
	private boolean oerror = false;
	private boolean decimal = false;
	private boolean starterror = false;
	private boolean newcalc = false;
	//constructor for calc frame
	public calcFrame()
	{
			setLayout(new BorderLayout());
			//add the reset panel
			add(reset(), BorderLayout.NORTH);
			//create text box
			textArea.setEditable(true);
			textArea.setText("");
			//create scroll bar for text box and attach
			scroll = new JScrollPane(textArea);
			//add the text area
			add(scroll, BorderLayout.CENTER);
			//add the numberpad
			add(numPad(), BorderLayout.SOUTH);

	}
		//constructor to create reset buttons on the top
		public JPanel reset()
		{
			clearPanel = new JPanel();
			clearPanel.setLayout(new GridLayout(1,2));
			JButton clearLast = new JButton("Clear Last");
			JButton clearAll = new JButton("Clear All");
			clearPanel.add(clearLast);
			clearPanel.add(clearAll);
			ActionListener wipeAll = new clearAll();
			clearAll.addActionListener(wipeAll);
			ActionListener delete = new clearLast();
			clearLast.addActionListener(delete);
			return clearPanel;
		}
		//create listeners for clear all and clear last
		class clearAll implements ActionListener
		{
			//@override
			public void actionPerformed(ActionEvent event)
			{
				textArea.setText("");
				counter = 0;
				calc = false;
				result = 0;
				add = false;
				sub = false;
				mult = false;
				div = false;
				equal = false;
				d1 = 0;
				d2 = 0;
				derror = false;
				zerror = false;
				oerror = false;			
				decimal = false;				
				starterror = false;
				newcalc = false;
			}
		}
		class clearLast implements ActionListener
		{	
			public void actionPerformed(ActionEvent event)
			{	
				String s = textArea.getText().replaceAll("[a-zA-Z\\s\\n]", "");
				char c = s.charAt(s.length()-1);
				if(s.length() > 0 && !starterror && !zerror && !oerror)
				//special cases for every key, resets so that calculations can be done again
				{
					if(!equal)
					{
					if(Character.isDigit(c) || c=='.')
					{
					textArea.setText(textArea.getText().substring(0, textArea.getText().length()-1));
					}
					if(!Character.isDigit(c) && c!='.')
					{
					//clear if decimal error to continue calculations
					if(derror)
					{
						textArea.setText(textArea.getText().replace(decerror, ""));
						derror = false;
						decimal = false;
					}
					if(c == '+')
					{
						textArea.setText(textArea.getText().substring(0, textArea.getText().length()-1));
						textArea.setText(textArea.getText().substring(0, textArea.getText().length()-1));
						add = false;
						equal = true;
					}			
					if(c == '-')
					{
						textArea.setText(textArea.getText().substring(0, textArea.getText().length()-1));
						textArea.setText(textArea.getText().substring(0, textArea.getText().length()-1));
						sub = false;
						equal = true;
					}
					if(c == '/')
					{
						textArea.setText(textArea.getText().substring(0, textArea.getText().length()-1));
						textArea.setText(textArea.getText().substring(0, textArea.getText().length()-1));
						div = false;
						equal = true;
					}
					if(c == '*')
					{
						textArea.setText(textArea.getText().substring(0, textArea.getText().length()-1));
						textArea.setText(textArea.getText().substring(0, textArea.getText().length()-1));
						mult = false;
						equal = true;
					}
					}
				}
				}
			}
		}
		//inner class to create numberpad for calculator
		public JPanel numPad()
		{
			num = new JPanel();
			//create individual buttons and attach listeners
			ActionListener key = new keyPress();
			ActionListener operand = new operands();
			JButton one = new JButton("1");
			one.addActionListener(key);
			JButton two = new JButton("2");
			two.addActionListener(key);
			JButton three = new JButton("3");
			three.addActionListener(key);
			JButton four = new JButton("4");
			four.addActionListener(key);
			JButton five = new JButton("5");
			five.addActionListener(key);
			JButton six = new JButton("6");
			six.addActionListener(key);
			JButton seven = new JButton("7");
			seven.addActionListener(key);
			JButton eight = new JButton("8");
			eight.addActionListener(key);
			JButton nine = new JButton("9");
			nine.addActionListener(key);
			JButton zero = new JButton("0");
			zero.addActionListener(key);
			JButton addBut = new JButton("+");
			addBut.addActionListener(operand);
			JButton subBut = new JButton("-");
			subBut.addActionListener(operand);
			JButton multBut = new JButton("*");
			multBut.addActionListener(operand);
			JButton divBut = new JButton("/");
			divBut.addActionListener(operand);
			JButton decBut = new JButton(".");
			decBut.addActionListener(key);
			JButton eqBut = new JButton("=");
			eqBut.addActionListener(operand);
			num.add(seven);
			num.add(eight);
			num.add(nine);
			num.add(addBut);
			num.add(four);
			num.add(five);
			num.add(six);
			num.add(subBut);
			num.add(one);
			num.add(two);
			num.add(three);
			num.add(multBut);
			num.add(zero);
			num.add(decBut);
			num.add(eqBut);
			num.add(divBut);
			num.setLayout(new GridLayout(4,4));
			return num;
		}
		//listener for inputting the numbers
		class keyPress implements ActionListener
		{
			//@override
			public void actionPerformed(ActionEvent event)
			{
				//retrieve source of event
  				Object obj = event.getSource();
   				JButton but = null;
   				String buttonText = "";
   				//casts button with object
				if(obj instanceof JButton)
				{
    	 			but = (JButton) obj;
     			}
     			//sets string of text to button text
				if(but != null)
		    	{
	     			buttonText = but.getText();
     			}
   				if(newcalc)
   				//checks if equal sign was pressed
   				{ 	
   					calc = false;
   					textArea.setText(textArea.getText());
   					equal = false;
   					result = 0;
					add = false;
					sub = false;
					mult = false;
					div = false;
					d1 = 0;
					d2 = 0;
   				}

     			//check if its another decimal
   				if(buttonText == ".")
   					{
   					//special case for decimals
   					if(decimal)
   						{   					
   						derror = true;
   						decimal = false;
     					textArea.setText(textArea.getText() + decerror);
   						}
   					
   						if(textArea.getText().length() < 1 || !Character.isDigit(textArea.getText().charAt(textArea.getText().length()-1)))
   						{
   							//automatically append 0 in front of decimal so long as decimal wasnt already inputted and not operand
   							if(!derror)
   								{
   									textArea.setText(textArea.getText() + "0" + buttonText);    	
   								}
   						}
   						else
   							{
   								textArea.setText(textArea.getText() + buttonText);   
   							}
							decimal = true;
   						}
   					else{
	     				//sets textarea to reflect new value for digits
    	 				textArea.setText(textArea.getText() + buttonText);   			
   						}   				
   				}
			}
		//listener for different operands
		class operands implements ActionListener
		{
			//@override
			public void actionPerformed(ActionEvent event)
			{	
				Object obj = event.getSource();
   				JButton but = null;
   				String buttonText = "";
   				//casts button with object
				if(obj instanceof JButton)
				{
     				but = (JButton) obj;
     			}
     			//sets string of text to button text
		    		if(but != null)
		    	{
     				buttonText = but.getText();
     			}
				//error for illegal start, must start with number or decimal
				if(textArea.getText().length() < 1)
						{
							textArea.setText("Illegal start please clear all and try again");
							starterror = true;
						}
				/**begin calculation cases, ran into issues with multiple numbers once number became negative
				since doubles are split by operands, and negative is denoted by the operand -.
				exception would occur potentiall, will work so long as numbers are positive
				*/
				if(textArea.getText().length() > 0)
				{
     				//store s with ignored error messages
					String s = textArea.getText().replaceAll("[a-zA-Z\\s\\n,]", "");
					//store numbers after each operand, filters out special characters so numbers can be pulled
					String[] parts = s.split("\\=|\\-|\\/|\\*|\\+|\\~");
					//stores a character to check for last character for matching
					char c = s.charAt(s.length()-1);
					//first operand case
					if(!calc)
					{
						if(newcalc)
							//for multiple calcuations once equal is pressed
						{

							d1 = Double.parseDouble(parts[counter]);
							newcalc = false;
							counter++;

						}
						//updates function to perform calculation on next operand, used to first calculation
						d1 = Double.parseDouble(parts[counter]);
						result = d1;
						calc = true;
						equal = false;
						if(buttonText == "+")
						{
							add = true;
							decimal = false;
							textArea.setText(textArea.getText() + buttonText + "\n");
						}
						if(buttonText == "-")
						{
							sub = true;
							decimal = false;
							textArea.setText(textArea.getText() + buttonText + "\n");
						}
						if(buttonText == "*")
						{
							mult = true;
							decimal = false;
							textArea.setText(textArea.getText() + buttonText + "\n");
						}
						if(buttonText == "/")
						{
							div = true;
							decimal = false;
							textArea.setText(textArea.getText() + buttonText + "\n");
						}
						if(buttonText == "=")
						{
							calc = true;
							decimal = false;
							textArea.setText(textArea.getText() + buttonText + "\n");
						}

					}
					//error check if multiple operands
					else if(c == '=' || c =='+' || c == '-' || c == '/' || c == '*'  || c == '.')
						//returns error message into text box and coninute calculations
						{		
								/* ran into empty string exception when attempting to print the previous operand,
								the code was:
								textArea.setText(textArea.getText() + buttontext);
								textArea.setText(textArea.getText().substring(0, textArea.getText().length()-1));
								where it would delete the inputted text, leaving behind the previous operand.
								Doing so broke the code, so I left it with missing operand
								*/
								textArea.setText(textArea.getText().substring(0, textArea.getText().length()-1));
								textArea.setText(textArea.getText().substring(0, textArea.getText().length()-1) + opererror + "\n");
								textArea.setText(textArea.getText() + s + "\n");
								oerror = true;
								equal = true;
						}
						else
						{
							if(buttonText == "+")
							{
								//performs calculation and returns result for each type of operator on press
								if(add)
								{
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									result = d1+d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "\n");
									d1 = result;
									counter++;
									equal = false;
									decimal = false;
								}
								if(sub)
								{	
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									result = d1-d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "\n");
									d1 = result;
									counter++;
									sub = false;
									equal = false;
									decimal = false;
								}
								if(mult)
								{
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									result = d1*d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "\n");
									d1 = result;
									counter++;
									mult = false;
									equal = false;
									decimal = false;
								}
								if(div)
								{
									//checks for divide by 0 
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									if(d1==0||d2==0)
									{
										textArea.setText(textArea.getText() + zeroerror);
									}
									else
									{
									result = d1/d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "\n");
									d1 = result;
									counter++;
									div = false;
									equal = false;
									decimal = false;
									}
								}
								if (equal)
								{
									textArea.setText(textArea.getText() + buttonText + "\n");
									equal = false;
									
									decimal = false;
								}
								add = true;
							}
							if(buttonText == "-")
							{
							//performs calculation and returns result for each type of operator on press

								if(add)
								{
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									result = d1+d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "\n");
									d1 = result;
									counter++;
									add = false;
									equal = false;
									decimal = false;
								}
								if(sub)
								{
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									result = d1-d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "\n");
									d1 = result;
									counter++;
									sub = false;
									equal = false;
									decimal = false;
								}
								if(mult)
								{
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									result = d1*d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "\n");
									d1 = result;
									counter++;
									mult = false;
									equal = false;
									decimal = false;
								}
								if(div)
								{
									//checks for divide by 0 
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									if(d1==0||d2==0)
									{
										
										textArea.setText(textArea.getText() + zeroerror);
									}
									else
									{
									result = d1/d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "\n");
									d1 = result;
									counter++;
									div = false;
									equal = false;
									decimal = false;
									}
								}
								if (equal)
								{
									textArea.setText(textArea.getText() + buttonText + "\n");
									equal = false;								
									sub = true;
									decimal = false;
								}
								sub = true;
							}
							if(buttonText =="/")
							{

								//performs calculation and returns result for each type of operator on press
								if(add)
								{
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									result = d1+d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "\n");
									d1 = result;
									counter++;
									add = false;
									equal = false;
									decimal = false;
								}
								if(sub)
								{
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									result = d1-d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "\n");
									d1 = result;
									counter++;
									sub = false;
									equal = false;
									decimal = false;
								}
								if(mult)
								{
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									result = d1*d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "\n");
									d1 = result;
									counter++;
									mult = false;
									equal = false;
									decimal = false;
								}
								if(div)
								{
									//checks for divide by 0 
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									if(d1==0||d2==0)
									{
										
										textArea.setText(textArea.getText() + zeroerror);
									}
									else
									{
									result = d1/d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "\n");
									d1 = result;
									counter++;
									div = false;
									equal = false;
									decimal = false;
									}
								}
								if (equal)
								{
									textArea.setText(textArea.getText() + buttonText + "\n");
									equal = false;									
									div = true;
									equal = false;
									decimal = false;
								}
								div = true;
							}
							if(buttonText =="*")
							{
									//performs calculation and returns result for each type of operator on press
								if(add)
								{
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									result = d1+d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "\n");
									d1 = result;
									counter++;
									add = false;
									equal = false;
								}
								if(sub)
								{
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									result = d1-d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "\n");
									d1 = result;
									counter++;
									sub = false;
									equal = false;
								}
								if(mult)
								{
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									result = d1*d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "\n");
									d1 = result;
									counter++;
									mult = false;
									equal = false;
								}
								if(div)
								{
									//checks for divide by 0 
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									if(d1==0||d2==0)
									{
										
										textArea.setText(textArea.getText() + zeroerror);
									}
									else
									{
									result = d1/d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "\n");
									d1 = result;
									counter++;
									div = false;
									equal = false;
									decimal = false;
									}
								}
								if (equal)
								{
									textArea.setText(textArea.getText() + buttonText + "\n");
									equal = false;
									mult = true;
								}
								mult = true;
							}
							if(buttonText =="=")
								{
									//sets equal to true for use in operations and clear
									equal = true;
									//performs calculation and returns result for each type of operator on press
									textArea.setText(textArea.getText() + "=" + "\n");
								if(add)
								{
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									result = d1+d2;
									textArea.setText(textArea.getText() + result + "~\n");
									d1 = result;
									counter++;
									add = false;
									newcalc = true;
								}
								if(sub)
								{
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									result = d1-d2;
									textArea.setText(textArea.getText() + result + "~\n");
									d1 = result;
									counter++;
									sub = false;
									newcalc = true;
								}
								if(mult)
								{
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									result = d1*d2;
									textArea.setText(textArea.getText() + result + "~\n");
									d1 = result;
									counter++;
									mult = false;
									newcalc = true;
								}
								if(div)
								{
									//checks for divide by 0 
									counter ++;
									d2 = Double.parseDouble(parts[counter]);
									if(d1==0||d2==0)
									{
										
										textArea.setText(textArea.getText() + zeroerror);
									}
									else
									{
									result = d1/d2;
									textArea.setText(textArea.getText() + "=\n" + result + buttonText + "~\n");
									d1 = result;
									counter++;
									div = false;
									equal = false;
									decimal = false;
									newcalc = true;
									}
								}
								
							}

						}
				}
			}
		}
	}