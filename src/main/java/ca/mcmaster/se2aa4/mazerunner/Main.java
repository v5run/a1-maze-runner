package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import com.sun.net.httpserver.HttpsConfigurator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    // make objects for maze, path classes

    public static void main(String[] args) {
        int argP = 1;
        Maze maze = new Maze();
        
        logger.info("** Starting Maze Runner");
        if (("-i").equals(args[0]) ||("--input").equals(args[0])){
            logger.info("**** Reading the maze from file " + args[1]);
            // send into maze reader to see if filename is good, else throw exception
            try{
                logger.info("**** Given Maze: \n");
                maze.create(args[1]);
                System.out.println();

                //store matrix as string
                maze.matrix(args[1]);
                logger.info("**** Entry/Exit Points: ");
                System.out.println( "[" + Integer.toString(maze.getEEast()[0]) + ", " + Integer.toString(maze.getEEast()[1]) + "]");
                System.out.println( "[" + Integer.toString(maze.getEWest()[0]) + ", " + Integer.toString(maze.getEWest()[1]) + "]");

            } catch (IOException e){
                logger.info("**** Innapropriate File name for Maze");
                System.exit(0);
            }
        }
        else{
            // no -i, then exit
            logger.info("/!\\No -i arguments given/!\\");
            System.exit(1);
        }

        // if p arguments
        try{
            if (("-p").equals(args[2])){ // account for spaces in args
                logger.info("**** Found -p tag!");

                Path path = new Path(args[3], maze); //send in matrix for maze too
                //String can_path = path.canonical(); //implement class to the string and turn into canonical form
                path.status(); // return statement to user stating whether path works
            }
            else{
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch (ArrayIndexOutOfBoundsException e){
            logger.info("/!\\No -p arguments given/!\\");
            argP = 0;
        }

        // if no p arguments
        try{
            if (argP == 0){
                logger.info("**** Computing path");
                Path auto_path = new Path("none", maze);
                //find the correct path b/c no path was given by the user

                Path.compute(); // compute the path on maze
                Path.CtoF(); //print factorized form of canonical
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
