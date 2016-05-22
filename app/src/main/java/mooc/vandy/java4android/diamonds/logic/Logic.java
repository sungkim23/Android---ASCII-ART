package mooc.vandy.java4android.diamonds.logic;

import android.util.Log;
import mooc.vandy.java4android.diamonds.ui.OutputInterface;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early
 * Android interactions.  Designing the assignments this way allows
 * you to first learn key 'Java' features without having to beforehand
 * learn the complexities of Android.
 */
public class Logic
       implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG = Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface [MainActivity.java].
     * <p>
     * It is called 'out' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'out' is good enough).
     */
    private OutputInterface mOut;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance (which
     * implements [OutputInterface]) to 'out'.
     */
    public Logic(OutputInterface out){
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labeled 'Process...' is pressed.
     */
    public void process(int size) {

        int boxHeight = (size * 2) + 1;
        int boxWidth = size * 2 + 2;

        boxAssembler(size, boxHeight, boxWidth);

    }

    //Creates the Box, and the Diamond inside it
    public void boxAssembler(int size, int boxHeight, int boxWidth){

        int accumulator = -(size+1);

        //This for loop is the vertical axis
        for (int i=1;i<=boxHeight;i++) {

            accumulator += 1;

            //This loop is for the horizontal axis
            for (int j=1;j<=boxWidth;j++) {

                if ((i == 1 || i == boxHeight) && (j == 1 || j == boxWidth)){
                    mOut.print("+");
                } else if ((i == 1 || i == boxHeight) && !(j == 1 || j == boxWidth)){
                    mOut.print("-");
                } else if (!(i == 1 || i == boxHeight) && (j == 1 || j == boxWidth)){
                    mOut.print("|");
                } else {
                    diamondAssembler(size, i, j, accumulator);
                }
            }
            mOut.println("");
        }
    }

    //Creates the Diamond, and the empty space around it
    public void diamondAssembler(int size, int i, int j, int accumulator){

        //Increase the thickness until the midpoint, then decrease it
        int diamondRowThickness;
        if (accumulator <= 0){
            diamondRowThickness = i*2-2;
        } else {
            diamondRowThickness = (i-accumulator*2)*2-2;
        }
        int diamondMidpoint = size + 1;
        int diamondBoundsLeft = diamondMidpoint - (diamondRowThickness/2-1);
        int diamondBoundsRight = diamondMidpoint + (diamondRowThickness/2);
        int frameTop = 1;
        int frameBottom = size * 2 + 1;

        if (j >= diamondBoundsLeft && j <= diamondBoundsRight) {
            if (j == diamondBoundsLeft || j == diamondBoundsRight) {
                if (i < diamondMidpoint && i > frameTop) {
                    if (j == diamondBoundsLeft) {
                        mOut.print("/");
                    } else {
                        mOut.print("\\");
                    }
                } else if (i == diamondMidpoint) {
                    if (j == diamondBoundsLeft) {
                        mOut.print("<");
                    } else {
                        mOut.print(">");
                    }
                } else if (i > diamondMidpoint && i < frameBottom) {
                    if (j == diamondBoundsLeft) {
                        mOut.print("\\");
                    } else {
                        mOut.print("/");
                    }
                }
            } else {
                if (i % 2 == 0) {
                    mOut.print("=");
                } else {
                    mOut.print("-");
                }
            }
        } else {
            mOut.print(" ");
        }
    }
}
