package wordCount.util;

/**
 * This class is used for debugging and printing statements
 * from various methods depending on the debug level
 */
public class Logger{

    private static Logger log = new Logger();

    public static Logger getInstance(){
        return log;
    }

    public static enum DebugLevel { 
        NONE, DEBUG, CONSTRUCTOR, VISIT, INSERT,
    };

    private static DebugLevel debugLevel;


    public static void setDebugValue (int levelIn) {
        switch (levelIn) {
            case 0: debugLevel = DebugLevel.NONE; break;
            case 1: debugLevel = DebugLevel.DEBUG; break;
            case 2: debugLevel = DebugLevel.CONSTRUCTOR; break;
            case 3: debugLevel = DebugLevel.VISIT; break;
            case 4: debugLevel = DebugLevel.INSERT; break;
        }
    }

    public static void setDebugValue (DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    // @return None
    public static void writeMessage (String message,
            DebugLevel levelIn ) {
        if (levelIn == debugLevel)
            System.out.println(message);
    }

    /*
     * @return the string representation of Logger which is the debug
     * level
     */
    public String toString() {
        return "Debug Level is " + debugLevel;
    }
}
