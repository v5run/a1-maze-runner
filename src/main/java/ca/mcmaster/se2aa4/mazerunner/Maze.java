package main.java.ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import src.main.java.ca.mcmaster.se2aa4.mazerunner.*;

import com.sun.net.httpserver.HttpsConfigurator;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class Maze {

    public int[] entry = new int[2];
    public int[] exit = new int[2];
    
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
    public String[][] matrix(String filename) throws IOException{
        
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
                if (((j==0) || (j==(line.length()-1))) && (maze[i][j] == "PASS")){
                    if (entry.length == 0){
                        entry[0] = i;
                        entry[1] = j;
                    }
                    else{
                        exit[0] = i;
                        exit[1] = j;
                    }
                }
                j++;
            }
            i++;
        }
        return maze;
    }
    //public void entry_exit(){} //finds entry and exit points in maze
}
