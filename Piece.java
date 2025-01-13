import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.lang.*;

public abstract class Piece {
    private int pos; // The position of the piece
    private boolean isBlue; // The color of the piece
    private ImageIcon icon; // The icon of the piece
    private String pieceType; // The type of the piece
    private ArrayList<Integer> valid = new ArrayList<Integer>(); // List of valid positions

    // Constructor
    public Piece(int pos, boolean isBlue) {
        this.pos = pos;
        this.isBlue = isBlue;
    }

    // Sets the icon of the piece
    public void setIcon(String path) {
        this.icon = addIcon(path);
    }

    // Sets the position of the piece
    public void setPos(int num) {
        this.pos = num;
    }

    // Sets the type of the piece
    public void setType(String type) {
        pieceType = type;
    }

    // Gets the position of the piece
    public int getPos() {
        return pos;
    }

    // Gets the color of the piece
    public boolean getBlue() {
        return isBlue;
    }

    // Gets the type of the piece
    public String getType() {
        return pieceType;
    }

    // Gets the icon of the piece
    public ImageIcon getIcon() {
        return icon;
    }

    // Gets the valid positions of the piece
    public ArrayList<Integer> getValid() {
        return valid;
    }

    // Update valid positions of the piece
    public abstract void updateValid(Piece[] p);

    // Checks valid positions
    public boolean checkValid(int num) {
        for (int i : valid) {
            if (i == num) return true;
        }
        return false;
    }

    // Checks Enemy Piece
    public boolean checkEnemy(Piece p) {
        return p.getBlue() == !isBlue;
    }

    // Converts a 2D array coordinates to single integer position
    public int posToNum(int[] pos) {
        return (pos[0] * 5 + pos[1]);
    }

    // Converts a single integer position to 2D array coordinates
    public int[] numToPos(int num) {
        int[] pos = new int[2];
        pos[0] = num / 5;
        pos[1] = num % 5;
        return pos;
    }

    // Resize image to icon
    public static ImageIcon addIcon(String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(62, 62, java.awt.Image.SCALE_SMOOTH); // Scale the image smoothly
        return new ImageIcon(newimg);
    }
}
