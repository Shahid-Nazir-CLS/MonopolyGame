import java.util.Random;
import javax.swing.JLabel;

public class Dice {
    private int diceValue;
    private Random rand;
    JLabel dice;

    public int getDiceFaceValue(){
        rand = new Random();

        // generate a random number and assign it to dice value
        diceValue = rand.nextInt(6) + 1;

        return diceValue;
    }
}
