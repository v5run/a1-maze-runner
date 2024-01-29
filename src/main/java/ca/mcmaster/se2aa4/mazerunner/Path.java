package ca.mcmaster.se2aa4.mazerunner;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Path {

    private static final Logger logger = LogManager.getLogger();

    private String user_path;
    private String[] can_path;
    private Maze maze;
    private boolean status_east=false, status_west=false;
    private String direction = "EAST";


    public Path(String path, Maze maze){

        this.maze = maze;
        this.user_path = path;
    }
    public void check(){
        canonical();
        isPathValid();
    }

    private void canonical() {
        PathString pathlist = new PathString(user_path);
        can_path = pathlist.canonical_list();
    }

    public String compute() {
        RightHandAlg alg = new RightHandAlg(maze);
        return alg.compute();
    }

    private void isPathValid() {

        try{
            logger.info("**** Checking path: East -> West");
            PathCheck checker1 = new PathCheck(maze.getMaze(), can_path, "WEST", maze.getEEast(), maze.getEWest());
            status_east = checker1.status();

        } catch(IndexOutOfBoundsException e){
            System.out.println("Path failed: East to West");
        }

        try{
            logger.info("**** Checking path: West -> East");
            PathCheck checker2 = new PathCheck(maze.getMaze(), can_path, "EAST", maze.getEWest(), maze.getEEast());            
            status_west = checker2.status();

        } catch(IndexOutOfBoundsException e){
            logger.info("**** Path failed: West to East");
        }

        if( status_east || status_west ){
            System.out.println("Correct path");
        }
        else{System.out.println("Incorrect path");}

    }
    
}
