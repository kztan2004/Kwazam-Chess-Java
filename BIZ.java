public class BIZ extends Piece {
    
    private int[][] move = {{-1,-2},{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2}};
    
    public BIZ(int pos, boolean isBlue){
        super(pos, isBlue);
        setType("BIZ");
        if(isBlue){
            setIcon("./img/blueBIZ.png");
        }else{
            setIcon("./img/redBIZ.png");
        }
    }
    
    @Override
    public void updateValid(Piece[] p){
        getValid().clear();
        int validPos;
        int[] pos = new int[2];
        for(int i = 0; i < 8; i++){
            pos = numToPos(getPos());
            pos[0] += move[i][0];
            pos[1] += move[i][1];
            if(pos[0] < 0 || pos[0] > 7 || pos[1] < 0 || pos[1] > 4){
                continue;
            }
            validPos = posToNum(pos);
            if (p[validPos] == null || checkEnemy(p[validPos])){
                getValid().add(validPos);
            }
        }
    }
}