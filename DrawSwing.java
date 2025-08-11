import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class DrawSwing extends JPanel{
  private JFrame frame;
  private JLabel heading;
  private JLabel subheading;
  private JTextArea instructions;
  private JButton clearPanel;
  private Color defaultColour = Color.BLUE;
  private ArrayList<Shape> shapes = new ArrayList<>(); //to store all shapes on the panel
  private Shape previousShape = null; //keep track of the last shape added to the panel
  
  public DrawSwing(){
    //Sets up the overall frame of the program
    frame = new JFrame();
    frame.setSize(1465, 945);
    frame.getContentPane().setBackground(Color.WHITE); 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null);
  
    //Writes a header, saying "Draw Swing" at the top of the frame
    heading = new JLabel();
    heading.setText("Draw Swing");
    heading.setFont(new Font("Arial", Font.BOLD, 50));
    heading.setBounds(600, 10, 500, 60);

    //Writes a subheader below the header, saying "Draw your very own shape art!" at the top of the frame
    subheading = new JLabel();
    subheading.setText("Draw your very own shape art!");
    subheading.setFont(new Font("Arial", Font.BOLD, 20));
    subheading.setBounds(600, 70, 900, 30);

    //Writes multiple lines of instructions of how to use the program at the bottom of the frame
    instructions = new JTextArea(); //used so that multiple lines of text can be written (a regular label does not allow this)
    instructions.setText("Instructions\n - Click on a shape button to add it to the panel\n - Click the keyboard spacebar to change the colour of the last shape added\n - Click on up, down, left, right keyboard buttons to move around the last shape added\n - Click on the 'Clear Panel' button to remove all shapes from the panel");
    instructions.setFont(new Font("Arial", Font.BOLD, 15));
    instructions.setBounds(190, 810, 600, 100);
    instructions.setEditable(false);  //ensures the text can't be changed by the user

    //A button with "Clear Panel" written on it, which allows for the panel to be cleared of all contents/shapes
    clearPanel = new JButton();
    clearPanel.setText("Clear Panel");
    clearPanel.setFont(new Font("Arial", Font.BOLD, 20));
    clearPanel.setBounds(1000, 830, 160, 60);
    clearPanel.addActionListener(new ActionListener() //actually implements the ability to remove all contents from the panel when the button is clicked by overriding ActionListener's actionPerformed method
    {
      @Override
      public void actionPerformed(ActionEvent e) 
      {
        shapes.clear(); //clears the array of Shape objects
        repaint(); 
      }
      });

    //Sets up the dimesions and display of the panel that the shapes will be drawn to
    this.setBounds(180, 130, 1100, 670);
    this.setBackground(Color.WHITE);
    this.setBorder(LineBorder.createGrayLineBorder());
  
    this.addKeyListener(new KeyTracker()); // Adds the KeyListner to the panel, allowing for shapes in the panel to actually move and change colour
    this.setFocusable(true);  // Allows the panel to be focused
    this.requestFocusInWindow();  // Makes the panel focused and not the frame

    //creates ShapeButton objects with the specfied shape type as its text, at specified positions of the frame. (the ShapeButton class creates a common way of making all the buttons to avoid code duplication for each new button created)
    new ShapeButton(1295, 165, "Small Empty C");
    new ShapeButton(1295, 205, "Small Filled C");
    new ShapeButton(1295, 245, "Medium Empty C");
    new ShapeButton(1295, 285, "Medium Filled C");
    new ShapeButton(1295, 325, "Large Empty C");
    new ShapeButton(1295, 365, "Large Filled C");

    new ShapeButton(1295, 470, "Small Empty R");
    new ShapeButton(1295, 510, "Small Filled R");
    new ShapeButton(1295, 550, "Medium Empty R");
    new ShapeButton(1295, 590, "Medium Filled R");
    new ShapeButton(1295, 630, "Large Empty R");
    new ShapeButton(1295, 670, "Large Filled R");

    new ShapeButton(10, 165, "Small Empty S");
    new ShapeButton(10, 205, "Small Filled S");
    new ShapeButton(10, 245, "Medium Empty S");
    new ShapeButton(10, 285, "Medium Filled S");
    new ShapeButton(10, 325, "Large Empty S");
    new ShapeButton(10, 365, "Large Filled S");

    new ShapeButton(10, 470, "Small Empty T");
    new ShapeButton(10, 510, "Small Filled T");
    new ShapeButton(10, 550, "Medium Empty T");
    new ShapeButton(10, 590, "Medium Filled T");
    new ShapeButton(10, 630, "Large Empty T");
    new ShapeButton(10, 670, "Large Filled T");

    //creates ShapeTitleLabel objects with the specfied shape name, at specified positions of the frame. (the ShapeTitleLabel class creates a common way of making all the labels to avoid code duplication for each new label created)
    new ShapeTitleLabel(45, 130, "SQUARE");
    new ShapeTitleLabel(45, 435, "TRIANGLE");
    new ShapeTitleLabel(1335, 130, "CIRCLE");
    new ShapeTitleLabel(1325, 435, "RECTANGLE");

    frame.add(heading);
    frame.add(subheading);
    frame.add(instructions);
    frame.add(clearPanel);
    frame.add(this); //adds the JPanel to the JFrame)
    frame.setVisible(true); //allows for the frame to actually appear when the program is run
  }


  /*
  Overridden paintComponent method from Java's JPanel class. This method loops over the array of 
  Shape objects, "shapes", calling each shape's implemented draw method. The shapes in the "shapes" 
  array are the ones that the user specifically wanted to add by clicking the shape adding buttons.
  */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (Shape shape : shapes) 
    {
      shape.draw(g);
    }
  }


  /*
  Class for creating the 24 shape adding buttons (created a class for this to avoid duplicate formatting code for each button)
  */
  public class ShapeButton
  {
    /*
    Constructor initializing a ShapeButton object with an x position on the frame, y position on the frame, and text to be written on the button
    */
    public ShapeButton(int x, int y, String text)
    {   
      JButton button = new JButton();
      button.setText(text);
      button.setFont(new Font("Arial", Font.PLAIN, 13));
      button.setBounds(x, y, 155, 40);
      button.addActionListener(new ButtonListener(text)); //allows shapes to appear when buttons clicked
      frame.add(button);  
    } 


    /*
    ActionListener implementation for allowing the shape adding buttons to be functional when clicked
    */
    public class ButtonListener implements ActionListener
    {
      private String text;
      /*
      Constructor initializing the ButtonListener with the text that will appear on the specific button
      */
      public ButtonListener(String text) 
      {
        this.text = text;
      }

      /*
      Overridden ActionListener's actionPerformed method to allow a specific shape to be drawn when a specific button is clicked. 

      This method checks for specific text written on the shape adding buttons and depending on what the text says, it creates that 
      new shape using the specific shape classes. For example, if the text on the button is "Small Empty C", a new Circle object will 
      be created at the (30,30) position. It will also be added to the "shapes" array. As more shape adding buttons are clicked, 
      more shapes are added to the array, allowing all of them to be drawn when repaint() is called. There are 24 buttons each with its
      own unique text, corresponding to 24 unique shapes, hence why this method checks text.equals() 24 times.
      */
      @Override
      public void actionPerformed(ActionEvent e) 
      {
        if (text.equals("Small Empty C"))
        {
          previousShape = new Circle(30, 30, true, 1, defaultColour);
        }
        if (text.equals("Small Filled C"))
        {
          previousShape = new Circle(30, 30, false, 1, defaultColour);
        }
        if (text.equals("Medium Empty C"))
        {
          previousShape = new Circle(30, 30, true, 2, defaultColour);
        }
        if (text.equals("Medium Filled C"))
        {
          previousShape = new Circle(30, 30, false, 2, defaultColour);
        }
        if (text.equals("Large Empty C"))
        {
          previousShape = new Circle(30, 30, true, 3, defaultColour);
        }
        if (text.equals("Large Filled C"))
        {
          previousShape = new Circle(30, 30, false, 3, defaultColour);
        }
        if (text.equals("Small Empty S"))
        {
          previousShape = new Square(30, 30, true, 1, defaultColour);
        }
        if (text.equals("Small Filled S"))
        {
          previousShape = new Square(30, 30, false, 1, defaultColour);
        }
        if (text.equals("Medium Empty S"))
        {
          previousShape = new Square(30, 30, true, 2, defaultColour);
        }
        if (text.equals("Medium Filled S"))
        {
          previousShape = new Square(30, 30, false, 2, defaultColour);
        }
        if (text.equals("Large Empty S"))
        {
          previousShape = new Square(30, 30, true, 3, defaultColour);
        }
        if (text.equals("Large Filled S"))
        {
          previousShape = new Square(30, 30, false, 3, defaultColour);
        }
        if (text.equals("Small Empty R"))
        {
          previousShape = new Rectangle(30, 30, true, 1, defaultColour);
        }
        if (text.equals("Small Filled R"))
        {
          previousShape = new Rectangle(30, 30, false, 1, defaultColour);
        }
        if (text.equals("Medium Empty R"))
        {
          previousShape = new Rectangle(30, 30, true, 2, defaultColour);
        }
        if (text.equals("Medium Filled R"))
        {
          previousShape = new Rectangle(30, 30, false, 2, defaultColour);
        }
        if (text.equals("Large Empty R"))
        {
          previousShape = new Rectangle(30, 30, true, 3, defaultColour);
        }
        if (text.equals("Large Filled R"))
        {
          previousShape = new Rectangle(30, 30, false, 3, defaultColour);
        }
        if (text.equals("Small Empty T"))
        {
          previousShape = new Triangle(30, 30, true, 1, defaultColour);
        }
        if (text.equals("Small Filled T"))
        {
          previousShape = new Triangle(30, 30, false, 1, defaultColour);
        }
        if (text.equals("Medium Empty T"))
        {
          previousShape = new Triangle(30, 30, true, 2, defaultColour);
        }
        if (text.equals("Medium Filled T"))
        {
          previousShape = new Triangle(30, 30, false, 2, defaultColour);
        }
        if (text.equals("Large Empty T"))
        {
          previousShape = new Triangle(30, 30, true, 3, defaultColour);
        }
        if (text.equals("Large Filled T"))
        {
          previousShape = new Triangle(30, 30, false, 3, defaultColour);
        }
        shapes.add(previousShape);
        DrawSwing.this.requestFocusInWindow(); //keeps the panel in focus and not the frame
        repaint();
      }
    }
  }


  /*
    Class for creating the 4 shape name labels on top of each set of shape adding buttons (created a class for this to avoid duplicate formatting code for each label)
  */
  public class ShapeTitleLabel
  {
    /*
    Constructor initializing a ShapeTitleLabel object with an x position on the frame, y position on the frame, and text to be written as the label
    */
    public ShapeTitleLabel(int x, int y, String text)
    {   
      JLabel label = new JLabel();
      label.setText(text);
      label.setFont(new Font("Arial", Font.BOLD, 17));
      label.setBounds(x, y, 155, 40);
      frame.add(label);  
    }
  }


  /*
  KeyListener implementation for allowing the shape to move position and change colour when specific keyboard keys pressed
  */  
  public class KeyTracker implements KeyListener
  {
    /*
    Unused overridden KeyListener method (not needed for my program)
    */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /*
    Overridden KeyListener's keyPressed method to allow for specific functionality when specific keyboard keys are pressed.

    This method checks which key the user pressed by using getKeyCode(). If the right or left arrow keys are pressed, the shape 
    moves left or right respectively by 10 and if the up or down arrow keys are pressed, the shape moves up or down respectively 
    by 10. If the spacebar is pressed, the colour of the shape randomly changes. The position change and colour change only 
    applies to the most recently added shape on the panel.
    */
    @Override
    public void keyPressed(KeyEvent e) 
    {
      int key = e.getKeyCode();
      if (key == KeyEvent.VK_LEFT)
      {
        previousShape.x -= 10;
      }
      if (key == KeyEvent.VK_RIGHT)
      {
        previousShape.x += 10;
      }
      if (key == KeyEvent.VK_UP)
      {
        previousShape.y -= 10;
      }
      if (key == KeyEvent.VK_DOWN)
      {
        previousShape.y += 10;
      }
      if (key == KeyEvent.VK_SPACE && previousShape != null) 
      {
        previousShape.colour = new Color((int)(Math.random() * 0x1000000)); //generates a random hexadecimal value every time, creating a new RGB colour code each time
      }
      repaint();
    }

    /*
    Unused overridden KeyListener method (not needed for my program)
    */
    @Override
    public void keyReleased(KeyEvent e) {
    }
  }

}
