import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class Board extends JFrame implements ActionListener{

	JButton throwBtn;
	JButton soundBtn;
	JButton newBtn;
	JButton testBtn;
	JButton sellBtn;
	JButton startBtn;
	JButton exitBtn;
	JLabel bc1;
	JLayeredPane boardPanel; 
	JPanel[] playerPanelOnBoard = new JPanel[5];

    Dice dc1;
    Dice dc2;

	JTextArea txtArea; 

	public Board(){

		// Name of Game Window
		super("Monopoly");

		// Size of window
		// setSize(800,700);

		// Exit Game on Closing Window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Location of window on Screen
		setLocation(400,50);

		// Window cannot be resized i.e. window size is fixed
		setResizable(false);


		// Panel Image 
        boardPanel = new JLayeredPane();
        boardPanel.setBackground(Color.BLUE);
        boardPanel.setPreferredSize(new Dimension(800, 830));
        boardPanel.setOpaque(true);
        

        // Panel for text box
        JPanel textPanel = new JPanel();
        textPanel.setBackground(Color.BLUE);
        textPanel.setPreferredSize(new Dimension(420, 830));

        // adding text field to text panel
    	txtArea = new JTextArea("Welcome to Monopoly.\n");  
    	txtArea.setFont(new Font("Serif", Font.PLAIN, 16));
        txtArea.setEnabled(false);
        txtArea.setDisabledTextColor(Color.BLACK);
    	txtArea.setLineWrap(true);

    	JScrollPane scroll = new JScrollPane (txtArea);
    	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	scroll.setPreferredSize(new Dimension(400, 820));
    	textPanel.add(scroll);

        // panel that will contain ImagePanel and TextPanel
        JPanel containerPanel = new JPanel();

        // Panel that will contain button panel and other panels
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.BLUE);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2,3));
        buttonPanel.setBackground(Color.BLUE);


        // conatiner panel for player panels on the board
       	JPanel conatinerPlayerPanel = new JPanel(new GridLayout(1, 4));
        conatinerPlayerPanel.setBackground(Color.LIGHT_GRAY);
        conatinerPlayerPanel.setBounds( 120, 120, 550, 420);
        boardPanel.add(conatinerPlayerPanel, 2);

        // player panels
        for(int i = 1; i < 5; i++){
        	playerPanelOnBoard[i] = new JPanel(new GridLayout(15, 1));
        	playerPanelOnBoard[i].setBackground(new Color(0,0,0,0));
            // playerPanelOnBoard[i].setVisible(false);
        	playerPanelOnBoard[i].setBorder(BorderFactory.createLineBorder(Color.black));
        	conatinerPlayerPanel.add(playerPanelOnBoard[i]);
        }


        // adding buttons to panels
        newBtn = new JButton("New Game");
		newBtn.setFont(new Font("Serif", Font.BOLD, 30));
		newBtn.setEnabled(false);
		buttonPanel.add(newBtn);

		soundBtn = new JButton("Sound");
		soundBtn.setFont(new Font("Serif", Font.BOLD, 30));
		buttonPanel.add(soundBtn);

		throwBtn = new JButton("Throw");
		throwBtn.setFont(new Font("Serif", Font.BOLD, 30));
        throwBtn.setEnabled(false);
		// throwBtn.setEnabled(false);
		buttonPanel.add(throwBtn);

		sellBtn = new JButton("Sell");
		sellBtn.setFont(new Font("Serif", Font.BOLD, 30));
		sellBtn.setEnabled(false);
		buttonPanel.add(sellBtn);

		exitBtn = new JButton("Exit");
		exitBtn.setFont(new Font("Serif", Font.BOLD, 30));
		buttonPanel.add(exitBtn);
		exitBtn.addActionListener(this);


		startBtn = new JButton("Start");
		startBtn.setFont(new Font("Serif", Font.BOLD, 30));
		buttonPanel.add(startBtn);
		startBtn.addActionListener(this);

        // adding die to boardpanel        
        dc1 = new Dice();
        dc1.dice = new JLabel(new ImageIcon("5.jpg"));
        dc1.dice.setBounds( 250, 520, 186, 186);
        boardPanel.add(dc1.dice, 2);

        // 2nd dice
        dc2 = new Dice();
        dc2.dice = new JLabel(new ImageIcon("6.jpg"));
        dc2.dice.setBounds( 400, 520, 186, 186);
        boardPanel.add(dc2.dice, 2);

        // Adding panels to the main window
        containerPanel.add(boardPanel);
        containerPanel.add(textPanel);



        // adding board
        Icon background1 = new ImageIcon("board.jpg");
        bc1 = new JLabel(background1);
		bc1.setBounds( 5, 5, 790, 790);
		bc1.setBorder(BorderFactory.createLineBorder(Color.black));
		bc1.setOpaque(true);

        boardPanel.add(bc1);

        footerPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(containerPanel, BorderLayout.NORTH);
        add(buttonPanel);			
        pack();

        // Visibility of window
        setVisible(true);


	}

	public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == exitBtn)
            System.exit(0);
    }
}