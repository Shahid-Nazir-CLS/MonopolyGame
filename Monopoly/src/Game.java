import java.util.Scanner;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.*; 
import java.lang.reflect.Field;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// game class
class Game implements ActionListener{
    private Board board;
    private Color clr;
    private int turnOfPlayer;
    private int rndNo1;
    private int rndNo2;
    private Properties properties;
    private int noOfPropertiesOnBoard;
    int soundOn = 0;

    JLabel[] nameAndMoney = new JLabel[5];
    Player[] player = new Player[5];
    JLabel[] propertyOnBoard = new JLabel[30];
    HashMap<String, JLabel> jLabelRefMap = new HashMap<String, JLabel>();
    HashMap<String, Integer> propertyOwnerMap = new HashMap<String, Integer>();


    AudioInputStream clickAudioInputStream;
    Clip clickClip;

    AudioInputStream backgroundAudioInputStream;
    Clip backgroundClip;

    AudioInputStream throwAudioInputStream;
    Clip throwClip;


    // normal boxes increment
    int inc1 = 64;
    // bottom left corner to up increment
    int inc2 = 80;
    // bottom right corner to left increment and top right -1 to top right increment
    int inc3 = 102;

    List<String> colorsAvailable = new ArrayList<>(); 

    // Constructor
    public Game(){

        board = new Board();

        // initial turn of player1
        turnOfPlayer = 1;

        // initial number of properties on board
        noOfPropertiesOnBoard = 0;

        // initialize players
        for(int i = 1; i <=4; i++){
            player[i] = new Player();
        }

        // add action listeners to buttons
        board.startBtn.addActionListener(this);
        board.exitBtn.addActionListener(this);
        board.throwBtn.addActionListener(this);
        board.newBtn.addActionListener(this);
        board.soundBtn.addActionListener(this);
        board.sellBtn.addActionListener(this);


        // initialize list of colors
        colorsAvailable.add("GREEN");
        colorsAvailable.add("RED");
        colorsAvailable.add("BLUE");
        colorsAvailable.add("YELLOW");

        // initialize properties
        properties = new Properties();

        // add background sound
        try {
            backgroundAudioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("mainback.wav"));
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(backgroundAudioInputStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            soundOn = 1;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

     public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == board.exitBtn)
            System.exit(0);

        if(evt.getSource() == board.startBtn){

            if(soundOn == 1){

                try {
                    throwAudioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("click.wav"));
                    throwClip = AudioSystem.getClip();
                    throwClip.open(throwAudioInputStream);
                    throwClip.start();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            gameStart();
        }

        if(evt.getSource() == board.throwBtn){
            throwDie();
        }

        if(evt.getSource() == board.soundBtn){

            if(soundOn == 1){

                try {
                    clickAudioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("click.wav"));
                    clickClip = AudioSystem.getClip();
                    clickClip.open(clickAudioInputStream);
                    clickClip.start();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            soundOnOff();
        }

        if(evt.getSource() == board.newBtn){

            if(soundOn == 1){

                try {
                    clickAudioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("click.wav"));
                    clickClip = AudioSystem.getClip();
                    clickClip.open(throwAudioInputStream);
                    clickClip.start();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            newGame();
        }

        if(evt.getSource() == board.sellBtn){

            if(soundOn == 1){

                try {
                    clickAudioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("click.wav"));
                    clickClip = AudioSystem.getClip();
                    clickClip.open(clickAudioInputStream);
                    clickClip.start();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            sellProperty();
        }


    }

    public void gameStart(){
        // get and set each player name
        for(int i = 1; i <=4; i++){

            String name;
            while(true){

                name = JOptionPane.showInputDialog(null,"Player" + i +", Enter your Name : \n");

                // check if name is not empty
                if(!name.equals("")){
                    break;
                }
            }
            // set player name
            player[i].setPlayerName(name);

            String color;
            
            while(true){
                // loop till correct color has been entered
                color = JOptionPane.showInputDialog(null,"Player" + i +", Enter one of the given color as your Token Color :\n "+ colorsAvailable + "\n");

                    if(colorsAvailable.contains(color)){
                        break;
                    }
            }

            player[i].token = new Token();

            // set player token color
            player[i].token.setTokenColor(color);
            colorsAvailable.remove(color);

            
            //give initial money
            player[i].setPlayerMoney(1500);

            // set token color
			try {
			    Field field = Class.forName("java.awt.Color").getField(player[i].token.getTokenColor());
			    clr = (Color)field.get(null);
			} catch (Exception e) {
			    clr = null; // Not defined
			}

			// add player color token to the board after he selects color
            player[i].token.square = new JPanel();
        	player[i].token.square.setBackground(clr);
            player[i].token.square.setVisible(true);
			player[i].token.square.setBounds( 770, 703 + (i - 1) * 17, 10, 10);
        	board.boardPanel.add(player[i].token.square, i, i);

        	// add players color and name on the text area
        	board.txtArea.append(":> " + player[i].getPlayerName() + " has chosen : " + player[i].token.getTokenColor() + " token.\n");
        	board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());

            // show names and initial money of players on the board
            nameAndMoney[i] = new JLabel("  " + player[i].getPlayerName() + " : $1500");
            nameAndMoney[i].setFont(new Font("Serif", Font.BOLD, 13));
            board.playerPanelOnBoard[i].add(nameAndMoney[i]);
            board.playerPanelOnBoard[i].setBackground(clr);

            board.playerPanelOnBoard[i].revalidate(); 
            board.playerPanelOnBoard[i].repaint();
        }

        // disable start button after taking input from users
        board.startBtn.setEnabled(false);
        board.newBtn.setEnabled(true);
        board.throwBtn.setEnabled(true);
        board.sellBtn.setEnabled(true);

        // add to text area that now which player's turn it is
        board.txtArea.append(":> " + "Now it is " + player[turnOfPlayer].getPlayerName() + " turn.\n");
        board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());
    }

    public void throwDie(){
        int j = turnOfPlayer;
        String locXAndLocY = String.valueOf(player[j].token.getlocX()) + ", " + String.valueOf(player[j].token.getlocY());

        // if player has money to pay for property and is not stuck
        if(player[j].getPlayerStuck() == 0 && player[j].getPlayerMoney() > 0){

            // add background sound
            if(soundOn == 1){
                try {
                    throwAudioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("throw.wav"));
                    throwClip = AudioSystem.getClip();
                    throwClip.open(throwAudioInputStream);
                    throwClip.start();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            // paste
            rndNo1 = player[j].rollDice1();
            rndNo2 = player[j].rollDice2();


            board.dc1.dice.setIcon(new ImageIcon(String.valueOf(rndNo1) + ".jpg"));
            board.dc2.dice.setIcon(new ImageIcon(String.valueOf(rndNo2) + ".jpg"));

            board.txtArea.append(":> " + player[j].getPlayerName() + " has rolled a sum of  " + (rndNo1 + rndNo2) + " on the die.\n");
            board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());


            // move token that number of steps as sum of die if player has enough money to pay rent 
        
            for(int i = 1; i <= (rndNo1 + rndNo2); i++){
                moveToken();
            }

            // after rolling the die what should be done by the player
            takeDecision();

        }else if(player[j].getPlayerStuck() == 1 && player[j].getPlayerMoney() >= Integer.parseInt(properties.getPropertyRent(locXAndLocY))){
            
            if(soundOn == 1){
                try {
                    throwAudioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("throw.wav"));
                    throwClip = AudioSystem.getClip();
                    throwClip.open(throwAudioInputStream);
                    throwClip.start();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


            // if player was broke and now has money
            player[j].setPlayerStuck(0);

            // paste
            rndNo1 = player[j].rollDice1();
            rndNo2 = player[j].rollDice2();


            board.dc1.dice.setIcon(new ImageIcon(String.valueOf(rndNo1) + ".jpg"));
            board.dc2.dice.setIcon(new ImageIcon(String.valueOf(rndNo2) + ".jpg"));

            board.txtArea.append(":> " + player[j].getPlayerName() + " has rolled a sum of  " + (rndNo1 + rndNo2) + " on the die.\n");
            board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());


            // move token that number of steps as sum of die if player has enough money to pay rent 
        
            for(int i = 1; i <= (rndNo1 + rndNo2); i++){
                moveToken();
            }

            // after rolling the die what should be done by the player
            takeDecision();


        }else if(player[j].getPlayerStuck() == 1 && player[j].getPlayerMoney() < Integer.parseInt(properties.getPropertyRent(locXAndLocY)) ){

            // player is stuck
            JOptionPane pane = new JOptionPane("Hey " + player[j].getPlayerName() + ", Guess what!\n You are 'BROKE' and cannot buy or rent this property and cannot even roll the Die.\nUntil you have enough money to rent the place you cannot move.\n");
            JDialog dialog = pane.createDialog(null, "Monopoly");
            dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            dialog.setVisible(true);
            dialog.dispose();

        }


        // if player rolls doubles then he will get another roll of die
        if(rndNo1 == rndNo2){

            // press ok option pay to pay rent
                JOptionPane pane1 = new JOptionPane(player[turnOfPlayer].getPlayerName() + ", you have rolled doubles.\n You will roll die again.");
                JDialog dialog1 = pane1.createDialog(null, "Monopoly");
                dialog1.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog1.setVisible(true);
                dialog1.dispose();

                // add to text area that now which player's turn it is
                board.txtArea.append(":> " + player[turnOfPlayer].getPlayerName() + ", has rolled doubles.\n He will roll die again.\n");
                board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());


        }else{
            // increase turn of player i.e. set turn to next player
            turnOfPlayer = (turnOfPlayer + 1) % 5;
            if(turnOfPlayer == 0)
                turnOfPlayer = 1;
        }

        // add to text area that now which player's turn it is
        board.txtArea.append(":> " + "Now it is " + player[turnOfPlayer].getPlayerName() + " turn.\n");
        board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());

        // after rolling die check if any three players are bankrupt or stuck at some property then game is over
        // and results will be displayed
        checkWin();
    }

