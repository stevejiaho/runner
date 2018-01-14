
/**
 * Marbles.java
 * Description Here
 * @author Your Name Here
 * @version 1.0
 * @since 1/9/2018
 */
import java.awt.Color;
import java.awt.Font;

public class Marbles
{
	/**    The board object.  1 represents a marble on the board, 0 is an empty space,
	 *     and -1 would indicate that this cell is not part of the board.                  */
	private int [][] board;
	
	/**    How long the GUI should pause, before expecting new input.                      */
	private int pause;
	
	/**    Current x and y values of the user's choice.  The x values count the cells
	 *     from the lower left to the right, while the y values count the cells from
	 *     the bottom left up.                                                             */
	private int xposition, yposition;
		
	/**
	 *  Creates a Marbles object, with the font to be used, current position initially
	 *  pause off the board, pause at 50 milliseconds, and the board values initialized
	 *  in a 9 x 9 grid.
	 */
	public Marbles ( )
	{
		Font font = new Font("Arial", Font.BOLD, 18);
		StdDraw.setFont(font);
		xposition = yposition = -5;
		pause = 50;
		board = new int[][]{{-1,-1,-1, 1, 1, 1,-1,-1,-1},
							{-1,-1,-1, 1, 1, 1,-1,-1,-1},
							{-1,-1,-1, 1, 1, 1,-1,-1,-1},
							{ 1, 1, 1, 1, 1, 1, 1, 1, 1},
							{ 1, 1, 1, 1, 0, 1, 1, 1, 1},
							{ 1, 1, 1, 1, 1, 1, 1, 1, 1},
							{-1,-1,-1, 1, 1, 1,-1,-1,-1},
							{-1,-1,-1, 1, 1, 1,-1,-1,-1},
							{-1,-1,-1, 1, 1, 1,-1,-1,-1}};
	}
	
	/**
	 *  Sets up and runs the game of Marbles.
	 *  @param  args     An array of String arguments (not used here).
	 */
	public static void main(String [] args)   
	{
		Marbles run = new Marbles();
		run.playGame();
	}
	
	/**
	 *  Comments.
	 */
	public void playGame ( )
	{
		boolean done = false;
		do
		{
			drawBoard();
			if(StdDraw.mousePressed())
			{
				double x = StdDraw.mouseX();
				double y = StdDraw.mouseY();
				int checkx = (int)(10 * x - 0.5);
				int checky = (int)(10 * y - 0.5);
				if(reset(x,y))
				{
					if(x>0.8-0.5&&x<0.8+0.5){
						if(y>0.25-0.125&&y<0.25+0.125){
							board = new int[][]{{-1,-1,-1,-1,-1,-1,-1,-1,-1},
									 			{-1,-1,-1, 1, 1, 1,-1,-1,-1}, 
									 			{-1,-1,-1, 1, 1, 1,-1,-1,-1}, 
									 			{-1, 1, 1, 1, 1, 1, 1, 1,-1}, 
									 			{-1, 1, 1, 1, 0, 1, 1, 1,-1}, 
									 			{-1, 1, 1, 1, 1, 1, 1, 1,-1}, 
									 			{-1,-1,-1, 1, 1, 1,-1,-1,-1}, 
									 			{-1,-1,-1, 1, 1, 1,-1,-1,-1},
				     			     			{-1,-1,-1,-1,-1,-1,-1,-1,-1}};
							//redeclares the board, resetting it to a 7x7 board
						}
						else if(y>0.12-0.125&&y<0.12+0.125){
							board = new int[][]{{-1,-1,-1, 1, 1, 1,-1,-1,-1},
									 			{-1,-1,-1, 1, 1, 1,-1,-1,-1},
									 			{-1,-1,-1, 1, 1, 1,-1,-1,-1},
									 			{ 1, 1, 1, 1, 1, 1, 1, 1, 1},
									 			{ 1, 1, 1, 1, 0, 1, 1, 1, 1},
									 			{ 1, 1, 1, 1, 1, 1, 1, 1, 1},
									 			{-1,-1,-1, 1, 1, 1,-1,-1,-1},
									 			{-1,-1,-1, 1, 1, 1,-1,-1,-1},
									 			{-1,-1,-1, 1, 1, 1,-1,-1,-1}};
				     		//redeclares the board, resetiting it to a 9x9 board
						}
					}
				}
				else if(possibleMoveSpace(xposition,yposition,checkx,checky))
				{
					board[xposition][yposition] = 0;
					board[xposition+checkx/2][yposition+checky/2] = 0;
					board[checkx][checky] = 1;
					//  You'll need to figure out how to "jump" from xposition, yposition to checkx, checky.
					drawBoard();
					StdDraw.show(4 * pause);
				}
				else
				{
					xposition = checkx;
					yposition = checky;
					StdDraw.show(pause);
				}
			}
			StdDraw.show(pause);
		}
		while(!done);
	}
	
	/**
	 *  Comments.
	 */
	public void drawBoard ( )
	{
		StdDraw.setPenColor(new Color(0,0,0));
		StdDraw.filledSquare(0.5,0.5,0.5);
		StdDraw.setPenColor(new Color(5,25,160));
		StdDraw.filledSquare(0.5,0.5,0.475);
		for ( int i = 0; i < board.length; i++ )
		{
			for ( int j = 0; j < board[i].length; j++ )
			{
				if(board[i][j] != -1)
				{
					drawCell(i,j);
				}
			}
		}
		drawResetButtons();
		drawWinOrLoseMessage();

	}
	
