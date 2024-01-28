package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;

import java.io.IOException;
import com.sun.net.httpserver.HttpsConfigurator;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import java.util.logging.ConsoleHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        int argP = 1;
        Maze maze = new Maze();
        
        logger.info("** Starting Maze Runner");
        try{
            Configuration config = configure(args);
            String inputfile = config.getInputFile();
            String user_path = config.getUserPath();

            String[] p_args = config.getPArgs();
            System.out.println(p_args[1]);

            logger.info("**** Reading the maze from file:  " + inputfile);
            logger.info("Maze: \n");
            //maze.create(inputfile);
            maze.matrix(inputfile);

            if (config.hasP()){ // account for spaces in args
                logger.info("**** Found -p tag!");
                
                Path path = new Path(user_path, maze);
                path.canonical();
                path.status();
            }
            else{
                logger.info("**** NO -p ARGUMENT: Computing path for given Maze ****");
                Path alg = new Path("none", maze);
                logger.info(alg.compute()); // compute the path on maze
                System.out.println("Path: " + PathString.toFactorized(alg.compute()));
            }
        } catch (Exception e){
            logger.error("**** Suspected: Innapropriate File name for Maze or missing -i");
            e.printStackTrace();
            System.exit(0);
        }
        logger.info("** End of MazeRunner **");
    }

    private static class Configuration {
        private final String inputFile;
        private final String user_path;
        private final boolean P;
        private final String[] p_args;


        public Configuration(String inputFile, String user_path, boolean P, String[] p_args) {
            this.inputFile = inputFile;
            this.user_path = user_path;
            this.P = P;
            this.p_args = p_args;
        }
        public String getInputFile() {
            return inputFile;
        }
        public String getUserPath() {
            return user_path;
        }
        public boolean hasP() {
            return P;
        }
        public String[] getPArgs() {
            return p_args;
        }
    }

    private static Configuration configure(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("i", "input", true, "Input File path");
        options.addOption("p", "path", true, "Input Path for Check");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String inputFile = cmd.getOptionValue("i");
        String user_path = cmd.getOptionValue("p");

        String[] p_args = cmd.getArgs();
        return new Configuration(inputFile, user_path, cmd.hasOption("p"), p_args);
    }

}