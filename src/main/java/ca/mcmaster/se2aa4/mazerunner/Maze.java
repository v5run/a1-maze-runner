package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
//import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class Maze {

    // storing entry and exit points
    public int[] e_east = new int[2];
    public int[] e_west = new int[2];
    public String[][] string_matrix;
    
    public void create(String filename) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    //logger.info("WALL ");
                } else if (line.charAt(idx) == ' ') {
                    //logger.info("PASS ");
                }
            }
            //logger.info(System.lineSeparator());
        }
    }
    public void matrix(String filename) throws IOException{
        
        String[][] maze = new String[10000][10000];
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int i=0, j=0;
        while (((line = reader.readLine()) != null)&&(i<10000)) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    maze[i][j]="WALL";
                } else if (line.charAt(idx) == ' ') {
                    maze[i][j]="PASS";
                }
                // if j==0 or j==len of line and  maze[i][j] == "PASS", then set as entry or exit (entry first)
                if ((j==0) && (maze[i][j] == "PASS")){
                    e_east[0] = i;
                    e_east[1] = j;
                }
                if ((j==(line.length()-1)) && (maze[i][j] == "PASS")){
                    e_west[0] = i;
                    e_west[1] = j;
                }

                j++;
            }
            i++;
        }
        string_matrix = maze;
    }

    public int[] getEEast() {
        return e_east;
    }

    public int[] getEWest() {
        return e_west;
    }
    
    //public void entry_exit(){} //finds entry and exit points in maze
}
