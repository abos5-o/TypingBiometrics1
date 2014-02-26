import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * http://www.ugrad.cs.ubc.ca/~cs219/CourseNotes/Swing/swing-Listeners-Key.html
 */

public class KeyListenerExample3 extends JFrame
{
    JTextField textField;
    JTextArea textArea;
    JScrollPane scrollPane;

    public KeyListenerExample3()
    {
        textField = new JTextField(30);
        textField.addKeyListener(new KeyListener()
            {
                public void keyPressed(KeyEvent e)
                {
                    information("keyPressed", e);
                }

                public void keyReleased(KeyEvent e)
                {
                    information("keyReleased", e);
                }

                public void keyTyped(KeyEvent e)
                {
                    information("keyTyped", e);
                }

                public void information(String event, KeyEvent e)
                {
                    textArea.append("\nCall made to " + event + "..." +
                                    "   \nUnicode character: " + e.getKeyChar() +
                                    "       \nKeyboard code: " + e.getKeyCode() +
                                    "       \nKeyboard text: " + e.getKeyText(e.getKeyCode()) +        
                                    "           \nModifiers: " + e.getKeyModifiersText(e.getModifiers()));                
                }
            });
        getContentPane().add(textField, BorderLayout.NORTH);

        textArea = new JTextArea(10, 30);
        scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args)
    {
        KeyListenerExample3 frame = new KeyListenerExample3();
        frame.pack();
        frame.setVisible(true);
    }
}