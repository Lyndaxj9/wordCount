package wordCount.visitors;

import wordCount.dsForStrings.AVLTree;
import wordCount.util.Logger;
import wordCount.util.FileProcessor;

public class PopulateVisitor implements VisitorInterface {
    private static Logger log = Logger.getInstance();
    private FileProcessor fileProc;

    public PopulateVisitor(String inputFile){
        log.writeMessage("CONSTRUCTOR: PopulateVisitor() called.", Logger.DebugLevel.CONSTRUCTOR);
        fileProc = new FileProcessor(inputFile);
        fileProc.openRead();
    }

    public void visit(AVLTree aTree) {
        String[] aLine = fileProc.readline();
        while(aLine != null) {
            for(int i = 0; i < aLine.length; i++) {
                aTree.insert(aLine[i]);
                //log.writeMessage("DEBUG: insert "+aLine[i]+" i = "+i, Logger.DebugLevel.DEBUG);
            }
            aLine = fileProc.readline();
        }
        
        fileProc.closeRead();
    }
}
