public class TOR extends Piece {
    
    public TOR(int pos, boolean isBlue){
        super(pos, isBlue);
        setType("TOR");
        if(isBlue){
            setIcon("./img/blueTOR.png");
        }else{
            setIcon("./img/redTOR.png");
        }
    }
    
    @Override
    public void updateValid(Piece[] p){
        getValid().clear();
        int validPos;
        int count = 0;
        int[] pos = new int[2];
        pos = numToPos(getPos());
        while(count < 4){
            if(count == 0){
                pos[0] += 1;
            }else if(count == 1){
                pos[0] -= 1;
            }else if(count == 2){
                pos[1] += 1;
            }else if(count == 3){
                pos[1] -= 1;
            }else{
                break;
            }
            if(pos[0] < 0 || pos[0] > 7 || pos[1] < 0 || pos[1] > 4){
                pos = numToPos(getPos());
                count++;
                continue;
            }
            validPos = posToNum(pos);
            if (p[validPos] == null){
                getValid().add(validPos);
            }else if(checkEnemy(p[validPos])){
                getValid().add(validPos);
                pos = numToPos(getPos());
                count++;
            }else{
                pos = numToPos(getPos());
                count++;
            }
        }
    }
}
