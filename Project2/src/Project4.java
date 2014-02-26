import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;

import javax.swing.JLabel;

/* http://www.java2s.com/Code/JavaAPI/javax.swing/JTextFieldaddKeyListenerKeyListenerl.htm
 * http://www.java2s.com/Tutorial/Java/0240__Swing/AddkeylistenereventhandlertoJTextField.htm
 * */


public class Project4 extends JFrame {
	public Project4() throws HeadlessException {
	    setSize(200, 200);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new FlowLayout(FlowLayout.LEFT));

		
        JLabel usernameLabel = new JLabel("Username: ");
        JTextField usernameTextField = new JTextField();
        usernameTextField.setPreferredSize(new Dimension(100, 20));
        add(usernameLabel);
        add(usernameTextField);
        
        final JButton button = new javax.swing.JButton("DoSomething");  
        button.setEnabled(false);  
        
        usernameTextField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
              JTextField textField = (JTextField) e.getSource();
              String text = textField.getText();
              textField.setText(text.toUpperCase());
              if (textField.getDocument().getLength() > 0) {  
                  button.setEnabled(true);  
              } else {  
                  button.setEnabled(false);  
              }
            }

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }
          });
	}
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new Project4().setVisible(true);
            }
		});
	}
	
	
}
