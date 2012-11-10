package rayTracePhase3;

/**
 * Class that references a pixel in a picture. A pixel has an x and y
 * location in a picture.  A pixel knows how to get and set the red, 
 * green, blue, and alpha values in the picture.  A pixel also knows
 * how to get and set the color using a Color object.
 * 
 * Copyright Georgia Institute of Technology 2004
 * @author Barb Ericson ericson@cc.gatech.edu
 * 
 * Modified by John M. Hunt Covenant College to trim out
 * anything not needed by ray tracer assignment
 */
public class Pixel
{
  
  ////////////////////////// fields ///////////////////////////////////
  
  /** the digital picture this pixel belongs to */
  private PicturePanel picture;
  
  /** the x location of this pixel in the picture (0,0) is top left */
  private int x; 
  
  /** the y location of this pixel in the picture (0,0) is top left */
  private int y; 
  
  ////////////////////// constructors /////////////////////////////////
  
  /** 
   * A constructor that take the x and y location for the pixel and
   * the picture the pixel is coming from
   * @param picture the picture that the pixel is in
   * @param x the x location of the pixel in the picture
   * @param y the y location of the pixel in the picture
   */
  public Pixel(PicturePanel picture, int x, int y)
  {
    // set the picture
    this.picture = picture;
    
    // set the x location
    this.x = x;
    
    // set the y location
    this.y = y;
    
  }
  
  ///////////////////////// methods //////////////////////////////
  
  /**
   * Method to get the x location of this pixel.  
   * @return the x location of the pixel in the picture
   */
  public int getX() { return x; }
  
  /**
   * Method to get the y location of this pixel.
   * @return the y location of the pixel in the picture
   */
  public int getY() { return y; }
  
  
    
  
  /**
   * Method to get the amount of red at this pixel.  It will be
   * from 0-255 with 0 being no red and 255 being as much red as
   * you can have.
   * @return the amount of red from 0 for none to 255 for max
   */
  public int getRed() { 
    
    /* get the value at the location from the picture as a 32 bit int
     * with alpha, red, green, blue each taking 8 bits from left to right
     */
    int value = picture.getBasicPixel(x,y);

    // get the red value (starts at 17 so shift right 16)
    // then and it with all 1's for the first 8 bits to keep
    // end up with from 0 to 255 
    int red = (value >> 16) & 0xff;
    
    return red;
  }
  
  /**
   * Method to get the red value from a pixel represented as an int
   * @param value the color value as an int
   * @return the amount of red
   */
  public static int getRed(int value)
  {
    int red = (value >> 16) & 0xff;
    return red;
  }
  
  /**
   * Method to get the amount of green at this pixel.  It will be
   * from 0-255 with 0 being no green and 255 being as much green as
   * you can have.
   * @return the amount of green from 0 for none to 255 for max
   */
  public int getGreen() { 
    
    /* get the value at the location from the picture as a 32 bit int
     * with alpha, red, green, blue each taking 8 bits from left to right
     */
    int value = picture.getBasicPixel(x,y);

    // get the green value (starts at 9 so shift right 8)
    int green = (value >>  8) & 0xff;
    
    return green;
  }
  
  /**
   * Method to get the green value from a pixel represented as an int
   * @param value the color value as an int
   * @return the amount of green
   */
  public static int getGreen(int value)
  {
    int green = (value >> 8) & 0xff;
    return green;
  }
  
  /**
   * Method to get the amount of blue at this pixel.  It will be
   * from 0-255 with 0 being no blue and 255 being as much blue as
   * you can have.
   * @return the amount of blue from 0 for none to 255 for max
   */
  public int getBlue() { 
    
    /* get the value at the location from the picture as a 32 bit int
     * with alpha, red, green, blue each taking 8 bits from left to right
     */
    int value = picture.getBasicPixel(x,y);

    // get the blue value (starts at 0 so no shift required)
    int blue = value & 0xff;
    
    return blue;
  }
  
  /**
   * Method to get the blue value from a pixel represented as an int
   * @param value the color value as an int
   * @return the amount of blue
   */
  public static int getBlue(int value)
  {
    int blue = value & 0xff;
    return blue;
  }
  
  /**
   * Method to update the picture based on the passed color
   * values for this pixel
   * @param alpha the alpha (transparency) at this pixel
   * @param red the red value for the color at this pixel
   * @param green the green value for the color at this pixel
   * @param blue the blue value for the color at this pixel
   */
  public void updatePicture(int alpha, int red, int green, int blue)
  {
    // create a 32 bit int with alpha, red, green blue from left to right
    int value = (alpha << 24) + (red << 16) + (green << 8) + blue;
    
    // update the picture with the int value
    picture.setBasicPixel(x,y,value);
  }
  
  /**
   * Method to correct a color value to be within 0 and 255
   * @param the value to use
   * @return a value within 0 and 255
   */
  private static int correctValue(int value)
  {
    if (value < 0)
      value = 0;
    if (value > 255)
      value = 255;
    return value;
  }
  
  /**
   * Method to set the red to a new red value
   * @param value the new value to use
   */
  public void setRed(int value)
  {
    // set the red value to the corrected value
    int red = correctValue(value);
    
    // update the pixel value in the picture
    // alpha of 255 is opaque 
    updatePicture(255, red, getGreen(), getBlue());
  } 
  
  /**
   * Method to set the green to a new green value
   * @param value the value to use
   */
  public void setGreen(int value)
  {
    // set the green value to the corrected value
    int green = correctValue(value);
    
    // update the pixel value in the picture
    // alpha of 255 is opaque 
    updatePicture(255, getRed(), green, getBlue());
  } 
  
  /**
   * Method to set the blue to a new blue value
   * @param value the new value to use
   */
  public void setBlue(int value)
  {
    // set the blue value to the corrected value
    int blue = correctValue(value);
    
    // update the pixel value in the picture
    // alpha of 255 is opaque 
    updatePicture(255, getRed(), getGreen(), blue);
  } 
   
   
  /**
   * Method to return a string with information about this pixel
   * @return a string with information about this pixel
   */
  public String toString()
  {
    return "Pixel red=" + getRed() + " green=" + getGreen() + 
      " blue=" + getBlue();
  }

}