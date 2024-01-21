package src.main.java.ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.sun.net.httpserver.HttpsConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import src.main.java.ca.mcmaster.se2aa4.mazerunner.*;

public class Main {
    private static final java.util.logging.Logger logger = LogManager.getLogger();
    // make objects for maze, path classes

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        if (("-i").equals(args[0]) ||("--input").equals(args[0])){
            logger.info("**** Reading the maze from file " + args[1]);
            // send into maze reader to see if filename is good, else throw exception
            try{
                Maze.create(args[1]);
            } catch (IOException e){
                logger.info("Innapropriate File name for Maze");
                System.exit(0);
            }
        }

        // if p arguments
        try{
            if (("-p").equals(args[2])){
                String user_path = Path.Canonical(args[3]); //implement class to tke string and turn into canonical form
                boolean status = Path.checkpath; // boolean to see if path exists on given map.
            }
            else{
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch (ArrayIndexOutOfBoundsException e){
            logger.info("/!\\No -p arguments given/!\\");
        }

        // if no p arguments
        try{
            if (!(("-p").equals(args[2]))){
                logger.info("**** Computing path");
                String correct_path = Path.Canonical(args[2]);

                Path.compute(correct_path); // compute the path on maze
                Path.CtoF(correct_path); //print factorized form of canonical
            }
            else{
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch (Exception e){
            logger.info("PATH NOT COMPUTED");;
        }
        logger.info("** End of MazeRunner");
    }
}
