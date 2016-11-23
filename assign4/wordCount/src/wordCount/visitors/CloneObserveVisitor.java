package wordCount.visitors;

import wordCount.dsForStrings.AVLTree;
import wordCount.dsForStrings.AVLNode;
import wordCount.util.Logger;
import wordCount.util.FileProcessor;

public class CloneObserveVisitor implements VisitorInterface {
    private static Logger log = Logger.getInstance();

    public CloneObserveVisitor(){
        log.writeMessage("CONSTRUCTOR: CloneObserveVisitor() called.", Logger.DebugLevel.CONSTRUCTOR);
    }

    public void visit(AVLTree aTree) {
        AVLNode root = aTree.getRoot();
        AVLNode cloneRoot = (AVLNode)root.clone();
        root.addObserver(cloneRoot); 
        cloning(cloneRoot, root);
    }

    public void cloning(AVLNode rC, AVLNode r) {
        if(r.getLeft() != null) {
            AVLNode rLeft = r.getLeft();
            rC.setLeft((AVLNode)rLeft.clone());
            rLeft.addObserver(rC.getLeft());
            cloning(rC.getLeft(), rLeft);
        }
        if(r.getRight() != null) {
            AVLNode rRight = r.getRight();
            rC.setRight((AVLNode)rRight.clone());
            rRight.addObserver(rC.getRight());
            cloning(rC.getRight(), rRight);
        }
    }
}
