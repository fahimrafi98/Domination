import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.border.EmptyBorder;
import java.lang.*;
import java.awt.Color;
import java.util.ArrayList;


public class GameTile extends JButton
{
  private int xcoord, ycoord, numOfPieces;
  private String state;
  private int currX = 0;
  private int currY = 0;
  private boolean hasPiece;
  private Piece removedPiece;

  public GameTile(int xcoord, int ycoord)
	{

    String state = "gameSquare";
    this.setSize(50,50);
		this.xcoord = xcoord;
		this.ycoord = ycoord;
    this.setBackground(Color.white);
    numOfPieces=0;
    boolean hasPiece;
    this.setVisible(true);
    this.setOpaque(true);

    /*this code is to set up the initial board, assigning "states" to the tiles that determine if it's filler (ie. no pieces can be placed there,
     "reserveSquare" where we can show any pieces a player has reserved, or "playSquare" which are the tiles in play
     probably need to add some sort of "getState" method*/

    if (ycoord==0||ycoord==9||xcoord==0||xcoord==9){
      state = "reserveSquare";
      this.setBackground(Color.gray);
      hasPiece = false;
      this.setVisible(false);
    }
    else if ((ycoord==1)||(ycoord==8))
    {
      if((xcoord==1)||(xcoord==2)||(xcoord==7)||(xcoord==8))
      {
        state = "fillerSquare";
        hasPiece = false;
        this.setBackground(Color.black);
        this.setVisible(false);
      }
    }
    else if ((ycoord==2 | ycoord==7) & (xcoord==1 |xcoord==8))
    {
      state = "fillerSquare";
      hasPiece = false;
      this.setBackground(Color.white);
      this.setVisible(false);
    }
	}

  ArrayList<Piece> piecesOnTile = new ArrayList<Piece>();

  public String getState()
  {
    return state;
  }

  public int getXCoord()
  {
    return xcoord;
  }

  public int getYCoord()
  {
    return ycoord;
  }

  public int getPieceHeight()
  {
    return numOfPieces;
  }

  public boolean hasPiece()
  {
    if(numOfPieces!=0)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  public Piece getPieceAt(int index)
  {
    return piecesOnTile.get(index);
  }

  public Color getTopPieceColor()
  {
    return piecesOnTile.get(piecesOnTile.size()-1).getColor();
  }

  public Player getTopPiecePlayer()
  {
    return piecesOnTile.get(piecesOnTile.size()-1).getPlayer();
  }

  public String getTopPiecePlayerName()
  {
    return piecesOnTile.get(piecesOnTile.size()-1).getPlayerName();
  }

  public void addPiece(Piece piece)
  {
    this.setBackground(piece.getColor());
    piecesOnTile.add(piece);
    hasPiece=true;
    numOfPieces++;
  }

  public Piece removePiece()
  {
    numOfPieces--;
    Piece removedPiece = piecesOnTile.get(piecesOnTile.size()-1);
    piecesOnTile.remove(piecesOnTile.size()-1);
    if(numOfPieces==0)
    {
      this.setBackground(Color.white);
    }
    else
    {
      this.setBackground(piecesOnTile.get(piecesOnTile.size()-1).getColor());
    }
    return removedPiece;
  }
}
