package ca.mcmaster.se2aa4.mazerunner;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathCheck {

    private static final Logger logger = LogManager.getLogger();
    
    private String[][] maz;
    private String direction;
    private String[] can_path;
    private int[] entry;
    private int[] exit;

    public PathCheck(String[][] maze, String[] can_path, String d, int[] entry, int[] exit){

        this.maz = maze;
        this.direction = d;
        this.entry = entry;
        this.exit = exit;
        this.can_path = can_path;

    }

    public boolean status() throws IndexOutOfBoundsException{

        int[] start = entry;
        int[] end = exit;
        //              [x, y] location
        int[] current = new int[2];
        current[0] = start[0];
        current[1] = start[1];

        //System.out.println("CURRENT: " + current[0] + ", " + current[1]);
        //System.out.println("START: " + start[0] + ", " + start[1]);
        //System.out.println("END: " + end[0] + ", " + end[1]);
        
        logger.info("**** Computing provided path");

        for (int i=0; i<can_path.length; i++){
            
            if (direction.equals("EAST")){
                if (can_path[i].equals("F")){
                    if (("PASS").equals((maz)[current[0]][current[1]+1])){
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
                    if (("PASS").equals((maz)[current[0]+1][current[1]])){
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
                    if (("PASS").equals((maz)[current[0]-1][current[1]])){
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
                    if (("PASS").equals((maz)[current[0]][current[1]-1])){
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
        //System.out.println("EP: " + current[0] + ", " + current[1]);
        
        if ((current[0] == end[0])&&(current[1] == end[1])){
            return true;
        }
        else{
            return false;
        }

    }




}
