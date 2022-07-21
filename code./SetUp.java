import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.Serializable;
import java.lang.*;
import java.lang.Object;
import java.util.ArrayList;
import java.awt.Color;

public class SetUp extends JFrame implements Serializable
{
  JPanel confirmWindow, colourSchemeWindow;
  JLabel confirmText, colourSchemeText, colourSchemePreview, colourScheme1, colourScheme2, colourScheme3;
  int numOfPlayers;
  String cpuDifficulty, colourScheme;

  public SetUp()
  { 
    numOfPlayers = getNumberOfPlayers();
    cpuDifficulty = getDifficultyLevel();
    colourScheme = getColourScheme();
    getConfirmation();
  }

  public int getNumberOfPlayers()
  {
    String[] howManyPlayers = {"1","2","3","4"};
    String numOfPlayersAsString = (String)JOptionPane.showInputDialog(null,"Select Number Of Human Players","Game Set-Up",JOptionPane.PLAIN_MESSAGE,null,howManyPlayers,"1");
    int numOfPlayers=Integer.parseInt(numOfPlayersAsString);
    return numOfPlayers;
  }

  public String getDifficultyLevel()
  {
    String[] easyOrHard = {"Easy","Hard"};
    String cpuDifficulty = (String)JOptionPane.showInputDialog(null,"Select Computer Difficulty","Game Set-Up",JOptionPane.PLAIN_MESSAGE,null,easyOrHard,"Easy");
    return cpuDifficulty;
  }

  public String getColourScheme()
  {
    /* A NOTE: I eventually want to find a better way to display the colour schemes, either through uploading an image or drawing circles in java. */
    colourSchemeWindow = new JPanel();
    colourSchemeWindow.setLayout(new BoxLayout(colourSchemeWindow,BoxLayout.PAGE_AXIS));
    colourSchemeText = new JLabel("Select A Colour Scheme For Your Game!");
    colourScheme1 = new JLabel("<html>1: <font-size:30px><font color='red'>●</font><font color='green'>●</font><font color='blue'>●</font><font color='yellow'>●</font></html></font>");
    colourScheme2 = new JLabel("<html>2: <font color='yellow'>●</font><font color='orange'>●</font><font color='blue'>●</font><font color='purple'>●</font></html>");
    colourScheme3 = new JLabel("<html>3: <font color='orange'>●</font><font color='fuchsia'>●</font><font color='purple'>●</font><font color='black'>●</font></html>");

    colourScheme1.setFont(new Font("Arial",Font.PLAIN,30));
    colourScheme2.setFont(new Font("Arial",Font.PLAIN,30));
    colourScheme3.setFont(new Font("Arial",Font.PLAIN,30));

    colourSchemeWindow.add(colourSchemeText);
    colourSchemeWindow.add(colourScheme1);
    colourSchemeWindow.add(colourScheme2);
    colourSchemeWindow.add(colourScheme3);
    String[] colourSchemeOptions = {"1","2","3"};
    String colourScheme = (String)JOptionPane.showInputDialog(null,colourSchemeWindow,"Game Set-Up",JOptionPane.PLAIN_MESSAGE,null,colourSchemeOptions,"1");
    return colourScheme;
  }

  public void getConfirmation()
  {
    confirmWindow = new JPanel();
    confirmWindow.setLayout(new BoxLayout(confirmWindow,BoxLayout.PAGE_AXIS));
    confirmText = new JLabel("You have selected "+numOfPlayers+" human players,"+(4-numOfPlayers)+" CPU players and a "+cpuDifficulty+" difficulty level. Is this correct?");

    confirmWindow.add(confirmText);

    String[] confirmOrGoBack = {"Yes, Start Game!","No, Go Back."};
    int result = JOptionPane.showOptionDialog(null, confirmWindow, "Game Set-Up",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,null, confirmOrGoBack, null);
    if (result == JOptionPane.YES_OPTION)
    {
      startGame();
    }
  }

  public void startGame()
  {
    GameController newGame = new GameController(numOfPlayers,cpuDifficulty, colourScheme);
  }
}
