package wordCount.dsForStrings;

import wordCount.util.Logger;
import wordCount.visitors.VisitorInterface;

/**
 * Code reference from http://www.sanfoundry.com/java-program-implement-avl-tree/
 *
 */
public class AVLTree {
    private static Logger log = Logger.getInstance();

    private AVLNode root;

    public AVLTree() {
        root = null;
    }

    public AVLTree(AVLNode rootIN) {
        root = rootIN;
    }

    /**
     * @return boolean True if the tree is empty
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * @return AVLNode the root of the tree
     */
    public AVLNode getRoot() {
        return root;
    }

    /**
     * @return int the height of the tree
     */
    public int height(AVLNode t) {
        return t == null ? -1 : t.getHeight();
    }

    /**
     * @return int the tree with more height
     */
    public int maxHeight(int lhs, int rhs) {
        return lhs > rhs ? lhs : rhs;
    }

    public void insert(String wordIN) {
        root = insert(wordIN, root);
    }

    /**
     * @return AVLNode the root for the tree that was inserted into
     * The root is returned just in case it was changed in the process
     * of rebalancing
     */
    public AVLNode insert(String wordIN, AVLNode t) {
        if(t == null){
            t = new AVLNode(wordIN);
            log.writeMessage("INSERT: insert()", Logger.DebugLevel.INSERT);

        } else if(wordIN.compareTo(t.data) < 0) {
            t.left = insert(wordIN, t.left);
            
            if(height(t.left) - height(t.right) == 2) {
                if(wordIN.compareTo(t.left.data) < 0) {
                    t = rotateWLeftChild(t);           
                } else {
                    t = doubleWLeftChild(t);
                }
            }
            
        } else if(wordIN.compareTo(t.data) > 0) {
            t.right = insert(wordIN, t.right);

            if(height(t.right) - height(t.left) == 2) {
                if(wordIN.compareTo(t.right.data) > 0) {
                    t = rotateWRightChild(t);
                } else {
                    t = doubleWRightChild(t);
                }
            } 

        } else {
            t.updateCount(); 
        }

        t.updateHeight((maxHeight(height(t.left), height(t.right)) + 1));

        return t;
    }

    /**
     * @return AVLNode 
     */
    private AVLNode rotateWLeftChild(AVLNode k2) {
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = maxHeight(height(k2.left), height(k2.right)) + 1;
        k1.height = maxHeight(height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     * @return AVLNode
     */
    private AVLNode rotateWRightChild(AVLNode k1) {
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = maxHeight(height(k1.left), height(k1.right)) + 1;
        k2.height = maxHeight(height(k2.right), k1.height) + 1;
        return k2;
    }

    /**
     * @return AVLNode
     */
    private AVLNode doubleWLeftChild(AVLNode k3) {
        k3.left = rotateWRightChild(k3.left);
        return rotateWLeftChild(k3);
    }

    /**
     * @return AVLNode
     */
    private AVLNode doubleWRightChild(AVLNode k4) {
        k4.right = rotateWLeftChild(k4.right);
        return rotateWRightChild(k4);
    }

    public void inorder(){
        inorder(root);
    }

    private void inorder(AVLNode r) {
        if(r != null) {
            inorder(r.left);
            System.out.print(r.data+ " ");
            inorder(r.right);
        }
    }

    public void preorder() {
        preorder(root);
        System.out.println();
    }

    private void preorder(AVLNode r) {
        if (r != null) {
            System.out.print(r.data+ " ");
            System.out.print("count: " + r.count+ "\n");
            preorder(r.left);
            preorder(r.right);
        }
    }

    /**
     * @return int the count for that word
     */ 
    public int search(String val) {
        return search(root, val);
    }

    private int search(AVLNode r, String val) {
        int found = 0;

        while ((r != null) && 0 == found) {
            String rval = r.data;

            if(val.compareTo(rval) < 0) {
                r = r.left;
            } else if (val.compareTo(rval) > 0) {
                r = r.right;
            } else {
                found = r.count;
                break;
            }

            found = search(r, val);
        }

        return found;
    }

    public void accept(VisitorInterface v) {
        v.visit(this);
    }
}
