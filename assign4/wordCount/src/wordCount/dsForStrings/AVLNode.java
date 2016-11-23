package wordCount.dsForStrings;

import wordCount.util.Cloneable;
import java.util.ArrayList;

/**
 * Code reference from http://www.sanfoundry.com/java-program-implement-avl-tree/
 *
 */
public class AVLNode implements Cloneable {

    AVLNode left, right;
    String data;
    int count, height;

    //Holds the observers to the original node
    ArrayList<AVLNode> observers = new ArrayList<AVLNode>();

    public AVLNode() {
        left = null;
        right = null;
        data = "";
        count = 0;
        height = 0;
    }

    public AVLNode(String wordIN) {
        left = null;
        right = null;
        data = wordIN;
        count = 1;
        height = 0;
    }

    /**
     * @return String containing the data which is the word
     */
    public String getData() {
        return data;
    }

    /**
     * @return AVLNode the left node
     */
    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode nL) {
        left = nL;
    }

    /**
     * @return AVLNode the right node
     */
    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode nR) {
        right = nR;
    }

    /**
     * @return int the height of the node
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return int the count, the amount of time the data (word)
     * appeared in the file
     */
    public int getCount() {
        return count;
    }

    private void setCount(int number) {
        count = number;
    }

    public void updateCount() {
        count++;
    }

    public void multiCount(int multiplier) {
        count *= multiplier;
        notifyObservers(multiplier);
    }

    public void updateHeight(int heightIN) {
        height = heightIN; 
    }

    public void addObserver(AVLNode obs) {
        observers.add(obs);
    }

    private void notifyObservers(int multiplier) {
        for (int i = 0; i < observers.size(); i++) {
            AVLNode obs = observers.get(i);
            obs.update(multiplier);
        }
    }

    /**
     * @return AVLNode the observer node at the index in the
     * ArrayList
     */
    public AVLNode getObserver(int index) {
        return observers.get(index);
    }

    public void update(int multiplier) {
        count *= multiplier;
    }

    /**
     * @return Object and AVLNode with the information from this node
     */
    public Object clone() {
        
        AVLNode aClone = new AVLNode(data);
        aClone.count = count;
        aClone.height = height;
        return aClone;
        
        //return this;
    }
}
