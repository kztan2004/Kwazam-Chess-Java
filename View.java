import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.*;

public class View extends JFrame {
    private ArrayList<Integer> validMove;
    private ActionListener ButtonListener;
    private WindowAdapter windowAdapter;
    
    // Constructor
    public View() {
        super("Khazam Chess");
        setLayout(new GridLayout(8, 5));
        setSize(375, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
    // Displays the game window
    public void viewing() {
        addWindowListener(windowAdapter);
        setVisible(true);
    }
    
    // Updates the game grid
    public void updateGrid(boolean turnBlue, Piece[] pieces, boolean gameOver){
        getContentPane().removeAll(); // Clear all existing components
        JButton button;
        for (int i = 0; i < 40; i++) {
            int num; // Flip the Board
            if(turnBlue){
                num = i;
            }else{
                num = 39 - i;
            }
            
            if (pieces[num] != null) {
                button = new JButton(pieces[num].getIcon()); //Print Piece Icon
            } else {
                button = new JButton();
            }
            
            if(validMove != null){
                if(validMove.contains(num)){
                    button.setBackground(new Color(211,211,211)); //Print Valid Move
                }else{
                    button.setBackground(Color.WHITE); 
                }
            }else{
                button.setBackground(Color.WHITE);
            }
            button.setBorderPainted(true);
            int top = (i < 5) ? 4 : 2; //Balanced Border Lines
            int left = (i % 5 == 0) ? 4 : 2;
            int bottom = (i >= 35) ? 4 : 2;
            int right = ((i + 1) % 5 == 0) ? 4 : 2;
            button.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, new Color(56, 93, 138)));
            // Set button action command and listener
            button.setActionCommand(String.valueOf(num));
            button.addActionListener(ButtonListener);
            add(button);
        }
        revalidate(); // Revalidate the frame after adding components
        repaint();// Repaint the frame to show the changes
        // Show game over message if the game is over
        if(gameOver){
            if(turnBlue){
                ImageIcon icon = Piece.addIcon("./img/redRAM.png");
                JOptionPane.showMessageDialog(null,"RED WIN","GAME OVER",JOptionPane.INFORMATION_MESSAGE, icon);
            }else{
                ImageIcon icon = Piece.addIcon("./img/blueRAM.png");
                JOptionPane.showMessageDialog(null,"BLUE WIN","GAME OVER",JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }
    
    // Checks if a saved game file exists and prompts the user to load or start a new game
    boolean loadStatus(){
        if(File.getLoadStatus()){
            int choice = JOptionPane.showOptionDialog(
                    this,
                    "A Save File was found. What would you like to do?",
                    "ChessVMax",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Load Save", "New Game"},
                    "Load Save");
            if (choice == JOptionPane.YES_OPTION) {
                return true; // Load the save file
            } else if (choice == JOptionPane.NO_OPTION) {
                return false; // Start a new game
            }
        }
        return false;
    }
    
    // Sets the list of valid moves
    public void setValidMove(ArrayList<Integer> validMove){
        this.validMove = validMove;
    }
    
    // Sets the button action listener
    public void setButtonListener(ActionListener actionListener){
        ButtonListener = actionListener;
    }
    
    // Sets the window adapter for handling window events
    public void setWindowAdapter(WindowAdapter windowAdapter){
        this.windowAdapter = windowAdapter;
    }
}
