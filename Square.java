import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Square extends Shape
{
  /*
  Constructor initializing a Square object with an x position, y position, empty status, size, and colour (uses the superclass 
  Shape's constructor to avoid code duplication).
  */
  public Square(int x, int y, boolean empty, int size, Color colour) 
  {
    super(x, y, empty, size, colour);
  }
    
  /*
  Implementation of superclass Shape's inherited abstract draw method.

  This method draws a square at the specified (x,y) position on the panel (from its top-left corner) with the given size and colour.
  A small square will have size 30x30, a medium square will be 70x70 and large square will be 110x110.
  If the empty status is true, it will draw an outline of the square otherwise it will draw a filled square.
  */
  public void draw(Graphics g)
  {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(colour);
    
    int width = 30;
    if (size == 2) {
      width = 70;
    }
    else if (size == 3) {
      width = 110;
    }        

    if (empty) {
      g2d.drawRect(x, y, width, width);
    } 
    else {
      g2d.fillRect(x, y, width, width);
    }
  }
}
