import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Project implements Runnable{
	// Area in which the system outputs messages to.
    static JTextArea textArea = new JTextArea();
    // Field in which the user enters his or her input.
    static JTextField textField = new JTextField();
    // Boolean switch to show if the ENTER key has been pressed or not.
    static boolean enterPress = false;
	
	
		    public void run() {
	        // Create the window
	        JFrame f = new JFrame("Typing Biometric Authentication System");
	        // Sets the behavior for when the window is closed
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        //set the size of the window that will appear.
	        f.setSize(700, 500);
	        //set the location point of where the window will be on the screen when it opens
	        //f.setLocation(new Point(100, 200));
	        // Add a layout manager so that the button is not placed on top of the label
	       // f.setLayout(new FlowLayout());
	        // Add a label and a button
	        //f.add(new JLabel("Typing Biometric Authentication System"));
	        //f.add(new JButton("Next"));
	        // Arrange the components inside the window
	        //f.pack();
	        // By default, the window is not visible. Make it visible.
	        f.setVisible(true);
	        
	        
	        // Initializing a new JTextArea and configuring it. */
	        textArea = new JTextArea();
	        textArea.setLineWrap(true);
	        textArea.setWrapStyleWord(true);
	        textArea.setEditable(false);
	        
	        /** Initializing a new JTextField and configuring it. */
	        textField = new JTextField();
	        /*textField.addKeyListener(new KeyListener(){

				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
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
	        */
	        f.add(textArea, BorderLayout.CENTER);
	        f.add(textField, BorderLayout.SOUTH);
	        f.setVisible(true);
	        
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*System.out.println("Type Your Password Please");
		System.out.println("Please retype this passage:");
		System.out.println("This is an authentication system designed to increase the security of your exisiting password system. Typing the message several times will increase the accuracy of the system");
*/	        Project se = new Project();
	        // Schedules the application to be run at the correct time in the event queue.
	        SwingUtilities.invokeLater(se);
	        initialisation();
	}
	
    static void initialisation() {
        textArea.append("In order to analyze your keystroke, you will be required to " +
                        "enter you password a certain amount of times to gather data.\n");
    }
}
