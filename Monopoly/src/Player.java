import javax.swing.JPanel;

public class Player {
    private String playerName;
    Token token;

    private int money;
    private int stuck;

    private Dice dice1;
    private Dice dice2;   


    public Player(){
        playerName = null;
        stuck = 0;
    }

    public int getPlayerMoney(){
        return this.money;
    }

    public void setPlayerMoney(int money){
        this.money = money;
    }

    public int getPlayerStuck(){
        return this.stuck;
    }

    public void setPlayerStuck(int stuck){
        this.stuck = stuck;
    }

    public int rollDice1(){
        dice1 = new Dice();
        return dice1.getDiceFaceValue();
    }

    public int rollDice2(){
        dice2 = new Dice();
        return dice2.getDiceFaceValue();
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
