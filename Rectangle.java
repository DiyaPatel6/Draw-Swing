import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rectangle extends Shape
{
  /*
  Constructor initializing a Rectangle object with an x position, y position, empty status, size, and colour (uses the superclass 
  Shape's constructor to avoid code duplication).
  */
  public Rectangle(int x, int y, boolean empty, int size, Color colour) 
  {
    super(x, y, empty, size, colour);
  }

  /*
  Implementation of superclass Shape's inherited abstract draw method.

  This method draws a rectangle at the specified (x,y) position on the panel (from its top-left corner) with the given size and colour.
  A small rectangle will have size 70x30, a medium rectangle will be 130x70 and large rectangle will be 180x110.
  If the empty status is true, it will draw an outline of the rectangle otherwise it will draw a filled rectangle.
  */
  public void draw(Graphics g){
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(colour);
    
    int width = 70;
    int height = 30;
    if (size == 2) {
      width = 130;
      height = 70;
    }
    else if (size == 3) {
      width = 180;
      height = 110;
    }        

    if (empty) {
      g2d.drawRect(x, y, width, height);
    } 
    else {
      g2d.fillRect(x, y, width, height);
    }
  }
}