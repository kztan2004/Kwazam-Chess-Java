public class RAM extends Piece {
    
    private boolean reversed = false;
    
    public RAM(int pos, boolean isBlue){
        super(pos, isBlue);
        setType("RAM");
        if(isBlue){
            setIcon("./img/blueRAM.png");
            reversed = true;
        }else{
            setIcon("./img/redRAM.png");
        }
    }
    
    @Override
    public void updateValid(Piece[] p){
        getValid().clear();
        int validPos;
        if (reversed){
            validPos = getPos() - 5;
        }else{
            validPos = getPos() + 5;
        }
        if (validPos < 0 || validPos > 39){
            reversed = !reversed;
            return;
        }
        if (p[validPos] == null || checkEnemy(p[validPos])){
            getValid().add(validPos);
        }
    }
}