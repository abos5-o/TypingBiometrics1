import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Project5 {

	 static JTextArea textArea = new JTextArea();
	 static JTextField textField = new JTextField();
	    /** Boolean switch to show if the ENTER key has been pressed or not. */
	    static boolean enterPress = false;
	    /** Boolean switch to show if there is at least one uppercase character in the user's password. */
	    static boolean uppercase = false;
	    /** Boolean switch to show if there is at least one number in the user's password. */
	    static boolean number = false;
	    /** Boolean switch to control how the system responds when the ENTER key is pressed. */
	    static boolean analyze = false;
	    /** Boolean switch to show if the current character the user is entering is the first or not. */
	    static boolean firstPress = false;
	    /** Boolean switch to control when the system starts parsing the next word from the user. */
	    static boolean nextWord = false;
	    /** Boolean switch to control how the system responds when the ENTER key is pressed. */
	    static boolean repeatTest = false;
	    /** ArrayList containing the elapsed times between characters when the user is entering his 
	     * or her password for data collection. */
	    static ArrayList<Long> keyDiff = new ArrayList<Long>();
	    /** 2D array that contains all the elapsed timing data gathered from the data collection phase. */
	    static Long[][] keyTimes;
	    /** Array used to store miscalleneous, temporary timing data. */
	    static Long[] tempArray;
	    /** Array used to store the averaged elapsed times between characters when the user is entering
	     * his or her password, caclulated from the data gathered in the data collection phase. */
	    static Long[] keyTimesFinal;
	    /** Array used to store the standard deviation of the average elapsed times between characters 
	     * when the user is entering his or her password. */
	    static Long[] stdDevArray;
	    /** Variable used to mark when time capture begins. */
	    static Long start = (long) 0.0;
	    /** Variable used to mark when time capture ends. */
	    static Long end = (long) 0.0;
	    /** Variable used to store calculated standard deviation values. */
	    static Long stdDev = (long) 0.0;
	    /** Variable used to store the user's password. */
	    static String password = "";
	    /** Variable used to store miscellaneous, temproary String data. */
	    static String temp = "";
	    /** Variable that represents the number of times the user will enter his password during the
	     * data collection phase to gather biometric keystroke data. */
	    static int sampleSize = 5;
	 
	 public static void project5() {
		 
		 JFrame guiFrame = new JFrame();
		 guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 guiFrame.setTitle("Typing Biometric Authentication System");
		 guiFrame.setSize(700, 500);
		 //This will centre the JFrame to the middle of the screen
		 guiFrame.setLocationRelativeTo(null);
		 
		 
		 
		 textArea = new JTextArea();
	     textArea.setLineWrap(true);
	     textArea.setWrapStyleWord(true);
	     textArea.setEditable(false);
	     //textArea.setCaretPosition(textArea.getDocument()  .getLength());      
	     
	     textField = new JTextField();
	     textField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(!analyze && e.getKeyCode() == KeyEvent.VK_ENTER){
					password = textField.getText();
					enterPress = true;
				}
				else if (analyze && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    end = System.nanoTime();        
                    firstPress = false;
                    nextWord = true;
                    temp = textField.getText();
                    textField.setText("");
				}
				else if(analyze && e.getKeyCode() != KeyEvent.VK_ENTER && e.getKeyCode() != KeyEvent.VK_SHIFT
                           && e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    if (!firstPress) {
                        firstPress = true;
                        start = System.nanoTime();
                    } else {
                        end = System.nanoTime();
                        keyDiff.add(end - start);
                        start = System.nanoTime();
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
	 
	 
	    /** Gets user input from the JTextField. */
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
	 
	 public static void initilisation() {
		 
		 textArea.append("\n");
		 textArea.append("Welcome to the Typing Biometric Authentication System\n");
		 textArea.append("Please type your password in the text area at the bottom of the window\n");
		 textArea.append("You will need to retype your password atlease 5 times to gather data to anyalse your typing\n");
		 
		 keyTimes = new Long[sampleSize][];
		 
		 password = getInput();
	     textArea.append(password);
	        
	 }
	 
	 static void collectData() {
	        textArea.append("\nStarting data collection...");
	        for (int i = 0; i < sampleSize; i++) {
	            textArea.append("\n" + (i + 1) + ". ");
	            getKeystrokeTimes();
	            
	            if (!temp.equals(password)) {
	                textArea.append(temp);
	                textArea.append("\nThe password you just entered does not match the password " +
	                                "you provided. Please enter data piece number " + (i + 1) + " again: ");
	                i = i - 1;
	            } else {
	                keyTimes[i] = tempArray.clone();
	                textArea.append(temp);
	            }
	        }
	        analyze = false;
	        textArea.append("\nData collection complete.");
	    }
	    
	    /** Method that calcuates the elapsed time betweeen keystrokes when the user
	     * is entering his or her password, and stores that data in an array. */
	    static void getKeystrokeTimes() {
	        analyze = true;
	        
	        while (!nextWord) {
	            temp = textField.getText();
	        }
	        
	        keyDiff.add(end - start);
	        nextWord = false;
	        tempArray = new Long[keyDiff.size()];
	        
	        for (int i = 0; i < keyDiff.size(); i++) {
	            tempArray[i] = keyDiff.get(i);
	        }
	        
	        keyDiff.clear();
	        analyze = false;
	    }
	    
	    /** Calculates the average elapsed time between keystrokes when the user 
	     * is entering his or her password during the data collection phase. */
	    static void analyzeData() {
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
	        
	        for (int k = 0; k < password.length(); k++) {
	            if (k == password.length() - 1) {
	                textArea.append("\nTime between " + password.substring(k) + " and " +
	                                "the Enter key: " + df.format(keyTimesFinal[k]*Math.pow(10.0, -9.0)) + " seconds");
	            } else {
	                textArea.append("\nTime between " + password.substring(k, k + 1) + " and " + 
	                                password.substring(k + 1, k + 2) + 
	                                ": " + df.format(keyTimesFinal[k]*Math.pow(10.0, -9.0)) + " seconds");
	            }
	        }

	        textArea.append("\nData anaylsis complete");
	    }
	 
	 
	 
	 
	 public static void main(String[] args) {
		 //Use the event dispatch thread for Swing components
	     EventQueue.invokeLater(new Runnable()
	     {

	        @Override
	         public void run()
	         {

	             project5();          
	         }
	     });
	     
	     initilisation();
	     collectData();
	     analyzeData();
	     //System.out.println(password);
	    // System.exit(0);
	 }
	 
}
