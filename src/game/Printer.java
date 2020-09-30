package game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Printer {

	protected static void print(checkerColour boardPieces[][], JFrame gui,Image board, Image redPawn, Image blackPawn,Image blackKing,Image redKing) throws IOException{
        //Get the dimensions 
        final int w = board.getWidth(null);
        final int h = board.getHeight(null);

        //Make the final image
        final var finalImage = new BufferedImage(w, h,
            BufferedImage.TYPE_INT_RGB);
        var g = finalImage.createGraphics();
        g.drawImage(board, 0, 0, null);
        
        //Its a 800 by 800 board, offset bet x-40 and y+10
        //Draw the board
        for(byte x=0;x!=8;x++) {
        	for(byte y=0;y!=8;y++) {
        		if(boardPieces[y][x]==checkerColour.RED) {
			        g.drawImage(redPawn, (x*100)-50, (y*100)+10, null);
        		}
        		else if(boardPieces[y][x]==checkerColour.BLACK) {
        			g.drawImage(blackPawn, (x*100), (y*100), null);
        		}
        		else if(boardPieces[y][x]==checkerColour.REDKING) {
        			g.drawImage(redKing, (x*100), (y*100), null);
        		}
        		else if(boardPieces[y][x]==checkerColour.BLACKKING) {
        			g.drawImage(blackKing, (x*100), (y*100), null);
        		}
        	}
        }
        g.dispose();
        
        var r = new Runnable() {
          @Override
          public void run() {
        	  var image = new ImageIcon(finalImage);
        	  var lastImage = new JLabel(image);
        	  gui.add(lastImage);
          }
        };
        SwingUtilities.invokeLater(r);
    }
}
