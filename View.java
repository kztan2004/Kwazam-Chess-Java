import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.*;

public class View extends JFrame {
    private ArrayList<Integer> validMove;
    private Model model;
    private boolean loadStatus;
    
    public View() {
        super("Khazam Chess");
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
                loadStatus = true;
            } else if (choice == JOptionPane.NO_OPTION) {
                loadStatus = false;
            }
        }
    }

    public void viewing() {
        setLayout(new GridLayout(8, 5));
        updateGrid();
        setSize(375, 600);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // Prevent default close

        // Add a WindowListener to handle window closing
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Save file before exiting
                int choice = JOptionPane.showConfirmDialog(
                        View.this,
                        "Do you want to save changes before exiting?",
                        "Confirm Exit",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (choice == JOptionPane.YES_OPTION) {
                    model.saveFile();
                    dispose(); // Close the frame
                } else if (choice == JOptionPane.NO_OPTION) {
                    dispose(); // Close the frame without saving
                }
            }
        });
        
    }

    public void updateGrid() {
        getContentPane().removeAll(); // Remove all components
        JButton button;
        for (int i = 0; i < 40; i++) {
            int num;
            if(model.getTurnBlue()){
                num = i;
            }else{
                num = 39 - i;
            }
            if (model.getPieces()[num] != null) {
                button = new JButton(model.getPieces()[num].getIcon());
            } else {
                button = new JButton();
            }
            if(validMove != null){
                if(validMove.contains(num)){
                    button.setBackground(new Color(211,211,211));
                }else{
                    button.setBackground(Color.WHITE); 
                }
            }else{
                button.setBackground(Color.WHITE);
            }
            button.setBorderPainted(true);
            int top = (i < 5) ? 4 : 2;
            int left = (i % 5 == 0) ? 4 : 2;
            int bottom = (i >= 35) ? 4 : 2;
            int right = ((i + 1) % 5 == 0) ? 4 : 2;
            button.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, new Color(56, 93, 138)));
            button.setActionCommand(String.valueOf(num));
            button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int buttonNum = Integer.parseInt(e.getActionCommand()); // Retrieve the action command (button number)
                        model.selectPiece(buttonNum);
                        validMove = model.getValid(buttonNum);
                        updateGrid(); // Update the grid after selecting a piece
                    }
                });
            add(button);
        }
        revalidate(); // Revalidate the frame after adding components
        repaint();// Repaint the frame to show the changes
        if(model.getGameOver()){
            if(model.getTurnBlue()){
                ImageIcon icon = Piece.addIcon("./img/redRAM.png");
                JOptionPane.showMessageDialog(null,"RED WIN","GAME OVER",JOptionPane.INFORMATION_MESSAGE, icon);
            }else{
                ImageIcon icon = Piece.addIcon("./img/blueRAM.png");
                JOptionPane.showMessageDialog(null,"BLUE WIN","GAME OVER",JOptionPane.INFORMATION_MESSAGE, icon);
            }
            model = new Model();
            updateGrid();
        }
    }
    
    public void connectModel(Model model){
        this.model = model;
        model.init(loadStatus);
    }
}
