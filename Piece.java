import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.lang.*;

public class Piece {
    private int pos;
    private boolean isBlue;
    private ImageIcon icon;
    private String pieceType;
    private ArrayList<Integer> valid = new ArrayList<Integer>();
    
    public Piece(int pos, boolean isBlue){
        this.pos = pos;
        this.isBlue = isBlue;
    }
    
    public void setIcon(String path){
        this.icon = addIcon(path);
    }
    
    public void setPos(int num){
        this.pos = num;
    }
    
    public void setType(String type){pieceType = type;}
    
    public int getPos() {return pos;}
    
    public boolean getBlue() {return isBlue;}
    
    public String getType(){return pieceType;}
    
    public ImageIcon getIcon() {return icon;}
    
    public ArrayList<Integer> getValid(){return valid;}
    
    public void updateValid(Piece[] p) {};
    
    public boolean checkValid(int num){
        for(int i : valid){
            if(i == num) return true;
        }
        return false;
    }
    
    public boolean checkEnemy(Piece p){
        return p.getBlue() == !isBlue;
    }
    
    public int posToNum(int[] pos){
        return (pos[0] * 5 + pos[1]);
    }
    
    public int[] numToPos(int num){
        int[] pos = new int[2];
        pos[0] = num/5;
        pos[1] = num%5;
        return pos;
    }
    
    public static ImageIcon addIcon(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(62, 62,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        return new ImageIcon(newimg);
    }
}