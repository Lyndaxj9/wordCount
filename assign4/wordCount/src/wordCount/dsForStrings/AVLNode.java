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

    public String getData() {
        return data;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode nL) {
        left = nL;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode nR) {
        right = nR;
    }

    public int getHeight() {
        return height;
    }

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

    public AVLNode getObserver(int index) {
        return observers.get(index);
    }

    public void update(int multiplier) {
        count *= multiplier;
    }

    public Object clone() {
        
        AVLNode aClone = new AVLNode(data);
        aClone.count = count;
        aClone.height = height;
        return aClone;
        
        //return this;
    }
}
