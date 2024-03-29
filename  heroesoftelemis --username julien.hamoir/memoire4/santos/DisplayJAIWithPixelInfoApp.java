/*
 * Created on May 22, 2005
 * @author Rafael Santos (rafael.santos@lac.inpe.br)
 * 
 * Part of the Java Advanced Imaging Stuff site
 * (http://www.lac.inpe.br/~rafael.santos/Java/JAI)
 * 
 * STATUS: Complete.
 * 
 * Redistribution and usage conditions must be done under the
 * Creative Commons license:
 * English: http://creativecommons.org/licenses/by-nc-sa/2.0/br/deed.en
 * Portuguese: http://creativecommons.org/licenses/by-nc-sa/2.0/br/deed.pt
 * More information on design and applications are on the projects' page
 * (http://www.lac.inpe.br/~rafael.santos/Java/JAI).
 */

package santos;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.RenderedImage;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * This class uses an instance of  DisplayJAIWithPixelInfo to display an image in an
 * application.
 */ 
public class DisplayJAIWithPixelInfoApp extends JFrame implements MouseMotionListener
  {
  private JLabel label; // a label that will contain information on the pixels
                        // of the image being displayed.
  private DisplayJAIWithPixelInfo dj; // an instance of the display component.
  
 /**
  * The constructor of the class, which sets the frame appearance and creates an
  * instance of the modified display class.
  * @param image
  */
  public DisplayJAIWithPixelInfoApp(RenderedImage image)
    {
    setTitle("Move the mouse over the image !");
    // Get the JFrame's ContentPane.
    Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());
    // Create an instance of DisplayJAIWithPixelInfo and adds it to the content pane.
    dj = new DisplayJAIWithPixelInfo(image);
    contentPane.add(new JScrollPane(dj),BorderLayout.CENTER);
    // Registers the mouse motion listener so any event from the display class
    // will be also processed here.
    dj.addMouseMotionListener(this);
    // Add a text label with the image information.
    label = new JLabel("???");
    contentPane.add(label,BorderLayout.SOUTH);
    // Set the closing operation so the application is finished.
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400,400); // adjust the frame size.
    setVisible(true); // show the frame.
    }

 /**
  * This method will be executed when the mouse is moved over the extended
  * display class.
  * @param e the mouse event
  */
  public void mouseMoved(MouseEvent e)
    {
    String pos = "("+e.getX()+","+e.getY()+") ";
    label.setText(pos+dj.getPixelInfo()); // just update the label with the
                                          // DisplayJAIWithPixelInfo instance info.
    }
  
 /**
  * This method will not do anything - it is here just to keep the
  * MouseMotionListener interface happy.
  */
  public void mouseDragged(MouseEvent e) { }

 /**
  * The application entry point.
  * @param args the command line arguments.
  */
  public static void main(String[] args)
    {
    PlanarImage image = JAI.create("fileload", "essai.bmp");
    new DisplayJAIWithPixelInfoApp(image);
    } // end class

  } // end main