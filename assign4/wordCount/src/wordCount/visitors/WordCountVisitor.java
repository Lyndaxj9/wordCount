package wordCount.visitors;

import wordCount.dsForStrings.AVLTree;
import wordCount.dsForStrings.AVLNode;
import wordCount.util.Logger;
import wordCount.util.FileProcessor;

/**
 * Visitor for going through the tree and calculating various counts
 */
public class WordCountVisitor implements VisitorInterface {
    private static Logger log = Logger.getInstance();
    private FileProcessor fileProc;

    private int totalWords = 0;
    private int numWords = 0;
    private int numChar = 0;

    public WordCountVisitor(String outputFile){
        log.writeMessage("CONSTRUCTOR: WordCountVisitor() called.", Logger.DebugLevel.CONSTRUCTOR);
        fileProc = new FileProcessor(outputFile);
    }

    public void visit(AVLTree aTree) {
        log.writeMessage("VISIT: visit() called.", Logger.DebugLevel.VISIT);
        AVLNode r = aTree.getRoot();
        preorderCount(r);
        fileProc.openWrite();
        fileProc.writeFile("Total Words: " + totalWords);
        fileProc.writeNewline();
        fileProc.writeFile("Distinct Words: " + numWords);
        fileProc.writeNewline();
        fileProc.writeFile("Characters: " + numChar);
        fileProc.closeWrite();
        log.writeMessage("DEBUG: Total Words: " + totalWords + " Distinct Words: " + numWords + "Number Characters: " + numChar, Logger.DebugLevel.DEBUG);
    }

    private void preorderCount(AVLNode r) {
        if (r != null) {
            totalWords += r.getCount();
            numWords++;
            numChar += r.getCount() * (r.getData()).length();
            preorderCount(r.getLeft());
            preorderCount(r.getRight());
        }
    }

}