	/**
	 *  Comments.
	 */
	public void drawResetButtons ( )
	{
		StdDraw.setPenColor(new Color(0,0,0));
		StdDraw.filledRectangle(0.8, 0.25, 0.125, 0.05);
		StdDraw.setPenColor(new Color(255,255,255));
		StdDraw.text(0.8, 0.25, "RESET 7 x 7");
		StdDraw.setPenColor(new Color(0,0,0));
		StdDraw.filledRectangle(0.8, 0.12, 0.125, 0.05);
		StdDraw.setPenColor(new Color(255,255,255));
		StdDraw.text(0.8, 0.12, "RESET 9 x 9");
	}
	
	/**
	 *  Comments.
	 */
	public void drawWinOrLoseMessage ( )
	{		
		if(gameIsFinished()){
			if(countMarbles()>0){
				StdDraw.setPenColor(new Color(0,0,0));
				StdDraw.filledRectangle(0.2, 0.8, 0.125, 0.05);
				StdDraw.setPenColor(new Color(255,255,255));
				StdDraw.text(0.2, 0.8, "YOU SUCK!!!");
			}
			else if(countMarbles() == 0){
				StdDraw.setPenColor(new Color(0,0,0));
				StdDraw.filledRectangle(0.2, 0.8, 0.125, 0.05);
				StdDraw.setPenColor(new Color(255,255,255));
				StdDraw.text(0.2, 0.8, "YOU WON!!!");
			}
			else{
				StdDraw.setPenColor(new Color(0,0,0));
				StdDraw.filledRectangle(0.2, 0.8, 0.125, 0.05);
				StdDraw.setPenColor(new Color(255,255,255));
				StdDraw.text(0.12, 0.8, "YOU BROKE IT! :'(");
			}
		}
	}
	
	/**
	 *  Comments.
	 */
	public boolean reset(double x, double y)
	{
		if(x>0.8-0.5&&x<0.8+0.5){
			if(y>0.25-0.125&&y<0.25+0.125){
				return true;
			}
			else if(y>0.12-0.125&&y<0.12+0.125){
				return true;
			}
		}
		return false;
	}
	
	/**
	 *  Comments.
	 */
	public void drawCell(int x, int y)   
	{
		StdDraw.setPenColor(new Color(0,0,0));
		StdDraw.filledSquare(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.055);
		StdDraw.setPenColor(new Color(255,255,255));
		StdDraw.filledSquare(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.0425);
		StdDraw.setPenColor(new Color(200,200,200));
		StdDraw.filledCircle(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.02);
		if(x == xposition && y == yposition && board[x][y] == 1)
		{
			StdDraw.setPenColor(new Color(0,0,0));
			StdDraw.filledSquare(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.05);
			StdDraw.setPenColor(new Color(230,30,30));
			StdDraw.filledCircle(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.04);
		}
		if(possibleMoveSpace(xposition,yposition,x,y))
		{
			StdDraw.setPenColor(new Color(0,0,0));
			StdDraw.filledSquare(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.05);
			StdDraw.setPenColor(new Color(230,30,30));
			StdDraw.filledCircle(0.1 + 0.1 * x, 0.1 + 0.1 * y, 0.03);
		}
		if(board[x][y] == 1)
		{
			StdDraw.picture(0.1 + 0.1 * x, 0.1 + 0.1 * y,"marble.png");
		}
	}
	
	/**
	 *  It checks to see if it is a valid move to go from (x,y) to (xval, yval).
	 *  In order to do this, there must be a single marble between the 2 points
	 */
	public boolean possibleMoveSpace(int x, int y, int xval, int yval)
	{
		if(board[xval][yval] != 0){
			return false;
		}
		if(board[x][y] != 1){
			System.out.println("place (" + x + "," + y + ") is -1, and therefore place(" + x + "," + y + ") can't exist");
			return false;
		}
		if(xval>0&&xval<board[0].length){
			if(yval>0&&yval<board.length){
				if(x == xval){
					if(yval == y+2&&board[x][y+1] == 1){
						return true;
					}
					else if(yval == y-2&&board[x][y-1] == 1){
						return true;
					}
					else{
						return false;
					}
					//moves vertically
				}
				else if(y == yval){
					if(xval == x+2&&board[x+1][y] == 1){
						return true;
					}
					else if(xval == x-2&&board[x-1][y] == 1){
						return true;
					}
					else{
						return false;
					}
					//moves horizontally
				}
			}
		}
		return false;
	}
	
	/**
	 *  Checks if there are any possible moves left to make, and if not, then
	 *  it declares the game to be over;
	 */
	public boolean gameIsFinished()
	{
		if(countMarbles() == 1){
			return true;
		}
		for(int i = 0;i<board.length;i++){
			for(int j = 0;j<board[0].length;j++){
				if(board[i][j] == 1){
					if(!possibleMoveSpace(xposition,yposition,xposition,yposition+2)){
						return true;
					}
					if(!possibleMoveSpace(xposition,yposition,xposition,yposition-2)){
						return true;
					}
					if(!possibleMoveSpace(xposition,yposition,xposition+2,yposition)){
						return true;						
					}
					if(!possibleMoveSpace(xposition,yposition,xposition-2,yposition)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 *  Comments.
	 */
	public int countMarbles ( )
	{
		int asdf = 0;
		for(int i = 0;i<board.length;i++){
			for(int j = 0;j<board[0].length;j++){
				if(board[i][j] == 1){
					asdf++;
				}
			}
		}
		System.out.println(asdf);
		return asdf;
	}
}
