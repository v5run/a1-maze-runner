package main.java.ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import src.main.java.ca.mcmaster.se2aa4.mazerunner.*;

public class Maze {
    

    public static void create(String filename) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    logger.info("WALL ");
                } else if (line.charAt(idx) == ' ') {
                    logger.info("PASS ");
                }
            }
            logger.info(System.lineSeparator());
        }
    }
}
