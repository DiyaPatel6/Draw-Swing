import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape
{
  public int x;
  public int y;
  public boolean empty; //true for empty shape (just the outline appears) or false for solid filled shape
  public int size; // 1 for small, 2 for medium, 3 for large
  public Color colour;

  /*
  Constructor initializing a Shape object with an x position on the panel, y position on the panel, empty status, size, and colour
  */
  public Shape(int x, int y, boolean empty, int size, Color colour)
  {
    this.x = x;
    this.y = y;
    this.empty = empty;
    this.size = size;
    this.colour = colour;
  }

  /*
  Abstract method for drawing the shape using Graphics. This is abstract because what it means to draw for each shape is very different.
  */
  abstract void draw(Graphics g);
}
