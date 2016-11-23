package wordCount.visitors;

import wordCount.dsForStrings.AVLTree;

/**
 * Interface that is a base for the rest of the visitor classes
 */
public interface VisitorInterface {
    public void visit(AVLTree aTree);

}