    public void takeDecision(){
        int i = turnOfPlayer;
        // move token or not after rolling die

        String locXAndLocY = String.valueOf(player[i].token.getlocX()) + ", " + String.valueOf(player[i].token.getlocY());

       

        // chance
        if(locXAndLocY.equals("-7, 0") || locXAndLocY.equals("0, -4") || locXAndLocY.equals("-8, -10")){
            // you have reached chance and will be given 50$ and parking here is free i.e. no rent
                JOptionPane pane = new JOptionPane(player[turnOfPlayer].getPlayerName() + ", you have reached Chance.\n Parking here is free and you have won $50.");
                JDialog dialog = pane.createDialog(null, "Monopoly");
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(true);
                dialog.dispose();

            // add 50 to player money
                player[turnOfPlayer].setPlayerMoney(player[turnOfPlayer].getPlayerMoney() + 50);

            // add to text area
                board.txtArea.append(":> " + player[turnOfPlayer].getPlayerName() + " has reached Chance and won $50.\n");
                board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());
                
                nameAndMoney[turnOfPlayer].setText("  " + player[turnOfPlayer].getPlayerName() + " : " + String.valueOf(player[turnOfPlayer].getPlayerMoney()));

            return;
        }

        // community chest
        if(locXAndLocY.equals("-10, -7") || locXAndLocY.equals("0, -7") || locXAndLocY.equals("-2, 0")){
            // add money $200

            // joption pane
                JOptionPane pane = new JOptionPane(player[turnOfPlayer].getPlayerName() + ", you have found Community Chest.\n Parking here is free and you have won $200.");
                JDialog dialog = pane.createDialog(null, "Monopoly");
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(true);
                dialog.dispose();

            // add 200 to player money
                player[turnOfPlayer].setPlayerMoney(player[turnOfPlayer].getPlayerMoney() + 200);

            // add to text area
                board.txtArea.append(":> " + player[turnOfPlayer].getPlayerName() + " has found Community Chest and won $200.\n");
                board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());

