package game;

public class Board {
	checkerColour[][] gameBoard = new checkerColour[8][8];

	//Display the board to console
	protected final void showBoard(){
		for(byte x = 0;x<8;x++) {
			for(byte y = 0;y<8;y++) {
				System.out.print("|" +this.gameBoard[x][y]+ "|");
			}
			System.out.println();
			System.out.println("------------------------------------------");
		}
	}

	//initialize the board with default pieces
	protected Board() {
		
		//Put in spaces where there needs to be black
		for(byte yAxis =0; yAxis<=2;yAxis++) {
			for(byte xAxis =0;xAxis<=7;xAxis++) {
				//Position the pieces correctly
				if((xAxis%2==0) && (yAxis!=1)) {
					gameBoard[yAxis][xAxis] = checkerColour.RED;
				}
				else if((xAxis!=8)&&(xAxis%2==0)) {
					gameBoard[yAxis][xAxis+1]= checkerColour.RED;
				}
			}
		}
		//Put in the spaces where there need to be blacks
		for(byte yAxis =5; yAxis<=7;yAxis++) {
			for(byte xAxis =0;xAxis<=7;xAxis++) {
				if((xAxis!=7)&&(xAxis%2==0)&&(yAxis!=6)) {
					gameBoard[yAxis][xAxis+1]= checkerColour.BLACK;
				}
				else if((xAxis%2==0)) {
					gameBoard[yAxis][xAxis] = checkerColour.BLACK;
				}
			}
		}
		
		//Initialize the spaces where there are no pawns
		for(byte yAxis =0; yAxis<=7;yAxis++) {
			for(byte xAxis =0;xAxis<=7;xAxis++) {
				if(gameBoard[yAxis][xAxis]==null) {
					gameBoard[yAxis][xAxis] = checkerColour.NONE;
				}
			}
		}
	}

}