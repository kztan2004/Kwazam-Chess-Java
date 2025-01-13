import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.*;

public class Controller {
    private Model model;
    private View view;
    
    // Constructor
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        this.model.init(this.view.loadStatus());
        this.view.setButtonListener(new ButtonListener());
        this.view.setWindowAdapter(new ClosingListener());
    }
    
    // Updates the view
    public void updateView(){
        updateGrid();
        this.view.viewing();
    }
    
     // Updates the grid
    private void updateGrid(){
        view.updateGrid(model.getTurnBlue(), model.getPieces(), model.getGameOver());
    }
    
    // Listener for button clicks
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int buttonNum = Integer.parseInt(e.getActionCommand());
            model.selectPiece(buttonNum);
            view.setValidMove(model.getValid(buttonNum));
            updateGrid();
            if(model.getGameOver()){
                model = new Model();
                model.init(false);
                updateGrid();
            }
        }
    }
    
    // Listener for window closing events
    class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            // Save file before exiting
            int choice = JOptionPane.showConfirmDialog(
                    view,
                    "Do you want to save changes before exiting?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (choice == JOptionPane.YES_OPTION) {
                model.saveFile();
                view.dispose(); // Close the frame
            } else if (choice == JOptionPane.NO_OPTION) {
                view.dispose(); // Close the frame without saving
            }
        }
    }
}