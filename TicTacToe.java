

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.GridLayout;

public class TicTacToe extends JFrame{

	private final int SIZE = 3;
	private String PLAYER_X = "X";
	private String PLAYER_O = "O";
	private int PLAYER_X2 = 1;
	private int PLAYER_O2 = 2;
	private static int turnCounter = 1;
	private int counterX;
	private int counterO;
	private boolean crossWins = false;
	private boolean circleWins = false;
	private int cellsFilled = 0;
	private JTextField [][] fields = new JTextField[SIZE][SIZE];
	private int [][] stateOfGame = new int[SIZE][SIZE];
	private JTextField result;
	private JPanel boardPanel, appPanel, btnPanel;
	private static final Font BOLD = new Font("Monospaced", Font.BOLD, 20);
	JButton newGame, exit;
	Font ft, ftX;
    private enum GameStatus{Win, Lose, Tie;}
    String gS;
        
        private void GameStatus(GameStatus gameStatus)
        {
            switch(gameStatus){
                case Win:
                    gS = "You win!";
                    break;
                case Lose:
                    gS = "You lose!";
                    break;
                case Tie:
                    gS = "You tie!";
                    break;
            }
        }
	
	//constructor
	public TicTacToe(){
		
		setTitle("TicTacToe");
		
		//panel for TicTacToe board
		boardPanel = new JPanel(new GridLayout(SIZE,SIZE));
		boardPanel.setBorder(new LineBorder(Color.BLACK, 4));
		boardPanel.setBorder(BorderFactory.createEmptyBorder(30,50,0,50));
		
		appPanel = new JPanel(new BorderLayout(10, 10));//panel that holds board and buttons	
		btnPanel = new JPanel(new FlowLayout());//panel that holds buttons
		btnPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
	
		//new game button, clears board of all symbols
		newGame = new JButton();
		newGame.setText("New Game");
		
		newGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){		
				clearBoard();
				initializeStateArray();
                                for(int row = 0; row<SIZE; row++){
                                    for(int col = 0; col<SIZE; col++){
                                        fields[row][col].setEditable(true);
                                        fields[row][col].setFocusable(true);
                                    }
                                }
                computerTurn();                
			}
		});
			
		exit = new JButton();
		exit.setText("Exit");
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		//fonts
		ft = new Font("Helvetica", Font.PLAIN, 18);
		ftX = new Font("sans-serif", Font.BOLD, 45);
		
		//display result
		result = new JTextField();
		result.setHorizontalAlignment(JTextField.CENTER);
		result.setEditable(false);
        result.setFocusable(false);
		result.setColumns(11);
		result.setFont(ft);
			
		//create cells (JTextfields) for TicTacToe board, set border, etc.
		for(int row = 0; row < fields.length; row++){		
			for(int col = 0; col < fields.length; col++){
						
				fields[row][col] = new JTextField();
				final int r = row;
				final int c = col;
					
				//add ActionListener to each JtextField
				fields[row][col].addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent event){
								
						if(fields[r][c].getText().equalsIgnoreCase(PLAYER_X) && turnCounter % 2 == 0){
							
							fields[r][c].setText("X");
							stateOfGame[r][c] = PLAYER_X2;
							fields[r][c].setEditable(false);
                            fields[r][c].setFocusable(false);
							fields[r][c].setBackground(Color.WHITE);
							cellsFilled++;
							turnCounter++;
							checkBoard();
							
							//once user has placed its symbol and board is not full yet and no one has won yet, call computerTurn function
                            if(!crossWins && !circleWins && cellsFilled != 9)
                            {
                                computerTurn();
                            }								
						}
					}
				});//end inner class
						
				initializeStateArray();
				
				//add all JTextFields to the boardPanel
				boardPanel.add(fields[row][col]);
				fields[row][col].setText("");
				fields[row][col].setEditable(true);
				fields[row][col].setHorizontalAlignment(JTextField.CENTER);
				fields[row][col].setFont(BOLD);
				fields[row][col].setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));	
				fields[row][col].setFont(ftX);
			}
		}		
				
		//add all the GUI components to frame
		btnPanel.add(newGame, BorderLayout.WEST);
		btnPanel.add(exit, BorderLayout.EAST);
		btnPanel.add(result, BorderLayout.SOUTH);
		appPanel.add(boardPanel, BorderLayout.CENTER);
		appPanel.add(btnPanel, BorderLayout.SOUTH);
				
		add(appPanel, BorderLayout.CENTER);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
		
		//playerO goes first
		computerTurn();
		
	}//end constructor
	
	
	//clears the board of all symbols and initializes int array
		private void clearBoard(){
			
			for(int row = 0; row < fields.length; row++){		
				for(int col = 0; col < fields.length; col++){
					
					fields[row][col].setText("");
					fields[row][col].setEditable(true);
					fields[row][col].setHorizontalAlignment(JTextField.CENTER);
					fields[row][col].setFont(BOLD);
					fields[row][col].setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
					fields[row][col].setBackground(Color.WHITE);
					fields[row][col].setFont(ftX);
				}
			}
			
			turnCounter = 1;
			cellsFilled = 0;
			result.setText("");
		}
		
		public void initializeStateArray(){
			for(int x = 0; x < stateOfGame.length; x++){
				for(int y = 0; y < stateOfGame.length; y++){
					stateOfGame[x][y] = 0;
				}
			}
		}
		
		//this method calls the generateTree function to generate the state tree and the expectiminimax function
		//to compute the best move for the computer
		public void computerTurn()
	    {            
			int[]coordinates = new int[2];
			int row, col;
			
			//current state of the game
			StateNode rootNode = new StateNode(stateOfGame);
			
			StateTree tree = new StateTree();
			
			//generate a new 3 layer tree based on current state
			tree.generateTree(rootNode, 0);	
			//find the best move for the computer based on the generated tree
			tree.findBestMove(coordinates);
			
			//when best move was found, the coordinates of where to place the symbol O was extracted
			row = coordinates[0];
			col = coordinates[1];
			
			//use coordinates to place O
			fields[row][col].setText(PLAYER_O); 
            fields[row][col].setEditable(false);
            fields[row][col].setFocusable(false);
            fields[row][col].setBackground(Color.WHITE);
			stateOfGame[row][col] = PLAYER_O2;
						
            turnCounter++;
            cellsFilled++;
            
            //check if there is a winner or a tie
            checkBoard();     
	    }
	
	//check rows, columns and diagonals for a winner and displays result
	public void checkBoard(){

        crossWins = false;
        circleWins = false;

        checkRows();
        checkColumns();
        checkDiagonals();

        if(crossWins && !circleWins){
            for(int i = 0; i<3; i++)
            {
                for(int j = 0; j < 3; j++)
                {
                    fields[i][j].setEditable(false);
                }
            }
            GameStatus(GameStatus.Win);
            result.setText(gS);
        }else if(circleWins && !crossWins){
            for(int i = 0; i<3; i++)
            {
                for(int j = 0; j < 3; j++)
                {
                    fields[i][j].setEditable(false);
                }
            }
            GameStatus(GameStatus.Lose);
            result.setText(gS);
        }else if(cellsFilled == 9){
            for(int i = 0; i<3; i++)
            {
                for(int j = 0; j < 3; j++)
                {
                    fields[i][j].setEditable(false);
                }
            }
            GameStatus(GameStatus.Tie);
            result.setText(gS);
        }
    }

	//check if there are three X's or O's in a row
    public void checkRows(){

        for(int row = 0; row < fields.length; row++){

            counterX = 0;
            counterO = 0;

            for(int column = 0; column < fields.length; column++){

                if(fields[row][column].getText().equalsIgnoreCase(PLAYER_X)){
                    counterX++;
                }else if(fields[row][column].getText().equalsIgnoreCase(PLAYER_O)){
                    counterO++;
                }

            }
            checkStatus();
        }
    }

    //check if there are three X's or O's in a column
    public void checkColumns(){

        for(int column = 0; column < fields.length; column++){

            counterX = 0;
            counterO = 0;

            for(int row = 0; row < fields.length; row++){

                if(fields[row][column].getText().equalsIgnoreCase(PLAYER_X)){
                    counterX++;
                }else if(fields[row][column].getText().equalsIgnoreCase(PLAYER_O)){
                    counterO++;
                }
            }
            checkStatus();
        }
    }

    //check if there are three X's or O's in a diagonal
    public void checkDiagonals(){

        counterX = 0;
        counterO = 0;

        for(int row = 0; row < fields.length; row++){
            for(int column = 0; column < fields.length; column++){

                if(row == column){

                    if(fields[row][column].getText().equalsIgnoreCase(PLAYER_X)){
                        counterX++;
                    }else if(fields[row][column].getText().equalsIgnoreCase(PLAYER_O)){
                        counterO++;
                    }

                }
            }
        }
        checkStatus();

        int y = 0;
        int x = -1;
        counterX = 0;
        counterO = 0;
        for(int row = 2; row >= 0; row--){
            x++;
            for(int col = y; col <= x; col++){

                if(fields[row][col].getText().equalsIgnoreCase(PLAYER_X)){
                    counterX++;
                }else if(fields[row][col].getText().equalsIgnoreCase(PLAYER_O)){
                    counterO++;
                }
                checkStatus();
                y++;
            }
        }
    }

    //check if any three cells in a row, column or diagonal have the same symbol
    public void checkStatus(){

        if(counterX == 3){
            crossWins = true;
        }

        if(counterO == 3){
            circleWins = true;
        }
    }	
}
