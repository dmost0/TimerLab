//********************************************************************
//  ReboundPanel.java     
//  Represents the primary panel for the Rebound program.
//********************************************************************

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class ReboundPanel extends JPanel
{
   private final int WIDTH = 300, HEIGHT = 100;
   private final int DELAY = 20, IMAGE_SIZE = 35;

   private ImageIcon image;
   private ImageIcon red;
   private ImageIcon yellow;
   private Timer timer;
   private int x, y, moveX, moveY;
  
   class mouseListener extends MouseAdapter
   {
	   public void mousePressed(MouseEvent e){
		   int currentX = e.getX();
		   int currentY = e.getY();
		   if(Math.abs(x - currentX) <= 30 && Math.abs(y - currentY) <= 30){
			   if(image.getDescription().equals("0")){
				   image.setImage(red.getImage());
				   image.setDescription("1");
			   }
			   else{
				   image.setImage(yellow.getImage());
				   image.setDescription("0");
			   }
			   
			 
		   }
	   }
   }
   //-----------------------------------------------------------------
   //  Sets up the panel, including the timer for the animation.
   //-----------------------------------------------------------------
   public ReboundPanel()
   {
	  mouseListener m = new mouseListener();
      timer = new Timer(DELAY, new ReboundListener());
      super.addMouseListener(m);
      image = new ImageIcon ("happyFace.gif");
      image.setDescription("0");
      red = new ImageIcon("redSmiley.gif");
      yellow = new ImageIcon("happyFace.gif");
      x = 0;
      y = 40;
      moveX = moveY = 3;

      setPreferredSize (new Dimension(WIDTH, HEIGHT));
      setBackground (Color.black);
      timer.start();
   }

   //-----------------------------------------------------------------
   //  Draws the image in the current location.
   //-----------------------------------------------------------------
   public void paintComponent (Graphics page)
   {
      super.paintComponent (page);
      image.paintIcon (this, page, x, y);
   }

   //*****************************************************************
   //  Represents the action listener for the timer.
   //*****************************************************************
   private class ReboundListener implements ActionListener
   {
      //--------------------------------------------------------------
      //  Updates the position of the image and possibly the direction
      //  of movement whenever the timer fires an action event.
      //--------------------------------------------------------------
      public void actionPerformed (ActionEvent event)
      {
         x += moveX;
         y += moveY;

         if (x <= 0 || x >= WIDTH-IMAGE_SIZE)
            moveX = moveX * -1;

         if (y <= 0 || y >= HEIGHT-IMAGE_SIZE)
            moveY = moveY * -1;
    
         repaint();
      }
   }
   
   
}
