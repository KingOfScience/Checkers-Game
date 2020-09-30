package game;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class CheckersMain{
	//Prints the game board
	//Class variables
	private static String startingPoint;
	private static String destination;
	private static boolean NoWinner = false;
	static JFrame gui = new JFrame();
	
	public static final void main(String[] args) throws IOException{
		/*This program makes an image from two images, and makes a game out of it
		 */
		
		//Make the images once
		final var boardImage = ImageIO.read(new File("C:\\Users\\Key\\Desktop\\Checkers\\checkerboard.png")).getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        final var redPawn =    ImageIO.read(new File("C:\\Users\\Key\\Desktop\\Checkers\\Red Checkered.png")).getScaledInstance(200, 100,Image.SCALE_SMOOTH);
        final var blackPawn =  ImageIO.read(new File("C:\\Users\\Key\\Desktop\\Checkers\\Black Checkered.png")).getScaledInstance(100, 100,Image.SCALE_SMOOTH);
        final var blackKing =  ImageIO.read(new File("C:\\Users\\Key\\Desktop\\Checkers\\blackKing.png")).getScaledInstance(100, 100,Image.SCALE_SMOOTH);
        final var redKing =    ImageIO.read(new File("C:\\Users\\Key\\Desktop\\Checkers\\redKing.png")).getScaledInstance(100, 100,Image.SCALE_SMOOTH);
        
		//Make a board object
		final var board = new Board();
		
		//make a player object
		final var player = new Player();
		
		//Set the parameters of the JFrame
        gui.setSize(800,800);
        gui.setResizable(true);
		gui.setVisible(true);
		
		//While there are no winners, continue that game
		while(NoWinner == false) {
			try {
				//Make a printer and print the board
				Printer.print(board.gameBoard, gui, boardImage,redPawn,blackPawn,blackKing,redKing);

				//Refresh the board
				gui.getContentPane().revalidate();
				gui.getContentPane().repaint();

				//Get the starting point
				System.out.println("Now choose a starting pawn");
				startingPoint = player.getMove();
				
				//get destination
				System.out.println("Now Choose destination");
				destination = player.getMove();
				
				//move the piece now
				player.movePiece(startingPoint,destination,board);
				
				//Check if game is won
				NoWinner = player.gameWinCondition(board);
				
				//Check if there needs to be kings
				player.kingMe(board);
				
				//Destroy the current image in the frame
				gui.getContentPane().removeAll();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
