package ca.mcmaster.se2aa4.mazerunner;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathString {

    private static final Logger logger = LogManager.getLogger();
    private String path;

    public PathString(String path) {
        this.path = path;
    }

    public String[] canonical_list(){

        char[] c_list = path.toCharArray();
        // create a string
        StringBuilder new_string = new StringBuilder();

        for (int i=0; i<(c_list.length);i++){
            if (Character.isDigit(c_list[i])){
                char num = c_list[i];
                char next_char = c_list[i+1];
                for (int j=0; j<(num - '0'); j++){
                    new_string.append(Character.toUpperCase(next_char));
                }
                i+=1;
            }
            else if ((c_list[i] == 'F') || (c_list[i] == 'f') || (c_list[i] == 'R') || (c_list[i] == 'r') || (c_list[i] == 'L') || (c_list[i] == 'l')){
                new_string.append(Character.toUpperCase(c_list[i]));
            }
        }
        logger.info("CANONICAL PATH: " + new_string);
        //System.out.println();
        String[] new_list = new_string.toString().split("");
        return new_list;
    }
    public static String toFactorized(String c_path){
        
        String[] c_list = c_path.split("");
        StringBuilder new_string = new StringBuilder();
        int j, i;

        for (i=0; i<c_list.length - 1; i++){

            if (!(c_list[i].equals(c_list[i+1]))){new_string.append(c_list[i]); new_string.append(" ");}
            else{
                int count = 0;
                for (j=i; j<c_list.length; j++){
                    if ((c_list[i]).equals(c_list[j])){
                        count+=1;
                    }
                    else{
                        break;
                    }
                }
                i=j-1;
                new_string.append(String.valueOf(count));
                new_string.append(c_list[i]);
                new_string.append(" ");
            }
        }
        
        if (c_list.length > 0 && i < c_list.length - 1 && !c_list[i].equals(String.valueOf(new_string.charAt(new_string.length() - 1)))) {
            new_string.append(c_list[c_list.length - 1]);
        }
        
        return new_string.toString();
    }


}
