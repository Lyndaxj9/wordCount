import wordCount.util.FileProcessor;
import wordCount.util.Logger;
import wordCount.dsForStrings.AVLTree;
import wordCount.visitors.VisitorInterface;
import wordCount.visitors.PopulateVisitor;
import wordCount.visitors.WordCountVisitor;
import wordCount.visitors.CloneObserveVisitor;
import wordCount.visitors.UpdateVisitor;

public class Driver {

    public static void main(String[] args) {
        int debugValue = 1;
        int numIterations = 3;
        String input = "";
        String output = "";

//-- Reading in command line arguments 
        if(args.length >= 3) {
            try {
                input = args[0];
                output = args[1];
                numIterations = Integer.parseInt(args[2]);

                if(numIterations <= 0) {
                    System.out.println("Please enter a positive number greater than 0!");
                    System.exit(1);
                }

            } catch(NumberFormatException e) {
                e.printStackTrace();
                System.exit(1);
            }
        } else {
            System.out.println("USAGE: -Darg0=inputFile.txt -Darg1=outputFile.txt -Darg2=#Iterations");
            System.exit(1);
        }

//-- Reading in debug level for Logger
        if (args.length == 4) {
            try {
                debugValue = Integer.parseInt(args[3]);
                
                if(debugValue > 4 && debugValue < 0) {
                    debugValue = 0;
                }
            } catch(NumberFormatException e) {
                debugValue = 0;
            }
        }
        
        Logger log = Logger.getInstance();
        log.setDebugValue(debugValue);

//-- START TIMING LOOP --
        long startTime = System.currentTimeMillis();
        AVLTree wordTree = new AVLTree();
        
        for (int i = 0; i < numIterations; i++) {
            wordTree = new AVLTree();
            VisitorInterface pv = new PopulateVisitor(input);
            VisitorInterface wcv = new WordCountVisitor(output);
            wordTree.accept(pv);
            wordTree.accept(wcv);
        }

        long finishTime = System.currentTimeMillis();
        long totalTime = (finishTime - startTime)/numIterations;
        System.out.printf("Total Time: %d milliseconds\n", totalTime);

//-- CLONE AND OBSERVER TREE
        if (!wordTree.isEmpty()) {
            VisitorInterface cov = new CloneObserveVisitor();
            VisitorInterface uv = new UpdateVisitor(3);
            wordTree.accept(cov);
            AVLTree treeBackup = new AVLTree((wordTree.getRoot()).getObserver(0));
            wordTree.accept(uv);
        }

   }
}
