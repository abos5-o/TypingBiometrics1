import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Project3 extends JFrame implements ActionListener{
    
    static JTextField textField;
	static JTextArea textArea;
	String newline = "\n";
	
	public Project3() {
		createGUI();
	}
	
	public void createGUI(){
        /** Creating a new JFrame and configuring it. */
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Password Hardening Based on Keystroke Dynamics");
        f.setSize(700, 500);
        f.setLocation(new Point(100, 200));
        
        /** Initializing a new JTextArea and configuring it. */
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setCaretPosition(textArea.getDocument()
                .getLength());        
       
        /** Initializing a new JTextField and configuring it. */
        textField = new JTextField();
        textField.addActionListener(this);
        
        f.add(textArea, BorderLayout.CENTER);
        f.add(textField, BorderLayout.SOUTH);
        f.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent evt) {
		String text = textField.getText();
		textArea.append(text + newline);
		textField.selectAll();
	}
	
	public static void main(String[] args) {
		Project3 project3 = new Project3();
		project3.addWindowListener(new WindowAdapter() {
            
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		initialisation();
	}

	public static void initialisation() {
		textArea.append("Welcome to the Typing Biometric Authentication System\n");
		textArea.append("Please type your password in the text area at the bottom of the window\n");
		textArea.append("you will need to type your password atlease 5 times\n");

	}
	
	
}
