import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.border.EmptyBorder;
import java.lang.*;
import java.awt.Color;
import java.util.ArrayList;


public class Square extends JButton
{
  private Color color;

  public Square(Color color)
	{
    this.setSize(50,50);
    this.setBackground(color);
    this.setVisible(true);
    this.setOpaque(true);
	}
}
