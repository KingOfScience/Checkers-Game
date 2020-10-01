package game;

import static game.checkerColour.BLACKKING;

import java.util.Scanner;

public class Player {
	//Create the printer object
	private Scanner sc;
	
	//Gets a string from the console
	protected final String getMove() {
		sc = new Scanner(System.in);
		final String input = sc.nextLine();
		return input;
	}
	
	//Checks if the move is a valid one. Returns false if not
	protected final boolean checkValidMove(final double startingXDouble,final double startingYDouble,final double endingXDouble,final double endingYDouble, Board board) {
		
		//Class variables
		byte bothEvenOrOdd = 1;
		final int startingX = (int)startingXDouble;
		final int startingY = (int)startingYDouble;
		final int endingX = (int)endingXDouble;
		final int endingY = (int)endingYDouble;
		
		//Check if the pawn is being put on a double odd or even number
		if((endingXDouble%2==0)&&(endingYDouble%2==0)) {
			bothEvenOrOdd = 0;
		}
		else if((endingXDouble%2!=0)&&(endingYDouble%2!=0)) {
			bothEvenOrOdd = 0;
		}
		
		//Check if valid move
		if(
				
		//Check if there is a piece already at destination
		(board.gameBoard[endingY][endingX]!=checkerColour.NONE)
		
		//Check if they are even moving a pawn
		&&		(board.gameBoard[startingY][startingX]!=checkerColour.NONE)
		) 
		{return false;}//Don't let the player move if they dont fit the conditions
		
		else {
			//Check to see if the pawn destination is full odd or evem
			//If so, then allow them to move there
			switch(bothEvenOrOdd) {
			
			case 0://True
				System.out.println("clause");
				//Now Get the colour logic
				if(board.gameBoard[startingY][startingX]==checkerColour.RED) {//If the colour is red
					if((endingY-startingY)>2) {//If you are moving more than two spaces, return false
						return false;
					}
					else if(((endingY-startingY)==1)) {//If you are moving one space, return true
						return true;
					}
					else if(((endingY-startingY)==2)&&(board.gameBoard[endingY-1][endingX-1]==checkerColour.BLACK)) {//If you are moving two spaces and hoping, then true
						return true;
					}
					else if(((endingY-startingY)==2)&&(board.gameBoard[endingY+1][endingX+1]==checkerColour.BLACK)) {//If you are moving two spaces and hoping, then true
						return true;
					}
					System.out.println("going no where");
				}
				else if(board.gameBoard[startingY][startingX]==checkerColour.BLACK) {//If the colour is Black
					if((startingY-endingY)>2) {//If you are moving two spaces, return false
						System.out.println(startingY-endingY);
						return false;
					}
					else if(((startingY-endingY)==1)) {//If you are moving one space, return true
						return true;
					}
					else if(((startingY-endingY)==2)&&(board.gameBoard[endingY+1][endingX-1]==checkerColour.RED)) {//If you are moving two spaces and hoping, then true
						return true;
					}
					else if(((startingY-endingY)==2)&&(board.gameBoard[endingY+1][endingX+1]==checkerColour.RED)) {//If you are moving two spaces and hoping, then true
						return true;
					}
				}
				else {
					System.out.println("really?");
					return true;
				}
			case 1://False, now determine the move logic
				System.out.println("no way");
				return false;
				
			default://Can't decide, don't make a move
				System.out.println("no");
				return false;
			}	
		}
	}
	
	//Moves a piece. Returns false if unable
	protected final boolean movePiece(final String starting, final String ending, Board board){
		
		//Break the starting position string into its two parts, the Y axis and X axis
		final String[] beginPosition = starting.split("-"); 
		final int startingY= Integer.parseInt(beginPosition[0]);
		final int startingX= Integer.parseInt(beginPosition[1]);

		//Break the ending position string into its two parts, the Y axis and X axis
		final String[] endPosition = ending.split("-"); 
		final int endingY= Integer.parseInt(endPosition[0]);
		final int endingX= Integer.parseInt(endPosition[1]);
		
		//Check if this is a valid move, if it is, return true and do the move. Otherwise stop.
		if(checkValidMove(startingX,startingY,endingX,endingY, board)==true){
			
			//If the piece is red, Then void it, and place a new piece at destination
				if(board.gameBoard[startingY][startingX] ==checkerColour.RED) {
					board.gameBoard[endingY][endingX]=checkerColour.RED;
					board.gameBoard[startingY][startingX]=checkerColour.NONE;
					capturePiece((byte)endingY,(byte)endingX,(byte)startingX,(byte)startingY, board);
					return true;
				}
				
			//If the piece is black, then void it, and place a new piece at destination
				else if(board.gameBoard[startingY][startingX] ==checkerColour.BLACK){
					board.gameBoard[endingY][endingX]=checkerColour.BLACK;
					board.gameBoard[startingY][startingX]=checkerColour.NONE;
					capturePiece((byte)endingY,(byte)endingX,(byte)startingX,(byte)startingY, board);
					return true;
				}
				else if(board.gameBoard[startingY][startingX] ==checkerColour.REDKING){
					board.gameBoard[endingY][endingX]=checkerColour.REDKING;
					board.gameBoard[startingY][startingX]=checkerColour.NONE;
					capturePiece((byte)endingY,(byte)endingX,(byte)startingX,(byte)startingY, board);
					return true;
				}
				else if(board.gameBoard[startingY][startingX] ==checkerColour.BLACKKING){
					board.gameBoard[endingY][endingX]=checkerColour.BLACKKING;
					board.gameBoard[startingY][startingX]=checkerColour.NONE;
					capturePiece((byte)endingY,(byte)endingX,(byte)startingX,(byte)startingY, board);
					return true;
				}
			//If it gets past checkValidMove, but doesn't fit in, then do nothing
				else {
					return false;
				}
		}
		//If it is not a valid move, do nothing
		else {
			System.out.println("invalid! oops!");
			return false;
		}
	}
	
