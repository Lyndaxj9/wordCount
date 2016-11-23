package wordCount.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Basic file processor that can be used for the reading and writing
 * of files
 */
public class FileProcessor {
    private static Logger log = Logger.getInstance();

    private String inputFile = "";
    private String outputFile = "";

    private String line = null;
    private String delim = "[ ]+";

    private FileReader fileReader;
    private FileWriter fileWriter;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public FileProcessor(String inputIN, String outputIN) {
        log.writeMessage("CONSTRUCTOR: FileProcessor(inputIN, outputIN) called.", Logger.DebugLevel.CONSTRUCTOR);
        inputFile = inputIN;
        outputFile = outputIN;
    }

    public FileProcessor(String inputIN) {
        log.writeMessage("CONSTRUCTOR: FileProcessor(inputIN) called.", Logger.DebugLevel.CONSTRUCTOR);
        inputFile = inputIN;
        outputFile = inputIN;
    }

    public void openRead() {
        try {
            fileReader = new FileReader(inputFile);
            bufferedReader = new BufferedReader(fileReader);
        } catch(FileNotFoundException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * @return a String array containing the strings a words split up by spaces
     */
    public String[] readline() {
        try {
            if(((line = bufferedReader.readLine()) != null) && !line.isEmpty()) {
                String[] aLine = line.split(delim);            
                return aLine;
            } else if ((line != null) && line.isEmpty()){
                log.writeMessage("DEBUG: blank line detected. ", Logger.DebugLevel.DEBUG);
                line = bufferedReader.readLine();

                while((line != null) && line.isEmpty()) {
                    line = bufferedReader.readLine();
                }

                if(line != null) {
                    String[] aLine = line.split(delim);            
                    return aLine;
                }
            }   
        } catch(IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    public void closeRead() {
        try {
            bufferedReader.close();
        } catch(IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public void openWrite() {
        try {
            fileWriter = new FileWriter(outputFile);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch(IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public void writeFile(String outputStringIN) {
        try {
            bufferedWriter.write(outputStringIN);
        } catch(IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public void writeNewline() {
        try {
            bufferedWriter.newLine();
        } catch(IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public void closeWrite() {
        try {
            bufferedWriter.close();
        } catch(IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

}
