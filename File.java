import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class File{
    // Constructor for the File class
    public File(){}
    
    //Saves the current game state to a file
    public void saveFile(Piece[] pieces, boolean turnBlue){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            writer.write(turnBlue+"\n");
            for(Piece p : pieces){
                if(p != null){
                    writer.write(p.getType()+" "+p.getPos()+" "+p.getBlue()+"\n");
                }
            }
            writer.close();
        }catch(Exception e){
            
        }
    }
    
    //Checks if a saved game file exists
    public static boolean getLoadStatus(){
        boolean result = false;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
            String lines;
            result = (lines = reader.readLine())!= null;
            reader.close();
        }catch(Exception e){
            
        }
        return result;
    }
    
    //Loads game state from the saved file
    public boolean loadFile(Piece[] pieces){
        boolean turnBlue = true;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
            String lines;
            String turnBlueRead = reader.readLine();
            turnBlue = turnBlueRead.equals("true"); // Parse the turn information
            while((lines = reader.readLine())!= null){
                String[] line = lines.split(" "); // Split the line into components
                switch(line[0]){
                    case "RAM":
                        pieces[Integer.parseInt(line[1])] = new RAM(Integer.parseInt(line[1]), line[2].equals("true"));
                        break;
                    case "XOR":
                        pieces[Integer.parseInt(line[1])] = new XOR(Integer.parseInt(line[1]), line[2].equals("true"));
                        break;
                    case "TOR":
                        pieces[Integer.parseInt(line[1])] = new TOR(Integer.parseInt(line[1]), line[2].equals("true"));
                        break;
                    case "BIZ":
                        pieces[Integer.parseInt(line[1])] = new BIZ(Integer.parseInt(line[1]), line[2].equals("true"));
                        break;
                    case "SAU":
                        pieces[Integer.parseInt(line[1])] = new SAU(Integer.parseInt(line[1]), line[2].equals("true"));
                        break;
                    default:
                        break;
                }
            }
            reader.close();
        }catch(Exception e){
            
        }
        return turnBlue;
    }
}