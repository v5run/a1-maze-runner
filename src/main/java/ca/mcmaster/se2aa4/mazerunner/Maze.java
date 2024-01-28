package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {

    private static final Logger logger = LogManager.getLogger();
    
    private int[] e_east = new int[2];
    private int[] e_west = new int[2];
    private String[][] string_matrix;
    
    public void create(String filename) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    System.out.print("WALL ");
                } else if (line.charAt(idx) == ' ') {
                    System.out.print("PASS ");
                }
            }
            System.out.println();
            //logger.info(System.lineSeparator());
        }
        System.out.println();
    }
    public void matrix(String filename) throws IOException{
        
        String[][] maze = new String[10000][10000];
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int i=0, lengthOfFirstRow=0;
        while (((line = reader.readLine()) != null)&&(i<100)) {
            if (i==0){
                lengthOfFirstRow = line.length();
            }
            for (int j=0; j < lengthOfFirstRow; j++) {
                
                try {
                    //System.out.println("[ " + i + ", " + j + " ]");
                    
                    if ((line.length() == 0)||(j>=line.length())){
                        maze[i][j]="PASS";
                        if (j==0){
                            e_west[0] = i;
                            e_west[1] = j;
                        }
                        if (j==(lengthOfFirstRow-1)){
                            e_east[0] = i;
                            e_east[1] = j;
                        }
                    }
                    else if (line.charAt(j) == ' ') {
                        maze[i][j]="PASS";
                        if (j==0){
                            e_west[0] = i;
                            e_west[1] = j;
                        }
                        if (j==(line.length()-1)){
                            e_east[0] = i;
                            e_east[1] = j;
                        }
                    }
                    else if (line.charAt(j) == '#') {
                        maze[i][j]="WALL";
                    }
                        
                } catch (StringIndexOutOfBoundsException e) {
                    maze[i][j]="PASS";
                }
                
            }
            i+=1;
        }
        string_matrix = maze;

        logger.info("Entry/Exit Points: ");
        logger.info( "   West End: [" + Integer.toString(getEWest()[0]) + ", " + Integer.toString(getEWest()[1]) + "]");
        logger.info( "   East End: [" + Integer.toString(getEEast()[0]) + ", " + Integer.toString(getEEast()[1]) + "]");
        logger.info("\n");
    }

    public int[] getEEast() {
        return e_east;
    }

    public int[] getEWest() {
        return e_west;
    }

    public String[][] getMaze(){
        return string_matrix;
    }
    
    //public void entry_exit(){} //finds entry and exit points in maze
}
