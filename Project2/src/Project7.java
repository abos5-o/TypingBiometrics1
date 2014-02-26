//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
/*
 * http://stackoverflow.com/questions/21319437/how-can-i-add-a-key-listener-to-activate-after-an-event
 */

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.util.Arrays;
import java.awt.EventQueue;


public class Project7 {

//Note: Typically the main method will be in a
//separate class. As this is a simple one class
//example it's all in the one class.
public static void main(String[] args) {

     //Use the event dispatch thread for Swing components
     EventQueue.invokeLater(new Runnable()
     {

        @Override
         public void run()
         {

             createGuiFrame();          
         }
     });

}

//handles the creation of the JFrame and
//all it's components
private static void createGuiFrame()
{

    JFrame guiFrame = new JFrame();
    //make sure the program exits when the frame closes
    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    guiFrame.setTitle("Dialog Box Example");
    guiFrame.setSize(500,300);

    //This will center the JFrame in the middle of the screen
    guiFrame.setLocationRelativeTo(null);
    guiFrame.setVisible(true);

    //Using a JTextArea to diplay feedback
    //(i.e., the username and password entered
    JTextArea textArea = new JTextArea("Password Tracker:");
    guiFrame.add(textArea);

    //Using a JPanel as the message for the JOptionPane
    JPanel userPanel = new JPanel();
    userPanel.setLayout(new GridLayout(2,2));

    JLabel usernameLbl = new JLabel("Username:");
    JLabel passwordLbl = new JLabel("Password:");
    JTextField username = new JTextField();
    JPasswordField passwordFld = new JPasswordField();

    userPanel.add(usernameLbl);
    userPanel.add(username);
    userPanel.add(passwordLbl);
    userPanel.add(passwordFld);

    //As the JOptionPane accepts an object as the message
    //it allows us to use any component we like - in this case 
    //a JPanel containing the dialog components we want
    int input = JOptionPane.showConfirmDialog(guiFrame, userPanel, "Enter your password:"
                        ,JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    char[] correctPassword = {'p', 'a', 's', 's', 'w', 'o','r','d'};
    if (input == 0) //OK Button = 0
    {
        textArea.append("\nUsername entered was: " + username.getText());

        //Retrieve password
        char[] enteredPassword = passwordFld.getPassword();
        textArea.append("\nPassword entered was: " + String.valueOf(enteredPassword));


        if (Arrays.equals(correctPassword, enteredPassword))
        {
            textArea.append("\nThe password entered is correct!");
            textArea.append("\nWould you like to continue?");
        }
        else
        {
            textArea.append("\nCall security - it's an imposter!");
        }



        //Note: best practice is to zero out the array
        //that contains the password. (Bit silly here
        //as I'm printing the password to show how it's retrived
        //but keep it in mind when using JPasswordFields.)
        Arrays.fill(enteredPassword, '0');
    }
    else
    {
        //either the cancel button or the 'x' 
        //has been pressed
        textArea.append("\nDialog cancelled..");
    }

}

}