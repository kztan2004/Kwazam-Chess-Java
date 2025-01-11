import java.util.ArrayList;

public class Model {
    private Piece[] pieces = new Piece[40];
    private int pieceNum;
    private boolean selected= false;
    private boolean turnBlue;
    private boolean gameOver= false;
    private int turn = 0;
    private File file;

    public Model() {
        file = new File();
    }
    
    public void init(boolean loadStatus){
        if(loadStatus){
            turnBlue = file.loadFile(pieces);
        }else{
            turnBlue = true;
            newGame();
        }
    }

    private void newGame() {
        pieces[0] = new TOR(0, false);
        pieces[1] = new BIZ(1, false);
        pieces[2] = new SAU(2, false);
        pieces[3] = new BIZ(3, false);
        pieces[4] = new XOR(4, false);
        for(int i = 5; i < 10; i++){
            pieces[i] = new RAM(i,false);
        }
        pieces[35] = new TOR(35, true);
        pieces[36] = new BIZ(36, true);
        pieces[37] = new SAU(37, true);
        pieces[38] = new BIZ(38, true);
        pieces[39] = new XOR(39, true);
        for(int j = 30; j < 35; j++){
            pieces[j] = new RAM(j,true);
        }
    }

    private void transform(){
        if(turn == 2){
            turn = 0;
            for(Piece p : pieces){
                if(p != null){
                    if(p.getType() == "TOR"){
                        Piece newP = new XOR(p.getPos(),p.getBlue());
                        pieces[newP.getPos()] = newP;
                    }else if(p.getType() == "XOR"){
                        Piece newP = new TOR(p.getPos(),p.getBlue());
                        pieces[newP.getPos()] = newP;
                    } 
                }
            }
        }
    }

    public Piece[] getPieces() {
        return pieces;
    }

    public boolean getTurnBlue() {
        return turnBlue;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void selectPiece(int num) {
        if(selected){
            if(pieces[pieceNum].checkValid(num)){
                pieces[pieceNum].setPos(num);
                if(pieces[num] != null && pieces[num].getType()=="SAU"){
                    gameOver = true;
                }
                pieces[num] = pieces[pieceNum];
                pieces[pieceNum] = null;
                turnBlue = !turnBlue;
                turn++;
                transform();
                pieces[num].getValid().clear();
            }
        }
        this.pieceNum = num;
        if(pieces[pieceNum] != null && pieces[pieceNum].getBlue() == turnBlue){
            selected = true;
            pieces[pieceNum].updateValid(pieces);
        }else{
            selected = false;
        }
    }

    public ArrayList<Integer> getValid(int buttonNum){
        if(pieces[buttonNum] == null){
            return null;
        }else{
            return pieces[buttonNum].getValid();
        }
    }
    
    public void saveFile(){
        file.saveFile(pieces, turnBlue);
    }
}