	//Checks if a pawn should be destroyed, returns true if it has
	protected final void capturePiece(final byte endingY,final byte endingX,final byte startingX,final byte startingY, Board board){
		//If its red
		if((board.gameBoard[endingY][endingX] == checkerColour.RED)) {
			if((endingX!=7)) {
				if((endingY-startingY==2)) {//make sure its capturable
					if((board.gameBoard[endingY-1][endingX-1] == checkerColour.BLACK)||(board.gameBoard[endingY-1][endingX-1] == checkerColour.BLACKKING)) {
						board.gameBoard[endingY-1][endingX-1] = checkerColour.NONE;
					}
					else if((board.gameBoard[endingY-1][endingX+1] == checkerColour.BLACK)||(board.gameBoard[endingY-1][endingX+1] == checkerColour.BLACKKING)) {
						board.gameBoard[endingY-1][endingX+1] = checkerColour.NONE;
					}
				}
			}
		}
		//If its black
		else if((board.gameBoard[endingY][endingX] == checkerColour.BLACK)&&(endingX!=0)) {//If its black
			if(startingY-endingY==2) {//Make sure its capturable
				if((board.gameBoard[endingY+1][endingX-1] == checkerColour.RED)||(board.gameBoard[endingY+1][endingX-1] == checkerColour.REDKING)) {
					board.gameBoard[endingY+1][endingX-1] = checkerColour.NONE;
				}
				else if((board.gameBoard[endingY+1][endingX+1] == checkerColour.RED)||(board.gameBoard[endingY+1][endingX+1] == checkerColour.REDKING)) {
					board.gameBoard[endingY+1][endingX+1] = checkerColour.NONE;
				}
			}
		}
		//if its a red king
		else if((board.gameBoard[endingY][endingX]==checkerColour.REDKING)||(board.gameBoard[endingY][endingX]==BLACKKING)){

			//If they are going right
			if(endingX-startingX>0){
				board.gameBoard[endingY-1][endingX-1]=checkerColour.NONE;
			}

			//If they are going left
			else if(endingX-startingX<0){
				board.gameBoard[endingY-1][endingX+1]=checkerColour.NONE;
			}
			//There was an error if this happened
			else{
				System.out.println("LOGIC ERROR");
			}
		}
	}
	
	//Check to see if the game has been won
	protected final boolean gameWinCondition(Board board) {
		byte blackPieces=0;
		byte redPieces=0;
		for(byte x = 0;x!=8;x++) {
			for(byte y = 0;y!=8;y++) {
				if((board.gameBoard[x][y]==checkerColour.RED)||(board.gameBoard[x][y]==checkerColour.REDKING)){
					redPieces = (byte)(redPieces +1);
				}
				else if((board.gameBoard[x][y]==checkerColour.BLACK)||(board.gameBoard[x][y]==checkerColour.BLACKKING)) {
					blackPieces = (byte)(blackPieces+1);
				}
			}
		}
		if((blackPieces!=0)&&(redPieces!=0)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	//Converts a string into two integer values
	protected final int[] convertResponseToMove(final String starting) {
		//Take a string, and turn it into an array of strings
		final String split[] = starting.split("-");
		int intSplit[]= {0,0};
		for(byte x=0;x!=2;x++) {
			intSplit[x]=Integer.parseInt(split[x]);
		}
		return intSplit;
	}
	
	//Check if you should be kinged
	protected final void kingMe(Board board) {
		//Create an iterator
		byte i = 0;
		
		//Check if there are pawns that need to be kinged
		while(i!=8) {
			
			//Check reds
			if(board.gameBoard[7][i]==checkerColour.RED) {
				board.gameBoard[7][i]=checkerColour.REDKING;
			}
			
			//Check Blacks
			if(board.gameBoard[0][i]==checkerColour.BLACK) {
				board.gameBoard[0][i]=checkerColour.BLACKKING;
			}
			i++;
		}
	}
}
