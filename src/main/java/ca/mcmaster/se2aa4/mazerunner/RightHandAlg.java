package ca.mcmaster.se2aa4.mazerunner;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandAlg {

    private static final Logger logger = LogManager.getLogger();
    private Maze maze;


    public RightHandAlg(Maze maze){
        this.maze = maze;
    }

    public String compute(){

        // current = start position, west -> east
        int[] c = new int[2];
        int[] end = new int[2];

        String[][] matrix = maze.getMaze();
        String direction = "EAST";
        c[0] = (maze.getEWest())[0];
        c[1] = (maze.getEWest())[1];
        end[0] = (maze.getEEast())[0];
        end[1] = (maze.getEEast())[1];

        StringBuilder can_path = new StringBuilder();

        while (!(c[0] == end[0] && c[1]==end[1])){

            if ("EAST".equals(direction)){
                
                if (("PASS").equals(matrix[c[0]+1][c[1]])){
                    direction="SOUTH";
                    can_path.append("R");
                    can_path.append("F");
                    c[0]+=1;
                }
                else if (("PASS").equals(matrix[c[0]][c[1] + 1])){
                    can_path.append("F");
                    c[1]+=1;
                }
                else{
                    direction="NORTH";
                    can_path.append("L");
                }
            }
            else if ("WEST".equals(direction)){
                if (("PASS").equals(matrix[c[0]-1][c[1]])){
                    direction="NORTH";
                    can_path.append("R");
                    can_path.append("F");
                    c[0]-=1;
                }
                else if (("PASS").equals(matrix[c[0]][c[1] - 1])){
                    can_path.append("F");
                    c[1]-=1;
                }
                else{
                    direction="SOUTH";
                    can_path.append("L");
                }
            }
            else if ("SOUTH".equals(direction)){
                if (("PASS").equals(matrix[c[0]][c[1]-1])){
                    direction="WEST";
                    can_path.append("R");
                    can_path.append("F");
                    c[1]-=1;
                }
                else if (("PASS").equals(matrix[c[0]+1][c[1]])){
                    can_path.append("F");
                    c[0]+=1;
                }
                else{
                    direction="EAST";
                    can_path.append("L");
                }
            }
            else if ("NORTH".equals(direction)){

                if (("PASS").equals(matrix[c[0]][c[1] + 1])){
                    direction="EAST";
                    can_path.append("R");
                    can_path.append("F");
                    c[1]+=1;
                }
                else if (("PASS").equals(matrix[c[0]-1][c[1]])){
                    can_path.append("F");
                    c[0]-=1;
                }
                else{
                    direction="WEST";
                    can_path.append("L");
                }
            }
        }
        //System.out.println(can_path.toString());
        return can_path.toString();
    }
    
}
