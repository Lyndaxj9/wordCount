package wordCount.visitors;

import wordCount.dsForStrings.AVLTree;
import wordCount.dsForStrings.AVLNode;
import wordCount.util.Logger;
import wordCount.util.FileProcessor;

public class UpdateVisitor implements VisitorInterface {
    private static Logger log = Logger.getInstance();
    private FileProcessor fileProc;

    private int multiplier;
    private String outputOriginal = "original.txt";
    private String outputBackup = "backup.txt";


    public UpdateVisitor(int multiplerIN){
        log.writeMessage("CONSTRUCTOR: UpdateVisitor() called.", Logger.DebugLevel.CONSTRUCTOR);
        multiplier = multiplerIN;
    }

    public void visit(AVLTree aTree) {
        log.writeMessage("VISIT: visit() called.", Logger.DebugLevel.VISIT);
        AVLNode rO = aTree.getRoot();
        preorderUpdate(rO);

        fileProc = new FileProcessor(outputOriginal);
        fileProc.openWrite();
        inorderWrite(rO);
        fileProc.closeWrite();

        AVLNode rC = rO.getObserver(0);
        fileProc = new FileProcessor(outputBackup);
        fileProc.openWrite();
        inorderWrite(rC);
        fileProc.closeWrite();
    }

    private void preorderUpdate(AVLNode r) {
        if (r != null) {
            r.multiCount(multiplier);
            preorderUpdate(r.getLeft());
            preorderUpdate(r.getRight());
        }
    }

    private void inorderWrite(AVLNode r) {
        if (r != null) {
            inorderWrite(r.getLeft());
            fileProc.writeFile(r.getData() + "  "+ r.getCount());
            fileProc.writeNewline();
            inorderWrite(r.getRight());
        }
    }

}