                nameAndMoney[turnOfPlayer].setText("  " + player[turnOfPlayer].getPlayerName() + " : " + String.valueOf(player[turnOfPlayer].getPlayerMoney()));

            return;
        }

        // free parking
        if(locXAndLocY.equals("0, 0") || locXAndLocY.equals("-10, -10")){
            // hey shahid good news you have landed on free parking and so you don't have to pay anything here
            
            // joption pane
                JOptionPane pane = new JOptionPane(player[turnOfPlayer].getPlayerName() + ", you have reached Free Parking so you can stay here without any rent.");
                JDialog dialog = pane.createDialog(null, "Monopoly");
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(true);
                dialog.dispose();


            // add to text area
                board.txtArea.append(":> " + player[turnOfPlayer].getPlayerName() + " has reached a free parking.\n");
                board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());

                nameAndMoney[turnOfPlayer].setText("  " + player[turnOfPlayer].getPlayerName() + " : " + String.valueOf(player[turnOfPlayer].getPlayerMoney()));

            return;
        }

        // tax
        if(locXAndLocY.equals("-4, 0")){
            // subtract 200 from player money

            // joption pane
                JOptionPane pane = new JOptionPane(player[turnOfPlayer].getPlayerName() + ", you have reached Income Tax.\n You have to pay $200 as Income Tax.");
                JDialog dialog = pane.createDialog(null, "Monopoly");
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(true);
                dialog.dispose();

            // subtract 200 from player money
                player[turnOfPlayer].setPlayerMoney(player[turnOfPlayer].getPlayerMoney() - 200);

            // add to text area
                board.txtArea.append(":> " + player[turnOfPlayer].getPlayerName() + " has paid an Income Tax of $200.\n");
                board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());

                nameAndMoney[turnOfPlayer].setText("  " + player[turnOfPlayer].getPlayerName() + " : " + String.valueOf(player[turnOfPlayer].getPlayerMoney()));

            return;
        }

        // luxury tax
        if(locXAndLocY.equals("0, -2")){
            // subtract 100 from player money

            // joption pane
                JOptionPane pane = new JOptionPane(player[turnOfPlayer].getPlayerName() + ", you have reached Luxury Tax.\n You have to pay $100 as Luxury Tax.");
                JDialog dialog = pane.createDialog(null, "Monopoly");
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(true);
                dialog.dispose();

            // subtract 100 from player money
                player[turnOfPlayer].setPlayerMoney(player[turnOfPlayer].getPlayerMoney() - 100);

            // add to text area
                board.txtArea.append(":> " + player[turnOfPlayer].getPlayerName() + " has paid Luxury Tax of $100.\n");
                board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());

                nameAndMoney[turnOfPlayer].setText("  " + player[turnOfPlayer].getPlayerName() + " : " + String.valueOf(player[turnOfPlayer].getPlayerMoney()));

            return;
        }

        // jail
        if(locXAndLocY.equals("-10, 0")){
            // subtract 300 from user money

            // joption pane
                JOptionPane pane = new JOptionPane(player[turnOfPlayer].getPlayerName() + ", you have landed in Jail.\n You have to pay $300 to get out of Jail.");
                JDialog dialog = pane.createDialog(null, "Monopoly");
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(true);
                dialog.dispose();

            // subtract 300 from player money
                player[turnOfPlayer].setPlayerMoney(player[turnOfPlayer].getPlayerMoney() - 300);

            // add to text area
                board.txtArea.append(":> " + player[turnOfPlayer].getPlayerName() + " has paid $300 to get out of jail.\n");
                board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());

                nameAndMoney[turnOfPlayer].setText("  " + player[turnOfPlayer].getPlayerName() + " : " + String.valueOf(player[turnOfPlayer].getPlayerMoney()));

            return;
        }

        // go to jail
        if(locXAndLocY.equals("0, -10")){
            // // move token to jail

            // joption pane
                JOptionPane pane = new JOptionPane(player[turnOfPlayer].getPlayerName() + ", you have landed in Go To Jail.\nYou will be sent to jail and you have to pay $300 to get out of Jail.");
                JDialog dialog = pane.createDialog(null, "Monopoly");
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(true);
                dialog.dispose();

            // subtract 100 from user money
                player[turnOfPlayer].setPlayerMoney(player[turnOfPlayer].getPlayerMoney() - 300);

            // add to text area
                board.txtArea.append(":> " + player[turnOfPlayer].getPlayerName() + " was sent to jail and has paid $300 to get out of jail.\n");
                board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());

            // move user token to jail -10, 0
                player[turnOfPlayer].token.setlocX(-10);
                player[turnOfPlayer].token.setlocY(0);

                player[turnOfPlayer].token.square.setLocation(92 , player[turnOfPlayer].token.square.getY() + 672);

                player[turnOfPlayer].token.setDirection(2);

                nameAndMoney[turnOfPlayer].setText("  " + player[turnOfPlayer].getPlayerName() + " : " + String.valueOf(player[turnOfPlayer].getPlayerMoney()));

            return;
        }


        // if property has now onwer
        if(properties.getPropertyOwner(locXAndLocY).equals("0")){

            String userInput;
            // show message to player about buying or renting property

            while(true){

                userInput = JOptionPane.showInputDialog(null,"Hey "+ player[i].getPlayerName() +", you have reached " + properties.getPropertyName(locXAndLocY) + ".\n This propert isn't owned by anyone.\nDo you want to buy the property for $" + properties.getPropertyCost(locXAndLocY)+ " or pay the rent of $" + properties.getPropertyRent(locXAndLocY) + " for staying here.\n Enter 1 to buy and 2 to pay rent.");

                if(Integer.parseInt(userInput) == 1 || Integer.parseInt(userInput) == 2){
                    break;
                }else{
                }
            }


            // player wants to buy that property
            if(userInput.equals("1")){

                // press ok option pay to pay rent
                JOptionPane pane = new JOptionPane("Press OK to buy the property.");
                JDialog dialog = pane.createDialog(null, "Monopoly");
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(true);
                dialog.dispose();

                // check if player has money greater than rent
                if(player[i].getPlayerMoney() >= Integer.parseInt(properties.getPropertyCost(locXAndLocY))){

                    // subtract money from players money if has enough money to pay rent
                    player[i].setPlayerMoney(player[i].getPlayerMoney() - Integer.parseInt(properties.getPropertyCost(locXAndLocY)));


                    // set property owner as that player
                    properties.setPropertyOwner(locXAndLocY, String.valueOf(i));

                    // update money in player panel on board
                    nameAndMoney[i].setText("  " + player[i].getPlayerName() + " : " + String.valueOf(player[i].getPlayerMoney()));

                    // add to text area that this player has bought this property
                    board.txtArea.append(":> " + player[i].getPlayerName() + " has bought " + properties.getPropertyName(locXAndLocY) + " for $ " + properties.getPropertyCost(locXAndLocY) + ".\n");
                    board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());
                    
                    // add jlabel on player panel board
                    propertyOnBoard[noOfPropertiesOnBoard] = new JLabel("   " + properties.getPropertyName(locXAndLocY));
                    propertyOnBoard[noOfPropertiesOnBoard].setFont(new Font("Serif", Font.BOLD, 13));
                    board.playerPanelOnBoard[i].add(propertyOnBoard[noOfPropertiesOnBoard]);

                    // save reference to jlabel using hashmap
                    jLabelRefMap.put(properties.getPropertyName(locXAndLocY), propertyOnBoard[noOfPropertiesOnBoard]);

                    // save property owner in hashmap with property name
                    propertyOwnerMap.put(properties.getPropertyName(locXAndLocY), i);

                    // increment number of properties on board
                    noOfPropertiesOnBoard++;


                }else if(player[i].getPlayerMoney() >= Integer.parseInt(properties.getPropertyRent(locXAndLocY))){

                    // pay rent if he cannot afford money
                    JOptionPane pane8 = new JOptionPane("You don't have enough money to buy this property.\nPress OK to pay rent of this property.");
                    JDialog dialog8 = pane8.createDialog(null, "Monopoly");
                    dialog8.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    dialog8.setVisible(true);
                    dialog8.dispose();


                    // subtract money from players money if has enough money to pay rent
                    player[i].setPlayerMoney(player[i].getPlayerMoney() - Integer.parseInt(properties.getPropertyRent(locXAndLocY)));


                    // update money in player panel on board
                    nameAndMoney[i].setText("  " + player[i].getPlayerName() + " : " + String.valueOf(player[i].getPlayerMoney()));

                    // add to text area that this player has paid rent on this property
                    board.txtArea.append(":> " + player[i].getPlayerName() + " has paid rent of $" + properties.getPropertyRent(locXAndLocY) + " on  " + properties.getPropertyName(locXAndLocY) + ".\n");
                    board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());
                    
                }else{
                    // if player doesn't have money for rent as well as property then he is stuck at that place until he has money
                    // output player has no money and is bankrupt
                    JOptionPane pane1 = new JOptionPane("Hey " + player[i].getPlayerName() + ", Guess what!\n You are 'BROKE' and cannot buy or rent this property.\nUntil you have enough money to rent the place you cannot move.\n");
                    JDialog dialog1 = pane1.createDialog(null, "Monopoly");
                    dialog1.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    dialog1.setVisible(true);
                    dialog1.dispose();
                    player[i].setPlayerStuck(1);

                    // add to text area that this player hasn't enough money to buy or rent this property
                    board.txtArea.append(":> " + player[i].getPlayerName() + " doesn't have enough money to buy or rent " + properties.getPropertyName(locXAndLocY) + " so he is stuck on this property.");
                    board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());
                }

            // player wants to pay rent    
            }else if(userInput.equals("2")){

                // press ok option pay to pay rent
                JOptionPane pane2 = new JOptionPane("Press OK to pay rent for the property.");
                JDialog dialog2 = pane2.createDialog(null, "Monopoly");
                dialog2.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog2.setVisible(true);
                dialog2.dispose();

                // check if player has money greater than rent
                if(player[i].getPlayerMoney() >= Integer.parseInt(properties.getPropertyRent(locXAndLocY))){

                    // subtract money from players money if has enough money to pay rent
                    player[i].setPlayerMoney(player[i].getPlayerMoney() - Integer.parseInt(properties.getPropertyRent(locXAndLocY)));

                    // add to text area that this player has paid rent on this property
                    board.txtArea.append(":> " + player[i].getPlayerName() + " has paid rent of $" + properties.getPropertyRent(locXAndLocY) + " on  " + properties.getPropertyName(locXAndLocY) + ".\n");
                    board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());

                    // update money in player panel on board
                    nameAndMoney[i].setText("  " + player[i].getPlayerName() + " : " + String.valueOf(player[i].getPlayerMoney()));
                }else{
                   // output player has no money and is bankrupt
                    JOptionPane pane3 = new JOptionPane("Hey " + player[i].getPlayerName() + ", Guess what!\n You are 'BROKE' and cannot buy or rent this property.\nUntil you have enough money to rent the place you cannot move.\n");
                    JDialog dialog3 = pane3.createDialog(null, "Monopoly");
                    dialog3.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    dialog3.setVisible(true);
                    dialog3.dispose();
                    player[i].setPlayerStuck(1);

                    // add to text area that this player hasn't enough money to buy or rent this property
                    board.txtArea.append(":> " + player[i].getPlayerName() + " doesn't have enough money to rent " + properties.getPropertyName(locXAndLocY) + " so he is stuck on this property.");
                    board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());
                }
            }

        // property already has a owner
        }else if(!properties.getPropertyOwner(locXAndLocY).equals("0")){

            // check if property on which player landed is owned by same player
            if(properties.getPropertyOwner(locXAndLocY).equals(String.valueOf(i))){

                // joption pane
                JOptionPane pane4 = new JOptionPane("Hey "+ player[i].getPlayerName() +", you have reached " + properties.getPropertyName(locXAndLocY) + ".\n This property is already owned by you.\nYou don't have to pay anything here.\nYou can stay here for free.");
                JDialog dialog4 = pane4.createDialog(null, "Monopoly");
                dialog4.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog4.setVisible(true);
                dialog4.dispose();

                // add to text area that this player hasn't enough money to buy or rent this property
                    board.txtArea.append(":> " + player[i].getPlayerName() + " has reached " + properties.getPropertyName(locXAndLocY) + " and he is the owner of this property so he doesn't have to pay rent or buy this place.\n");
                    board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());

            }else{
                
                // press ok option pane to pay rent

            int owner = Integer.parseInt(properties.getPropertyOwner(locXAndLocY));

            JOptionPane pane4 = new JOptionPane("Hey "+ player[i].getPlayerName() +", you have reached " + properties.getPropertyName(locXAndLocY) + ".\n This property is owned by " + player[owner].getPlayerName() + ". \nPress OK to pay $" + properties.getPropertyRent(locXAndLocY) + " as rent for the property.");
            JDialog dialog4 = pane4.createDialog(null, "Monopoly");
            dialog4.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            dialog4.setVisible(true);
            dialog4.dispose();


            // check
            if(player[i].getPlayerMoney() >= Integer.parseInt(properties.getPropertyRent(locXAndLocY))){

            // subtract money from players money if has enough money to pay rent
                player[i].setPlayerMoney(player[i].getPlayerMoney() - Integer.parseInt(properties.getPropertyRent(locXAndLocY)));
            
            // add money to owner
                player[Integer.parseInt(properties.getPropertyOwner(locXAndLocY))].setPlayerMoney(player[Integer.parseInt(properties.getPropertyOwner(locXAndLocY))].getPlayerMoney() + Integer.parseInt(properties.getPropertyRent(locXAndLocY)));

            // update money on board
                nameAndMoney[i].setText("  " + player[i].getPlayerName() + " : " + String.valueOf(player[i].getPlayerMoney())); 

                 nameAndMoney[Integer.parseInt(properties.getPropertyOwner(locXAndLocY))].setText("  " + player[Integer.parseInt(properties.getPropertyOwner(locXAndLocY))].getPlayerName() + " : " + String.valueOf(player[Integer.parseInt(properties.getPropertyOwner(locXAndLocY))].getPlayerMoney()));

                 // add to text area that this player hasn't enough money to buy or rent this property
                    board.txtArea.append(":> " + player[i].getPlayerName() + " has reached " + properties.getPropertyName(locXAndLocY) + " and this property is owned by " + player[Integer.parseInt(properties.getPropertyOwner(locXAndLocY))].getPlayerName() + " so " + player[i].getPlayerName() + " has paid a rent of $" + properties.getPropertyRent(locXAndLocY) + " to " + player[Integer.parseInt(properties.getPropertyOwner(locXAndLocY))].getPlayerName() + "\n");
                    board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());

            // if player has not enough money    
            }else{
                // output player has no money and is bankrupt

                    JOptionPane pane5 = new JOptionPane("Hey " + player[i].getPlayerName() + ", Guess what!\n You are 'BROKE' and cannot buy or rent this property.\nUntil you have enough money to rent the place you cannot move.\n");
                    JDialog dialog5 = pane5.createDialog(null, "Monopoly");
                    dialog5.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    dialog5.setVisible(true);
                    dialog5.dispose();
                    player[i].setPlayerStuck(1);

                    // add to text area that this player hasn't enough money to buy or rent this property
                    board.txtArea.append(":> " + player[i].getPlayerName() + " doesn't have enough money to rent " + properties.getPropertyName(locXAndLocY) + " so he is stuck on this property.");
                    board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());
            }

            }


        }

        board.playerPanelOnBoard[i].revalidate(); 
        board.playerPanelOnBoard[i].repaint();

    }

    public void moveToken(){

        int i = turnOfPlayer;

        // check if we area at corner nodes then add more displacement for token
        if((player[i].token.getlocX() == 0 & player[i].token.getlocY() ==0 ) || (player[i].token.getlocX() == -10 & player[i].token.getlocY() ==0 ) || (player[i].token.getlocX() == -10 & player[i].token.getlocY() == -10 ) || (player[i].token.getlocX() == 0 & player[i].token.getlocY() == -10 )){
                
            // increment x or y according to player[i].token.getDirection()
            if(player[i].token.getDirection() == 1){
                player[i].token.setlocX(player[i].token.getlocX() - 1);
                player[i].token.square.setLocation(player[i].token.square.getX() - inc3, player[i].token.square.getY());
            }else if(player[i].token.getDirection() == 2){
                player[i].token.setlocY(player[i].token.getlocY() - 1);
                player[i].token.square.setLocation(player[i].token.square.getX(), player[i].token.square.getY() - inc2);
            }else if(player[i].token.getDirection() == 3){
                player[i].token.setlocX(player[i].token.getlocX() + 1);
                player[i].token.square.setLocation(player[i].token.square.getX() + inc1, player[i].token.square.getY());
            }else if( player[i].token.getDirection() == 4){
                player[i].token.setlocY(player[i].token.getlocY() + 1);
                player[i].token.square.setLocation(player[i].token.square.getX(),player[i].token.square.getY() + inc2);
            }

        }
        // we are not at corners
        else{

            // check if we have reached corner node i.e. token is in corner square then change player[i].token.getDirection()
            if((player[i].token.getlocX() == 0 & player[i].token.getlocY() == -1 ) || (player[i].token.getlocX() == -9 & player[i].token.getlocY() == 0 ) || (player[i].token.getlocX() == -10 & player[i].token.getlocY() == -9 ) || (player[i].token.getlocX() == -1 & player[i].token.getlocY() == -10 )){
                
                // increment x or y according to player[i].token.getDirection()
                if(player[i].token.getDirection() == 1){
                    player[i].token.setlocX(player[i].token.getlocX() - 1);
                    player[i].token.square.setLocation(player[i].token.square.getX() - inc1, player[i].token.square.getY());
                    player[i].token.setDirection(2);

                }else if(player[i].token.getDirection() == 2){
                    player[i].token.setlocY(player[i].token.getlocY() - 1);
                    player[i].token.square.setLocation(player[i].token.square.getX(), player[i].token.square.getY() - inc2);
                    player[i].token.setDirection(3);

                }else if(player[i].token.getDirection() == 3){
                    player[i].token.setlocX(player[i].token.getlocX() + 1);
                    player[i].token.square.setLocation(player[i].token.square.getX() + inc3, player[i].token.square.getY());
                    player[i].token.setDirection(4);

                }else if( player[i].token.getDirection() == 4){
                    player[i].token.setlocY(player[i].token.getlocY() + 1);
                    player[i].token.square.setLocation(player[i].token.square.getX(), player[i].token.square.getY() + inc2);
                    player[i].token.setDirection(1);
                    }


                } else{

                    if(player[i].token.getDirection() == 1){
                    player[i].token.setlocX(player[i].token.getlocX() - 1);
                    player[i].token.square.setLocation(player[i].token.square.getX() - inc1, player[i].token.square.getY());
                    }else if(player[i].token.getDirection() == 2){
                    player[i].token.setlocY(player[i].token.getlocY() - 1);
                    player[i].token.square.setLocation(player[i].token.square.getX(), player[i].token.square.getY() - inc1);
                }else if(player[i].token.getDirection() == 3){
                    player[i].token.setlocX(player[i].token.getlocX() + 1);
                    player[i].token.square.setLocation(player[i].token.square.getX() + inc1, player[i].token.square.getY());
                }else if( player[i].token.getDirection() == 4){
                    player[i].token.setlocY(player[i].token.getlocY() + 1);
                    player[i].token.square.setLocation(player[i].token.square.getX(), player[i].token.square.getY() + inc1);
                }
            }
        }
    }

    public void sellProperty(){
        // enter name of owner of property
        String ownerName = JOptionPane.showInputDialog(null,"Enter name of owner of the property.");

        // enter property location
        String ownerPropertyLocationX = JOptionPane.showInputDialog(null,"Enter x location of property.\ne.g. x location for Baltic and Ventnor is 7 and for  Kentucky and Connecticut, x location will be 1.");

        String ownerPropertyLocationY = JOptionPane.showInputDialog(null,"Enter y location of property.\ne.g. y location for New York and Pacific is 1 and for  States and Park Place, y location will be 7.");

        // 

        // create string for xloc and yloc

        String locXAndLocY = String.valueOf((-10 + Integer.parseInt(ownerPropertyLocationX))) + ", " + String.valueOf((-10 + Integer.parseInt(ownerPropertyLocationY)));

        int i = 1; 
        boolean ownerFound = false;

        // get the player no from player name
        for(i = 1; i < 5; i++){

            if(player[i].getPlayerName().equals(ownerName)){

                ownerFound = true;
                break;
            }
        }

        try{

            if(ownerFound == false){

             // Wrong name enetered
                JOptionPane pane = new JOptionPane("No player has that name which you entered.");
                JDialog dialog = pane.createDialog(null, "Monopoly");
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(true);
                dialog.dispose();

        }else{
            
            // check if he is owner of that property
            if(properties.getPropertyOwner(locXAndLocY).equals(String.valueOf(i))){

                // enter name of buyer of property

            String buyerName = JOptionPane.showInputDialog(null,"Enter name of buyer of the property.");

            boolean buyerFound = false;

            int  j = 1;
            // check if buyer's name is correct
            for(j = 1; j < 5; j++){

            if(player[j].getPlayerName().equals(buyerName)){

                    buyerFound = true;
                    break;
                }
            }

            if(buyerFound == false){

             // Wrong name enetered
                JOptionPane pane1 = new JOptionPane("No player has that name which you entered.");
                JDialog dialog1 = pane1.createDialog(null, "Monopoly");
                dialog1.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog1.setVisible(true);
                dialog1.dispose();

            }else if (buyerName.equals(ownerName)){
                // same buyer and seller name
                JOptionPane pane2 = new JOptionPane("The buyer and seller name are same. This transaction can't take place.");
                JDialog dialog2 = pane2.createDialog(null, "Monopoly");
                dialog2.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog2.setVisible(true);
                dialog2.dispose();

            }else{

                // buyer is found

                // check if he has enough money to buy the property
                if(player[j].getPlayerMoney() >= Integer.parseInt(properties.getPropertyCost(locXAndLocY))){
                    // if yes then change owner 

                    properties.setPropertyOwner(locXAndLocY, String.valueOf(j));

                    // add money to seller
                    player[i].setPlayerMoney(player[i].getPlayerMoney() + Integer.parseInt(properties.getPropertyCost(locXAndLocY)));

                    // subtract from buyers
                    player[j].setPlayerMoney(player[j].getPlayerMoney() - Integer.parseInt(properties.getPropertyCost(locXAndLocY)));
                    
                    // update money in board panel
                    nameAndMoney[i].setText("  " + player[i].getPlayerName() + " : " + String.valueOf(player[i].getPlayerMoney()));

                    nameAndMoney[j].setText("  " + player[j].getPlayerName() + " : " + String.valueOf(player[j].getPlayerMoney()));



                    // add message to text area

                    board.txtArea.append(":> " + player[i].getPlayerName() + " has sold " + properties.getPropertyName(locXAndLocY) + " to " + player[j].getPlayerName() + " for $" + properties.getPropertyCost(locXAndLocY));
                    board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());

                    // add jlabel and remove jlabel  seller
                    board.playerPanelOnBoard[i].remove(jLabelRefMap.get(properties.getPropertyName(locXAndLocY)));
                    
                    propertyOwnerMap.remove(properties.getPropertyName(locXAndLocY));

                    board.playerPanelOnBoard[i].revalidate(); 
                    board.playerPanelOnBoard[i].repaint();


                    board.playerPanelOnBoard[j].add(jLabelRefMap.get(properties.getPropertyName(locXAndLocY)));
                    
                    propertyOwnerMap.put(properties.getPropertyName(locXAndLocY), j);

                    board.playerPanelOnBoard[j].revalidate(); 
                    board.playerPanelOnBoard[j].repaint();
                    return;


                }else{
                    // if no then joption pane not enough money to buy this property
                    JOptionPane pane3 = new JOptionPane("You don't have enough money to buy this place.");
                    JDialog dialog3 = pane3.createDialog(null, "Monopoly");
                    dialog3.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    dialog3.setVisible(true);
                    dialog3.dispose();


                }
            }
            
            

        }else{

            JOptionPane pane3 = new JOptionPane(ownerName + " is not the owner of the" + properties.getPropertyName(locXAndLocY) + " so this property can't be sold by him.");
            JDialog dialog3 = pane3.createDialog(null, "Monopoly");
            dialog3.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            dialog3.setVisible(true);
            dialog3.dispose();


        }

        }


        }catch(NullPointerException exp){
            JOptionPane pane9 = new JOptionPane("Your input location doesn't correspond to any property location.");
            JDialog dialog9 = pane9.createDialog(null, "Monopoly");
            dialog9.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            dialog9.setVisible(true);
            dialog9.dispose();
        }
    }

    public void newGame(){


        String input = JOptionPane.showInputDialog(null,"Are you sure you want to start a new Game.\nEnter CONFIRM to start a new Game.");

        if(input.equals("CONFIRM")){

            // reset players
            for(int i = 1; i < 5; i++){
                player[i].setPlayerName(null);
                player[i].setPlayerMoney(0);
                player[i].setPlayerStuck(0);


                // reset init labels
                board.playerPanelOnBoard[i].remove(nameAndMoney[i]);
                board.playerPanelOnBoard[i].revalidate(); 
                board.playerPanelOnBoard[i].repaint();

                // reset each player token
                player[i].token.setlocX(0); 
                player[i].token.setlocY(0);
                player[i].token.setDirection(1);

                // reset token squares
                player[i].token.square.setVisible(false);

                // reset each player on board panel
                board.playerPanelOnBoard[i].setBackground(Color.LIGHT_GRAY);
                board.playerPanelOnBoard[i].revalidate(); 
                board.playerPanelOnBoard[i].repaint();



            }

            // reset property owners to 0
            resetPropertyOwners();

            // reset game class variables
            turnOfPlayer = 1;

            colorsAvailable.add("GREEN");
            colorsAvailable.add("RED");
            colorsAvailable.add("BLUE");
            colorsAvailable.add("YELLOW");

            // set buttons
            board.startBtn.setEnabled(true);
            board.sellBtn.setEnabled(false);
            board.newBtn.setEnabled(false);
            board.throwBtn.setEnabled(false);

            // txt area update

            board.txtArea.setText("Welcome to Monopoly.");

            board.txtArea.append(":> " + "\nPress on start to start a new game.\n");
            board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());

            // clear property labels on board
            for (String key : jLabelRefMap.keySet()) {
                
                // reset each player on board panel
                board.playerPanelOnBoard[propertyOwnerMap.get(key)].remove(jLabelRefMap.get(key));
                board.playerPanelOnBoard[propertyOwnerMap.get(key)].revalidate(); 
                board.playerPanelOnBoard[propertyOwnerMap.get(key)].repaint();
            }
                // remove all elements from map
                jLabelRefMap.clear();
                propertyOwnerMap.clear();
        }

        
    }

    public void soundOnOff(){

        if(soundOn == 1){
                backgroundClip.stop();
                soundOn = 0;
            }else{
                backgroundClip.start();
                soundOn = 1;
            }
    }

    public void checkWin(){

        int countStuck = 0;

        for(int i = 1; i < 5; i++){
            if(player[i].getPlayerStuck() == 1){
                countStuck++;
            }
        }

        if(countStuck == 3){

            board.startBtn.setEnabled(false);
            board.throwBtn.setEnabled(false);
            board.newBtn.setEnabled(true);
            board.soundBtn.setEnabled(true);
            board.sellBtn.setEnabled(false);

            // joption pane add, positions of each player
            JOptionPane pane = new JOptionPane("The Game is over.\n The results are as follows : " + player[1].getPlayerName() + " : $" + player[1].getPlayerMoney() + "\n" +  player[2].getPlayerName() + " : $" + player[2].getPlayerMoney() + "\n" +  player[3].getPlayerName() + " : $" + player[3].getPlayerMoney() + "\n" +  player[4].getPlayerName() + " : $" + player[4].getPlayerMoney() + "\nTo start a new game press New Game button.");
            JDialog dialog = pane.createDialog(null, "Monopoly");
            dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            dialog.setVisible(true);
            dialog.dispose();

            // txt area add, positions of each player
            board.txtArea.append(":> " + "The Game is over.\n The results are as follows : " + player[1].getPlayerName() + " : $" + player[1].getPlayerMoney() + "\n" +  player[2].getPlayerName() + " : $" + player[2].getPlayerMoney() + "\n" +  player[3].getPlayerName() + " : $" + player[3].getPlayerMoney() + "\n" +  player[4].getPlayerName() + " : $" + player[4].getPlayerMoney() + "\nTo start a new game press New Game button.");
            board.txtArea.setCaretPosition(board.txtArea.getDocument().getLength());

        }
        // reset countstuck to 0 again
        countStuck = 0;
    }

    public void resetPropertyOwners(){

        // reset property owners to 0
        properties.setPropertyOwner("0, 0", "0");
        properties.setPropertyOwner("-1, 0", "0");
        properties.setPropertyOwner("-2, 0", "0");
        properties.setPropertyOwner("-3, 0", "0");
        properties.setPropertyOwner("-4, 0", "0");
        properties.setPropertyOwner("-5, 0", "0");
        properties.setPropertyOwner("-6, 0", "0");
        properties.setPropertyOwner("-7, 0", "0");
        properties.setPropertyOwner("-8, 0", "0");
        properties.setPropertyOwner("-9, 0", "0");
        properties.setPropertyOwner("-10, 0", "0");

        properties.setPropertyOwner("-10, -1", "0");
        properties.setPropertyOwner("-10, -2", "0");
        properties.setPropertyOwner("-10, -3", "0");
        properties.setPropertyOwner("-10, -4", "0");
        properties.setPropertyOwner("-10, -5", "0");
        properties.setPropertyOwner("-10, -6", "0");
        properties.setPropertyOwner("-10, -6", "0");
        properties.setPropertyOwner("-10, -7", "0");
        properties.setPropertyOwner("-10, -8", "0");
        properties.setPropertyOwner("-10, -9", "0");
        properties.setPropertyOwner("-10, -10", "0");

        properties.setPropertyOwner("-9, -10", "0");
        properties.setPropertyOwner("-8, -10", "0");
        properties.setPropertyOwner("-7, -10", "0");
        properties.setPropertyOwner("-6, -10", "0");
        properties.setPropertyOwner("-5, -10", "0");
        properties.setPropertyOwner("-4, -10", "0");
        properties.setPropertyOwner("-3, -10", "0");
        properties.setPropertyOwner("-2, -10", "0");
        properties.setPropertyOwner("-1, -10", "0");
        properties.setPropertyOwner("0, -10", "0");

        properties.setPropertyOwner("0, -9", "0");
        properties.setPropertyOwner("0, -8", "0");
        properties.setPropertyOwner("0, -7", "0");
        properties.setPropertyOwner("0, -6", "0");
        properties.setPropertyOwner("0, -5", "0");
        properties.setPropertyOwner("0, -4", "0");
        properties.setPropertyOwner("0, -3", "0");
        properties.setPropertyOwner("0, -2", "0");
        properties.setPropertyOwner("0, -1", "0");

    }
}

