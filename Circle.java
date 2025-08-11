import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Circle extends Shape 
{
  /*
  Constructor initializing a Circle object with an x position, y position, empty status, size, and colour (uses the superclass 
  Shape's constructor to avoid code duplication).
  */
  public Circle(int x, int y, boolean empty, int size, Color colour) 
  {
    super(x, y, empty, size, colour);
  }

  /*
  Implementation of superclass Shape's inherited abstract draw method.

  This method draws a circle at the specified (x,y) position on the panel (from its top-left corner) with the given size and colour.
  A small circle will have diameter 30, a medium circle will have diameter 70 and large circle will have diameter 110.
  If the empty status is true, it will draw an outline of the circle otherwise it will draw a filled circle.
  */
  public void draw(Graphics g)
  {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(colour);
    
    int diameter = 30;
    if (size == 2) {
      diameter = 70;
    }
    else if (size == 3) {
      diameter = 110;
    }        

    if (empty) {
      g2d.drawOval(x, y, diameter, diameter);
    } 
    else {
      g2d.fillOval(x, y, diameter, diameter);
    }
  }
}