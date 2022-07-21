import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;
import java.lang.Object;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GameController extends JFrame
{
  public int players, currX, currY, steps, prevX, prevY, currentPlayer, stackHeight, stackBottomPiece;
  private JPanel topPanel, bottomPanel, stackDisplay;
  private JLabel topLabel, stackDisplayLabel;
  private JButton saveButton, newGameButton;
  private String difficulty, color;
  public GameTile [][] gameTile;
  public Player activePlayer,player1,player2,player3,player4;
  public boolean selected;

  ArrayList<Integer> xlist = new ArrayList<Integer>();
  ArrayList<Integer> ylist = new ArrayList<Integer>();
  ArrayList<Player> activePlayers = new ArrayList<Player>();
  ArrayList<GameTile> potentialMoves = new ArrayList<GameTile>();


  public Color playerColor[]; //An array that stores the four colors for the four players
  public String playerColorAsString[]; //An array that stores the four colors for the four players


  public GameController(int players, String difficulty, String color)
  {
    this.players = players;
    this.difficulty = difficulty;
    this.color = color;
    this.setSize(700,700);
    this.setTitle("Focus!");
    this.setLocationRelativeTo(null);

    topPanel = new JPanel();
    topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.PAGE_AXIS));

    saveButton = new JButton("Save Game");
    newGameButton = new JButton("New Game");

    bottomPanel = new JPanel();
    bottomPanel.setLayout(new GridLayout(10,10));

    topLabel = new JLabel("Let's play Focus! Player x's turn.\n");
    topLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    topPanel.add(saveButton);
    //topPanel.add(newGameButton);
    saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    topPanel.add(topLabel);


    stackDisplay = new JPanel();
    stackDisplay.setLayout(new BoxLayout(stackDisplay,BoxLayout.PAGE_AXIS));
    stackDisplayLabel = new JLabel("");

    Color playerColor[] = new Color[4];
    String playerColorAsString[] = new String[4];
    /**
     * We have 3 color schemes and when the user picks one,
     * the choice is stored in variable color
     * given that choice, I store all four colors for the four
     * players in an array playerColor[]
     */
    if (color == "1") {
      playerColor[0] = Color.red;
      playerColor[1] = Color.green;
      playerColor[2] = Color.blue;
      playerColor[3] = Color.yellow;
    }
    else if (color == "2") {
      playerColor[0] = Color.yellow;
      playerColor[1] = new Color(255,102,0); //color orange;
      playerColor[2] = Color.blue;
      playerColor[3] = new Color(102,0,153); //color purple
    }
    else if (color == "3") {
      playerColor[0] = Color.orange; //using the lighter orange because it is so much easier to see in this color scheme than the darker one i used above
      playerColor[1] = new Color(255, 0, 255); //color fuchsia
      playerColor[2] = new Color(102,0,153); //color purple
      playerColor[3] = Color.black;
    }

    //ArrayList<Human> humanPlayers = new ArrayList<Human>();
    //ArrayList<CPU> cpuPlayers = new ArrayList<CPU>();

    Player player1 = new Human(playerColor[0],0,"Player 1");
    Player player2 = new Human(playerColor[1],1,"Player 2");
    Player player3 = new Human(playerColor[2],2,"Player 3");
    Player player4 = new Human(playerColor[3],3,"Player 4");

    activePlayers.add(player1);
    activePlayers.add(player2);
    activePlayers.add(player3);
    activePlayers.add(player4);
    activePlayer=activePlayers.get(0);

    /*for(int i=0; i<4; i++) {
      if (i < players) {
        humanPlayers.add(new Human(playerColor[i], i));
      }
      else if (i >= players) {
        cpuPlayers.add(new CPU(playerColor[i], i, difficulty));
      }
    }*/

    gameTile = new GameTile [10][10];
    for (int y=0; y<10; y++)
    {
      for (int x=0; x<10; x++)
      {
        gameTile[x][y] = new GameTile(x,y);
        gameTile[x][y].setSize(30,30);
        gameTile[x][y].setBorder(BorderFactory.createLineBorder(Color.darkGray));
        gameTile[x][y].addActionListener(ma);
        bottomPanel.add(gameTile[x][y]);

        if ((y==5 & (x==1|x==3|x==5|x==6|x==7|x==8)) | (y==6 & (x==1|x==3)) | (y==7 & (x==3|x==5|x==6|x==7)) | (y==8 & x==3)) {
          Piece piece = new Piece(player2,playerColor[1],x,y);
          gameTile[x][y].addPiece(piece);
        }
        else if ((x==5 & (y==1|y==2|y==3|y==4|y==6|y==8)) | (x==6 & (y==6|y==8)) | (x==7 & (y==2|y==3|y==4|y==6)) | (x==8 & y==6)) {
          Piece piece = new Piece(player1,playerColor[0],x,y);
          gameTile[x][y].addPiece(piece);
        }
        else if ((x==1 & y==3) | (x==2 & (y==3|y==5|y==6|y==7)) | (x==3 & (y==1|y==3)) | (x==4 & (y==1|y==3|y==5|y==6|y==7|y==8))) {
          Piece piece = new Piece(player3,playerColor[2],x,y);
          gameTile[x][y].addPiece(piece);
        }
        else if ((y==1 & x==6) | (y==2 & (x==2|x==3|x==4|x==6)) | (y==3 & (x==6|x==8)) | (y==4 & (x==1|x==2|x==3|x==4|x==6|x==8))) {
          Piece piece = new Piece(player4,playerColor[3],x,y);
          gameTile[x][y].addPiece(piece);
        }
      }
    }


    //assembling the frame and adding panels
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(topPanel, BorderLayout.NORTH);
    getContentPane().add(bottomPanel, BorderLayout.CENTER);

    //setting the frame's behaviour and making it visible
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);

    playGame();
  }

  public void setColourScheme () {
      Color playerColor[] = new Color[4];
      if (color == "1") {
        playerColor[0] = Color.red;
        playerColor[1] = Color.green;
        playerColor[2] = Color.blue;
        playerColor[3] = Color.yellow;
      }
      else if (color == "2") {
        playerColor[0] = Color.yellow;
        playerColor[1] = new Color(255,102,0); //color orange;
        playerColor[2] = Color.blue;
        playerColor[3] = new Color(102,0,153); //color purple
      }
      else if (color == "3") {
        playerColor[0] = Color.orange; //using the lighter orange because it is so much easier to see in this color scheme than the darker one i used above
        playerColor[1] = new Color(255, 0, 255); //color fuchsia
        playerColor[2] = new Color(102,0,153); //color purple
        playerColor[3] = Color.black;
      }
  }




  //move testing action listener
  ActionListener ma = new ActionListener()
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      Object selection = e.getSource();
      if(selection.equals(saveButton))
      {
        //CODE TO SAVE GAME
        //new saveGame();
        String filename = "file.ser";

        try {
          FileOutputStream file = new FileOutputStream(filename);
          ObjectOutputStream out = new ObjectOutputStream(file);

          saveGame.saveToFile(out);
          file.close();
          System.out.println("Object has been serialized");
        }
        catch(IOException ex) {
          System.out.println("IOException has been cuaght");
        }
      }
      if(selection.equals(newGameButton))
      {
        //CODE TO START NEW GAME
      }
      if(selection instanceof GameTile)
      {
        GameTile clicked = (GameTile) selection;
        if(!selected)
        {
          selectPiece(clicked);
        }

        else
        {
          currX = clicked.getXCoord();
          currY = clicked.getYCoord();
          if(!(potentialMoves.contains(clicked)))
          {
            deselectPiece();
          }

          else
          {
            movePiece();
          }
        }
      }
    }
  };

  public void playGame()
  {
    currentPlayer = 0;
    takeTurn();
  }

  public void takeTurn()
  {
    if(activePlayers.size()>1)
    {
      if(currentPlayer>activePlayers.size()-1)
      {
        currentPlayer=0;
      }
      activePlayer=activePlayers.get(currentPlayer);
      topLabel.setText(activePlayer.getPlayerName()+"'s turn! Double click the piece you'd like to move.");
      topLabel.setForeground(activePlayer.getColor());
      boolean selected = false;
    }
    else
    {
      //game over
    }
  }

  public void selectPiece(GameTile clicked)
  {
    clicked=clicked;
    if(clicked.hasPiece())
    {
      if(clicked.getTopPiecePlayer() == activePlayer)
      {
        currX = clicked.getXCoord();
        currY = clicked.getYCoord();

        clicked.setBorder(BorderFactory.createLoweredBevelBorder());
        steps = clicked.getPieceHeight();
        for(int i=1; i<=steps; i++)
        {
          if(((currX+i)>=0)&&((currX+i)<=10))
          {
            if(gameTile[currX+i][currY].hasPiece())
            {
              gameTile[currX+i][currY].setBackground(gameTile[currX+i][currY].getTopPieceColor().darker());
              potentialMoves.add(gameTile[currX+i][currY]);
            }
            potentialMoves.add(gameTile[currX+i][currY]);
          }

          if(((currX-i)>=0)&&((currX-i)<=10))
          {
            if(gameTile[currX-i][currY].hasPiece())
            {
              gameTile[currX-i][currY].setBackground(gameTile[currX-i][currY].getTopPieceColor().darker());
            }
            potentialMoves.add(gameTile[currX-i][currY]);
          }

          if(((currY+i)>=0)&&((currY+i)<=10))
          {
            if(gameTile[currX][currY+i].hasPiece())
            {
              gameTile[currX][currY+i].setBackground(gameTile[currX][currY+i].getTopPieceColor().darker());
            }
            potentialMoves.add(gameTile[currX][currY+i]);
          }

          if(((currY-i)>=0)&&((currY-i)<=10))
          {
            if(gameTile[currX][currY-i].hasPiece())
            {
              gameTile[currX][currY-i].setBackground(gameTile[currX][currY-i].getTopPieceColor().darker());
            }
            potentialMoves.add(gameTile[currX][currY-i]);
          }
        }


        topLabel.setText("button pressed: " + currX + ", "+ currY+". Choose where to move this piece. Piece height: "+steps);
        selected=true;
        prevX = currX;
        prevY = currY;
      }
    }
  }

  public void movePiece()
  {
    if(gameTile[prevX][prevY].getPieceHeight()>1)
    {
      moveStack();
    }
    else
    {
      topLabel.setText("Moved the piece from "+prevX+", "+prevY+" to "+currX+", "+currY);
      gameTile[currX][currY].addPiece(gameTile[prevX][prevY].removePiece());
      for (int y=0; y<10; y++)
      {
        for (int x=0; x<10; x++)
        {
          gameTile[x][y].setBorder(BorderFactory.createLineBorder(Color.darkGray));
          if(gameTile[x][y].hasPiece())
          {
            gameTile[x][y].setBackground(gameTile[x][y].getTopPieceColor());
          }
          else
          {
            gameTile[x][y].setBackground(Color.white);
          }
        }
      }
      potentialMoves.clear();
      currentPlayer++;
      takeTurn();
    }
  }

  public void deselectPiece()
  {
    for (int y=0; y<10; y++)
    {
      for (int x=0; x<10; x++)
      {
        gameTile[x][y].setBorder(BorderFactory.createLineBorder(Color.darkGray));
        if(gameTile[x][y].hasPiece())
        {
          gameTile[x][y].setBackground(gameTile[x][y].getTopPieceColor());
        }
        else
        {
          gameTile[x][y].setBackground(Color.white);
        }
        potentialMoves.clear();
        selected=false;
      }
    }
  }

  public void moveStack()
  {
    /*int stackHeight = gameTile[prevX][prevY].getPieceHeight();
    stackDisplayLabel.setText("Choose where to separate the stack:");
    for(int x=0; x<stackHeight; x++)
    {
      Square square = new Square(gameTile[prevX][prevY].getPieceAt(x).getColor());
      square.setSize(30,30);
      square.setBackground(gameTile[prevX][prevY].getPieceAt(x).getColor());
      stackDisplay.add(square);
    }
    String[] stackDisplayOptions = {"1","2","3"};
    int stackBottomPiece = (int)JOptionPane.showInputDialog(null,stackDisplay,"Separate Stack",JOptionPane.PLAIN_MESSAGE,null,stackDisplayOptions,"1");*/
  }
}
