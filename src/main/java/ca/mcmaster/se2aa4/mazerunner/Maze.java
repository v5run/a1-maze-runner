package main.java.ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import src.main.java.ca.mcmaster.se2aa4.mazerunner.*;

import com.sun.net.httpserver.HttpsConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    
    public void create(String filename) throws IOException{
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
    public String[] matrix(String filename) throws IOException{
        
        String[] maze = new String[10000];
        return maze;
    }
}
