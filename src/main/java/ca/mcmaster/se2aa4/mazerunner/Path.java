package ca.mcmaster.se2aa4.mazerunner;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Path {

    private static final Logger logger = LogManager.getLogger();

    public String user_path;
    private String[] can_path; //CREATE A METHOD TO TURN CMD LINE ARGUMENTS INTO CANONICAL PATH
    public Maze maze;
    private boolean status_east=false, status_west=false;
    private String direction = "EAST";

    // make another class that deals separately (UserPath, CompPath)

    //constructor
    public Path(String path, Maze maze){

        this.maze = maze; // calls the string version of the matrix
        this.user_path = path;
    }

    public void canonical() {
        PathString pathlist = new PathString(user_path);
        can_path = pathlist.canonical_list();
    }

    public static void compute() { // find a working path on the maze, maybe have different class

    }
    public void status() {

        try{
            logger.info("**** Computing path from East -> West");

            // from East -> West, go through eastern entry and end at western entry
            status_east = isPathValid("WEST", maze.getEEast(), maze.getEWest());
            
            if (status_east){logger.info("**** Path works from East to West");}
            else{logger.error("**** Path failed: East to West");}

        } catch(IndexOutOfBoundsException e){
            //logger.info("/!\\No -p arguments given/!\\");
            logger.error("**** Path failed: East to West");
        }

        try{
            logger.info("**** Computing path from West -> East");
            
            // from West -> East, go through western entry and end at eastern exit 
            status_west = isPathValid("EAST", maze.getEWest(), maze.getEEast());
            
        
            if (status_west){logger.info("**** Path works from: West to East");}
            else{logger.error("**** Path failed: West to East");}

        } catch(IndexOutOfBoundsException e){
            logger.error("**** Path failed: West to East");
        }

        // print statements if path exists or doesn't
    }
    private boolean isPathValid(String d, int[] entry, int[] exit) throws IndexOutOfBoundsException{

        direction = d;
        int[] start = entry;
        int[] end = exit;
        //              [x, y] location
        int[] current = new int[2];
        current[0] = start[0];
        current[1] = start[1];

        System.out.println( "Start: [" + Integer.toString(start[0]) + ", " + Integer.toString(start[1]) + "]");
        System.out.println( "End: [" + Integer.toString(end[0]) + ", " + Integer.toString(end[1]) + "]");

        //logger.info("**** Computing provided path");

        for (int i=0; i<can_path.length; i++){
            
            //System.out.println( "CP: [" + Integer.toString(current[0]) + ", " + Integer.toString(current[1]) + "]");

            if (direction.equals("EAST")){
                if (can_path[i].equals("F")){
                    if (("PASS").equals((maze.string_matrix)[current[0]][current[1]+1])){
                        current[1] += 1;
                    }
                    else{return false;}
                }
                else{
                    if (can_path[i].equals("R")){
                        direction = "SOUTH";
                    }
                    else if (can_path[i].equals("L")){
                        direction = "NORTH";
                    }
                }
            }
            else if (direction.equals("SOUTH")){
                if (can_path[i].equals("F")){
                    if (("PASS").equals((maze.string_matrix)[current[0]+1][current[1]])){
                        current[0] += 1;
                    }
                    else{return false;}
                }
                else{
                    if (can_path[i].equals("R")){
                        direction = "WEST";
                    }
                    else if (can_path[i].equals("L")){
                        direction = "EAST";
                    }
                }
            }
            else if (direction.equals("NORTH")){
                if (can_path[i].equals("F")){
                    if (("PASS").equals((maze.string_matrix)[current[0]-1][current[1]])){
                        current[0] -= 1;
                    }
                    else{return false;}
                }
                else{
                    if (can_path[i].equals("R")){
                        direction = "EAST";
                    }
                    else if (can_path[i].equals("L")){
                        direction = "WEST";
                    }
                }
            }
            else if (direction.equals("WEST")){
                if (can_path[i].equals("F")){
                    if (("PASS").equals((maze.string_matrix)[current[0]][current[1]-1])){
                        current[1] -= 1;
                    }
                    else{return false;}
                }
                else{
                    if (can_path[i].equals("R")){
                        direction = "NORTH";
                    }
                    else if (can_path[i].equals("L")){
                        direction = "SOUTH";
                    }
                }
            }   
        }
        
        //System.out.println( "FP: [" + Integer.toString(current[0]) + ", " + Integer.toString(current[1]) + "]");

        if ((current[0] == end[0])&&(current[1] == end[1])){
            //System.out.println("Working.");
            return true;
        }
        else{
            return false;
        }

    }
    public static void CtoF() { // maybe make separate paths 
    }

    
}
