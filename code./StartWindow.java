import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;
import java.lang.*;
import java.lang.Object;

public class StartWindow extends JFrame implements ActionListener, Serializable
{

  //instance variables here
  public JPanel mainPanel, menuPanel, instructionPanel;
  public JLabel welcomeLabel, instructionLabel, instructionTitle;
  public JButton startNew, loadGame, howToPlay;

  public StartWindow()
  {

    this.setSize(400,500);
    this.setTitle("Focus!");
    this.setLocationRelativeTo(null);

    // Creating the first panel, containing the menu with "Start New Game" or "Load Saved Game" options, as well as the option for players to be shown the rules
    menuPanel = new JPanel();
    menuPanel.setLayout(new BoxLayout(menuPanel,BoxLayout.PAGE_AXIS));

    welcomeLabel = new JLabel("Welcome to the game of Focus! Ready to play?");
    startNew = new JButton("Start New Game");
    loadGame = new JButton("Load Saved Game");
    howToPlay = new JButton("How To Play");

    welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
    startNew.setAlignmentX(CENTER_ALIGNMENT);
    loadGame.setAlignmentX(CENTER_ALIGNMENT);
    howToPlay.setAlignmentX(CENTER_ALIGNMENT);

    startNew.addActionListener(this);
    loadGame.addActionListener(this);
    howToPlay.addActionListener(this);

    menuPanel.add(Box.createRigidArea(new Dimension(0,140)));
    menuPanel.add(welcomeLabel);
    menuPanel.add(Box.createRigidArea(new Dimension(0,30)));
    menuPanel.add(startNew);
    menuPanel.add(loadGame);
    menuPanel.add(howToPlay);

    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(menuPanel, BorderLayout.CENTER);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
  }

  // for MouseListener
  public void mouseEntered(MouseEvent arg0){}
  public void mouseExited(MouseEvent arg0) {}
  public void mousePressed(MouseEvent arg0) {}
  public void mouseReleased(MouseEvent arg0) {}


  // actionEvent for the three menu buttons
  public void actionPerformed(ActionEvent aevt)
  {
    Object selected = aevt.getSource();

    if(selected.equals(startNew))
    {
      startNew();
    }

    else if(selected.equals(loadGame))
    {
      try {
        saveGame.readFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    else if(selected.equals(howToPlay))
    {
      showInstructions();
    }
  }

  // Start the Set Up process for a new game
  public void startNew()
  {
    SetUp setUpNewGame = new SetUp();
  }

  // Load a previously saved game
  public void loadGame()
  {
    /* Need to code this part!!! I'm figuring we can make it so that games will be saved as "FOCUSGAMESAVE-#" or
       something. For the time being we can probably write a code to check whether the folder has any saved files
       that start with "FOCUSGAMESAVE". If it doesn't (which ours won't at this point), it'll display an error
       saying there are no saved games that can be loaded */
  }


  // Show players the game instructions in a popup frame
  public void showInstructions()
  {
    instructionPanel = new JPanel();
    instructionPanel.setLayout(new BoxLayout(instructionPanel,BoxLayout.PAGE_AXIS));

    instructionTitle = new JLabel("How to play:");
    instructionLabel = new JLabel("Game instructions will go here, in case any players don't know the rules of the game.");

    instructionPanel.add(Box.createRigidArea(new Dimension(0,40)));
    instructionPanel.add(instructionTitle);
    instructionPanel.add(Box.createRigidArea(new Dimension(0,30)));
    instructionPanel.add(instructionLabel);
    instructionPanel.add(Box.createRigidArea(new Dimension(0,30)));

    Object[] options = { "Back!" };
    JOptionPane.showOptionDialog(null,instructionPanel, "How To Play",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, options, options[0]);
  }

}
