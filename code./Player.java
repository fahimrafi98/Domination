import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;
import java.lang.Object;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player
{
  private String name; //should we let users input their names or just leave as "player1" "player2" etc.?
  private Color color;
  private int turnOrder;
  private int numOfPieces = 13; //we'll need to keep track of how many pieces a player has in play, so we'll be able to determine if they're out or not
  private boolean stillPlaying; //to determine if a player is out or not

  ArrayList<Piece> capturedPieces = new ArrayList<Piece>();

  public Player(Color color, int order, String name)
  {
    this.color = color;
    this.turnOrder = order;
    this.name = name;
    this.stillPlaying = true;
  }

  public Color getColor() {
    return color;
  }

  public int getOrder() {
    return turnOrder;
  }

  public String getPlayerName()
  {
    return name;
  }

  public void removePiece()
  {
    numOfPieces = numOfPieces - 1;
  }

  public boolean checkPlayingStatus()
  {
    if(numOfPieces==0)
    {
      return false;
    }
    else
    {
      return true;
    }
  }

/* Possible other methods to include:

  public void takeTurn()
  // This will be called from "GameController", but we'll have to figure out if the code for the actual turn taking (ie. determining how many spaces the
  // player can move, where they can move, moving the actual piece, etc.) should go here or in GameController.
  {

  }

  public void gameLost()
  {
    if (numOfPieces==0)
    {
      stillPlaying==false;
    }
  } */

}
