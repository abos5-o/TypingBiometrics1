import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Project8 {

	 static JTextArea textArea = new JTextArea(); //create a new JTextArea for text output
	 static JTextField textField = new JTextField(); //create a new JTextField for text input
	 static boolean enterPress = false; //to know if enter has been pressed on not   
	 static boolean analyse = false; //this will be how the system should respond if enter key is pressed
	 static boolean firstPress = false; //this is to know if first character is being pressed or not
	 static boolean nextWord = false; //will know when to enable the nextword the user will type
	 static boolean repeatTest = false;
	 static ArrayList<Long> keyDiff = new ArrayList<Long>(); //arrayList of times between the keys pressed
	 static Long[][] keyTimes; //array of times between keys press
	 static Long[] tempArray; //array to store temporary calculation times
	 static Long[] keyTimesFinal; //array with average times between keys
	 static Long[] stdDevArray;	//array to store standard deviation of average times between characters 
	 static Long[] distance;
	 static Long start = (long) 0.0; //variable for when the time counter starts
	 static Long end = (long) 0.0; //variable for when time counter ends
	 //static Long average;
	 //static Long average2;
	 static String password = ""; //variable which the password will be saved as
	 static String temp = ""; //temporary string that is used when typing
	 static int sampleSize = 5; //this is the number of times the user will type their password
	 
	 public static void project8() {
		 
		 JFrame guiFrame = new JFrame();
		 guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 guiFrame.setTitle("Typing Biometric Authentication System");
		 guiFrame.setSize(700, 500); //this will set the size of the window that will appear
		 guiFrame.setLocationRelativeTo(null); //This will center the JFrame to the middle of the screen
		
		 
		 textArea = new JTextArea();
	     textArea.setLineWrap(true);
	     textArea.setWrapStyleWord(true);
	     textArea.setEditable(false);   
	     
	     textField = new JTextField();
	     textField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(!analyse && e.getKeyCode() == KeyEvent.VK_ENTER){
					if(!repeatTest){
						password = textField.getText();
					}	
					enterPress = true;
				}
				else if (analyse && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    end = System.nanoTime();  //if enter is pressed while in analyse mode, then turn the system timer off.      
                    firstPress = false; //if enter is pressed then this means that this would not be the first key that is pressed
                    nextWord = true; //go onto the next word
                    temp = textField.getText(); //we dont want to replace password, so replace the temp string to the new text value
                    textField.setText(""); 
				}
				//if in analyse mode and it is not the enter key that is being pressed:
				else if(analyse && e.getKeyCode() != KeyEvent.VK_ENTER) {
                    if (!firstPress) {
                        firstPress = true; 
                        start = System.nanoTime(); //start the timer for the press
                    } else {
                        end = System.nanoTime(); //end the timer
                        keyDiff.add(end - start); //find the dwell time.
                        start = System.nanoTime(); //this will be for the flight time
                    }
				}
					
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
	    	 
			});

		 
		 guiFrame.add(textArea, BorderLayout.CENTER);
		 guiFrame.add(textField, BorderLayout.SOUTH);
		 guiFrame.setVisible(true);
	 }
	 
	 
	    //this is the method that will be called to every time an input needs to be read from JTextField
	    static String getInput() {
	        String input = "";
	        enterPress = false;
	        while (true) {
	            input = textField.getText();
	            if (enterPress) {
	                enterPress = false;
	                break;
	            }
	        }
	        textField.setText("");
	        return input;
	    }
	 
	    
	 //this is the first method that will be called at the beginning to first part of the system   
	 public static void initilisation() {
		 //print out the initial statements at the beginning
		 textArea.append("\n");
		 textArea.append("Welcome to the Typing Biometric Authentication System\n");
		 textArea.append("Please type your password in the text area at the bottom of the window\n");
		 textArea.append("You will need to retype your password 5 times to gather data to anyalse your typing\n");
		 //the user will type the password
		 password = getInput(); //the method is called and the input that is returned will be the password
	     textArea.append(password); //the password that is typed will be printed in the JTextArea
	        
	 }
	 
	 
	 //this is the next method that will be called when the timings need to be collected
	 static void collectData() {
	        textArea.append("\nStarting data collection...");
	        keyTimes = new Long[sampleSize][]; //the size of the array will be the sample size
	        
	        //this is the for loop which will enable the user to type the password i times which will be the sample size
	        for (int i = 0; i < sampleSize; i++) {
	            textArea.append("\n" + (i + 1) + ". "); //this will print out the sample number which they have to start
	            getKeystrokeTimes(); //the method is called which will get the times of the keys pressed
	            
	            if (!temp.equals(password)) {
	                textArea.append(temp);
	                textArea.append("\nThe password you just entered does not match the password " +
	                                "you provided. Please enter data piece number " + (i + 1) + " again: ");
	                i = i - 1;
	            
	            }
	            else {
	            	keyTimes[i] = tempArray.clone(); //the keyTimes array values are a exact copy of the values in the temporary array
	            	textArea.append(temp); //the temporary string value is printed to the textArea
	            
	            }
	            
	        }
	        analyse = false; //once all the timing data has all been collect turn the analyse mode off. 
	        textArea.append("\nData collection complete.");
	    }
	
	 
	 
	 /*
	  * Calculates time between keystrokes when typing password and stores these times in array
	  * this is the method that is called to measure the timings 
	  */
	    static void getKeystrokeTimes() {
	        analyse = true; //this is now in analysis mode which relates to the keyListener when the keys are pressed
	        
	        while (!nextWord) {
	            temp = textField.getText();
	        }
	        
	        keyDiff.add(end - start);
	        nextWord = false;
	        tempArray = new Long[keyDiff.size()];//create an array of size of the array keyDiff
	        
	        //for all the values of 
	        for (int i = 0; i < keyDiff.size(); i++) {
	            tempArray[i] = keyDiff.get(i);
	        }
	        
	        keyDiff.clear();
	        analyse = false; //turn analysis mode off
	    }
	    
	    
	    
	    /*
	     * Calculates the average elapsed time between keystrokes when the user
	     * is entering his or her password during the data collection phase
	     */
	    static void analyseData() {
	        textArea.append("\nAnalyzing data...");
	        keyTimesFinal = new Long[keyTimes[0].length];
	        Long total = (long) 0.0;
	        
	        for (int i = 0; i < keyTimes[0].length; i++) {
	            for (int j = 0; j < keyTimes.length; j++) {
	                total += keyTimes[j][i];
	            }
	            keyTimesFinal[i] = (total/keyTimes.length);
	            total = (long) 0.0;                
	        }
	        
	        DecimalFormat df = new DecimalFormat("#0.00");
	        //for loop traverses through the whole password and k will be each letter
	        //it will print out the time between each letter
	        for (int k = 0; k < password.length(); k++) {
	            if (k == password.length() - 1) {
	                textArea.append("\nTime between " + password.substring(k) + " and " +
	                                "the Enter key: " + df.format(keyTimesFinal[k]*Math.pow(10.0, -9.0)) + " seconds");
	            } else {
	                textArea.append("\nTime between " + password.substring(k, k + 1) + " and " + 
	                                password.substring(k + 1, k + 2) + 
	                                ": " + df.format(keyTimesFinal[k]*Math.pow(10.0, -9.0)) + " seconds");
	                //calculating the system milliseconds to seconds
	            }
	        }
	        calculateStdDev();
	        //textArea.append("\nData anaylsis complete");
	    }
	 
	    
	    static void calculateStdDev() {
	        Long diffTotal = (long) 0.0;
	        
	        stdDevArray = new Long[keyTimes[0].length];
	        
	        for (int i = 0; i < keyTimes[0].length; i++) {
	            for (int j = 0; j < keyTimes.length; j++) {
	                diffTotal += ((keyTimesFinal[i] - keyTimes[j][i])*(keyTimesFinal[i] - keyTimes[j][i]));  
	            }
	            stdDevArray[i] = (long)Math.sqrt(diffTotal/keyTimes.length);
	            diffTotal = (long) 0.0;

	            System.out.println("standDev =" + stdDevArray[i]);
	        }
	    }
	    
	    static void calculateDistance() {
	    	
	    	Long average = (long) 0.0;
	   	 	
	    	distance = new Long[keyTimes[0].length];
		        //Long average2 = (long) 0.0;
		    	//average2 = new Long[keyTimes[0].length];
		        for (int i = 0; i < keyTimes[0].length; i++) {
		        	for (int j = 0; j < keyTimes.length; j++) {
		        		//average += (keyTimesFinal[i]-keyTimes[j][i]);
		        		//average += (keyTimesFinal[i] - tempArray[i]);
		        	average += ((tempArray[i]-keyTimes[j][i])/stdDevArray[i]);
		        	}
		            
		        	//average += (tempArray[i]-keyTimesFinal[i]);
		            //System.out.println("tempArray =" + tempArray[i]);
		            //System.out.println("KeyTimesFinal =" + keyTimesFinal[i]);
		           // System.out.println("stdDevArray =" + stdDevArray[i]); 
		        	distance[i] = average;
		        	//average = (long) 0.0;
		        System.out.println("distance =" + distance[i]);
		        }
	    
	    //average2 = average*1000000;
	    //System.out.println("distance =" + average2);
	    }
	    	     
		        
		        
	    static void startTest() {
	        int strikes = password.length() - (password.length() * 3 / 4);
	        textArea.setText("");
	        textArea.append("\nEnter your password to test the typing biometric authentication system: ");
	        getKeystrokeTimes();
	        textArea.append(temp);
	        calculateDistance();
	        while (!temp.equals(password)) {
	            textArea.append("\nYou have entered an incorrect password. Please try again: ");
	            getKeystrokeTimes();
	            textArea.append(temp);
	        }

	        textArea.append("\n");
	       
	        for (int i = 0; i < password.length(); i++) {
	            textArea.append("\nRange needed: " + Math.pow(10.0, -9.0)*(keyTimesFinal[i] - stdDevArray[i]) +
	                            " - " + Math.pow(10.0, -9.0)*(keyTimesFinal[i] + stdDevArray[i]) + " secs. Your time: " + 
	                            Math.pow(10, -9) * tempArray[i] + " secs");
	            if (tempArray[i]  <= (keyTimesFinal[i] + stdDevArray[i]) &&
	                tempArray[i]  >= (keyTimesFinal[i] - stdDevArray[i])) {
	                textArea.append(" Pass");
	            } else {
	                strikes--;
	                textArea.append(" Fail");
	            }
	        }
	        /*
	        for (int i = 0; i < password.length(); i++) {
	            textArea.append("\nRange needed: " + Math.pow(10.0, -9.0)*(keyTimesFinal[i] - distance[i]) +
	                            " - " + Math.pow(10.0, -9.0)*(keyTimesFinal[i] + distance[i]) + " secs. Your time: " + 
	                            Math.pow(10, -9) * tempArray[i] + " secs");
	            if (tempArray[i]  <= (keyTimesFinal[i] + distance[i]) &&
	                tempArray[i]  >= (keyTimesFinal[i] - distance[i])) {
	                textArea.append(" Pass");
	            } else {
	                strikes--;
	                textArea.append(" Fail");
	            }
	        }
	        */
	        
	        
	        
	        if (strikes >= 0) {
	            textArea.append("\n\nYou passed!\n");
	        } else {
	            textArea.append("\n\nYou failed.\n");
	        }
	    }
	    
	    static boolean continueTest() {
	        repeatTest = true;
	        String inp = "";
	        textArea.append("\nDo you want to try again (Y or N)? ");
	        inp = getInput();
	        textArea.append(inp);
	        
	        while (!inp.equalsIgnoreCase("Y") && !inp.equalsIgnoreCase("N")) {
	            textArea.append("\nPlease enter Y or N: ");
	            inp = getInput();
	            textArea.append(inp);
	        }
	        
	        repeatTest = false;
	        
	        if (inp.equalsIgnoreCase("Y")) {
	            return true;

	        }
	        return false;
	    }
	 
	 
	 public static void main(String[] args) {
		 //Use the event dispatch thread for Swing components
	     EventQueue.invokeLater(new Runnable()
	     {

	        @Override
	         public void run()
	         {

	             project8();          
	         }
	     });
	     
	     initilisation();
	     collectData();
	     analyseData();
	     startTest();
	     
	     while (continueTest()) {
	            startTest();
	        }
	        System.exit(0);
	     //System.out.println(password);
	    // System.exit(0);
	 }
	 
}
