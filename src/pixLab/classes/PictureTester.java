package pixLab.classes;
/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test zeroRed */
  public static void testZeroRed()
  {
	  Picture arch = new Picture("arch.jpg");
	  arch.explore();
	  arch.zeroRed();
	  arch.explore();
  }
  
  /** Method to test zeroGreen */
  public static void testZeroGreen()
  {
	  Picture msg = new Picture("msg.jpg");
	  msg.explore();
	  msg.zeroGreen();
	  msg.explore();
  }
  
  /** Method to test KeepOnlyBlue.
   * 
   */
  public static void testKeepOnlyBlue()
  {
	  
  }
  
  /** Method to test KeepOnlyRed.
   * 
   */
  public static void testKeepOnlyRed()
  {
	  
  }
  
  /** Method to test keepOnlyGreen.
   * 
   */
  public static void testKeepOnlyGreen()
  {
	  
  }
  
  /** Method to test keepOnlyGreen.
   * 
   */
  public static void testNegate()
  {
	  
  }
  
  /** Method to test Grayscale.
   * 
   */
  public static void testGrayscale()
  {
	  
  }
  
  /** Method to test fixUnderwater.
   * 
   */
  public static void testFixUnderwater()
  {
	  
  }
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test mirrorArms.
   * 
   */
  public static void testMirrorArms()
  {
	  
  }
  
  /** Method to test mirrorGull.
   * 
   */
  public static void testMirrorGull()
  {
	  Picture gull = new Picture("seagull.jpg");
	  gull.explore();
	  gull.mirrorGull();
	  gull.explore();
  }
  
  /** Method to test mirrorDiagonal.
   * 
   */
  public static void testMirrorDiagonal()
  {
	  
  }
  
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test Copy. */
  public static void testCopy()
  {
	  Picture kitten2 = new Picture("kitten2.jpg");
	//  kitten2.copy(fromPic, startRow, startCol);
	  kitten2.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Method to test edgeDetection2.
   * 
   */
  public static void testEdgeDetection2()
  {
	  
  }
  
   /** Method that tests ChromaKey.
    * 
    */
  public static void testChromakey()
  {
	  
  }
  
  /** Method that tests EncodeAndDecode.
   * 
   */
  public static void testEncodeAndDecode()
  {
	  
  }
  
  /** Method that tests GetCountRedOverValue.
   * 
   */
  public static void testGetCountRedOverValue()
  {
	  
  }
  
  /** Method that tests SetRedToHalfValueInTopHalf.
   * 
   */
  public static void testSetRedToHalfValueInTopHalf()
  {
	  
  }
  
  /** Method that tests clearBlueOverValue.
   * 
   */
  public static void testClearBlueOverValue()
  {
	  
  }
  
  /** Method that tests getAverage.
   * 
   */
  public static void testGetAverage()
  {
	  
  }
  
 /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
//    testZeroBlue();
//    testZeroRed();
//    testZeroGreen();
//    testKeepOnlyBlue();
//    testKeepOnlyRed();
//    testKeepOnlyGreen();
//    testNegate();
//    testGrayscale();
//    testFixUnderwater();
//    testMirrorVertical();
//   testMirrorTemple();
//    testMirrorArms();
    testMirrorGull();
//    testMirrorDiagonal();
//    testCollage();
//    testCopy();
//    testEdgeDetection();
//    testEdgeDetection2();
//    testChromakey();
//    testEncodeAndDecode();
//    //testGetCountRedOverValue(250);
//    testSetRedToHalfValueInTopHalf();
//    //testClearBlueOverValue(200);
//   // testGetAverageForColumn(0);
  }
}