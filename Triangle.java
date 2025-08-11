import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Triangle extends Shape
{
  /*
  Constructor initializing a Triangle object with an x position, y position, empty status, size, and colour (uses the superclass 
  Shape's constructor to avoid code duplication).
  */
  public Triangle(int x, int y, boolean empty, int size, Color colour) 
  {
    super(x, y, empty, size, colour);
  }

  /*
  Implementation of superclass Shape's inherited abstract draw method.

  This method draws a triangle (pointing upward from its base) centered at the top vertex at 
  the specified (x,y) position on the panel with the given size and colour. A small triangle will have a base 
  width of 50 and height of 35, a medium triangle will have a base width of 110 and height of 65 and large 
  triangle have a base width of 170 and height of 95. If the empty status is true, it will draw an outline of 
  the triangle otherwise it will draw a filled triangle.
  */
  public void draw(Graphics g){
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(colour);
    
    int[] a = {x, x-25, x+25};
    int[] b = {y, y+35, y+35};
    int c = 3;

    if (size == 2) {
      a = new int[] {x, x-55, x+55};
      b = new int[] {y, y+65, y+65};
    }
    else if (size == 3) {
      a = new int[] {x, x-85, x+85};
      b = new int[] {y, y+95, y+95};
    }        

    if (empty) {
      g2d.drawPolygon(a, b, c); //used to make 3 lines into a triangle shape 
    } 
    else {
      g2d.fillPolygon(a, b, c);
    }
  }
}