package pixLab.classes;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List
import com.sun.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method to set the red to 0 */
  public void zeroRed()
  {
	  Pixel [] [] pixels = this.getPixels2D();
	  for (Pixel [] rowArray : pixels)
	  {
		  for (Pixel pixelObj : rowArray)
		  {
			  pixelObj.setRed(0);
		  }
	  }
  }
  
  /** Method to set the green to 0 */
  public void zeroGreen()
  {
	  Pixel [] [] pixels = this.getPixels2D();
	  for (Pixel [] rowArray : pixels)
	  {
		  for (Pixel pixelObj : rowArray)
		  {
			  pixelObj.setGreen(0);
		  }
	  }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  public void edgeDetection2(int edgeDist)
  {
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  Pixel rightPixel = null;
	  Pixel [][] pixels = this.getPixels2D();
	  Color bottomColor = null;
	  Color rightColor = null;
	  
	  for (int row = 0; row < pixels.length; row++)
	  {
	  for (int col = 0; 
	           col < pixels[0].length-1; col++)
	      {
	      topPixel = pixels[row][col];
	     rightPixel = pixels[row][col+1];
	     bottomPixel = pixels[row+1][col];
	      bottomColor = bottomPixel.getColor();
	      rightColor = rightPixel.getColor();
	      if ((topPixel.colorDistance(bottomColor) > 
	      edgeDist) && (topPixel.colorDistance(rightColor)> edgeDist))
	    	  topPixel.setColor(Color.BLACK);
	      else
	    	  topPixel.setColor(Color.WHITE);
	      }
	  }
  }
  
  public void decrypt(int max) 
	{
		try
		{
			RandomAccessFile data = new RandomAccessFile("gorge.jpg", "rw");
			long size = data.length();
			
			int num = 0;
			int power = 128;
			int bits = 8;
			int count = 0;
			
			
			for(int x=54; x < size; x++)
			{
				data.seek(x);
				byte b = data.readByte();
				
				b = (byte) (b & 1);
				
				num = num + b * power;
				bits = bits + 1;
				power = power /2;
				
				if ((bits & 8) == 0 )
				{
					char c = (char)num;
					System.out.print(c);
					power = 128;
					num = 0;
					count = count +1;
					if (count >= max)
					{
						return;
					}
				}
			
			}
			data.close();
		}
		catch (IOException ex)
		{
			
		}
		
	}
	
  public void keepOnlyBlue()
  {
	  
  }
 
  public void keepOnlyRed()
  {
	  
  }
  
  public void keepOnlyGreen()
  {
	  
  }
  
  public void Negate()
  {
	  int mirrorPoint = 340;
	    Pixel leftPixel = null;
	    Pixel rightPixel = null;
	    int count = 0;
	    Pixel[][] pixels = this.getPixels2D();
	    
	    // loop through the rows
	    for (int row = 235; row < 323; row++)
	    {
	      // loop from 238(col), less than mirrorPoint.
	      for (int col = 238; col < mirrorPoint; col++)
	      {
	           leftPixel = pixels[row][col];      
	           rightPixel = pixels[row]                       
	        [mirrorPoint - col + mirrorPoint];
	        rightPixel.setColor(leftPixel.getColor());
	      }
	    }    
}
  
  
  public void Grayscale()
  {
	
  	
  }    	

  
  public void fixUnderwater()
  {
	  
  }
  
  public void mirrorArms()
  {
	  
  }
  
  public void mirrorGull()
  {
	  int mirrorPoint = 340;
	    Pixel leftPixel = null;
	    Pixel rightPixel = null;
	    int count = 0;
	    Pixel[][] pixels = this.getPixels2D();
	    
	    // loop through the rows
	    for (int row = 235; row < 323; row++)
	    {
	      // loop from 238(col), less than mirrorPoint.
	      for (int col = 238; col < mirrorPoint; col++)
	      {
	           leftPixel = pixels[row][col];      
	           rightPixel = pixels[row]                       
	        [mirrorPoint - col + mirrorPoint];
	        rightPixel.setColor(leftPixel.getColor());
	      }
	    }    
  }
  
  public void mirrorDiagonal()
  {
	  Pixel [] [] pixels = this.getPixels2D();
	  Pixel pixel1 = null;
	  Pixel pixel2 = null;
	    int width = pixels[0].length;
	      // loop through the rows
	    for (int row = 0; row < pixels.length; row++)
	    {
	      // loops from col less than the length of the pixels.
	      for (int col = 0; col < pixels[0].length; col++)
	      {
	        if(col < pixels.length)
            pixel1 = pixels [row][col];
            pixel2 = pixels [col] [row];
            pixel1.setColor(pixel2.getColor());
          }
	    }
  }
  
  public void chromaKey() throws IOException
  {
	  Pixel [] [ ] FirstPicture = this.getPixels2D();
	  Pixel [] [] SecondPicture = new Picture("flower1.jpg").getPixels2D();
	  
	  for(int row = 0; row<FirstPicture.length; row++)
	  {
		  for(int col = 0; col<SecondPicture.length; col++)
		  {
			  
		  }
	  }
	  
  }
  
  public void EncodeandDecode()
  {
	  
  }
  
  public void getCountRedOverValue()
  {
	  
  }
  
  public void setRedtoHalfValueInTopHalf()
  {
	  
  }
  
  public void clearBlueOverValue()
  {
	  
  }
  
  public void getAverageforColumn()
  {
	  
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) throws IOException 
  {
    Picture gorge = new Picture("gorge.jpg");
    //gorge.getBufferedImage();
    gorge.mirrorGull();
    gorge.chromaKey();
    gorge.Negate();
   // gorge.createCollage();
   // gorge.createGraphics();
   // gorge.copyPicture(gorge);
   gorge.explore();
   // gorge.write("newGorge.jpg");
  }
  
} // this } is the end of class Picture, put all new methods before this
