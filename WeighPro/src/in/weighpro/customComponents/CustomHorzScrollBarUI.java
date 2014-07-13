/* This class provides the pluggable horizontal scroll bar component
 *  used in the various panels 
 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */


package in.weighpro.customComponents;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

//this class is a child of the BasicScrollBar UI which is the default scroll bar UI
public class CustomHorzScrollBarUI extends BasicScrollBarUI{
	private final Dimension d = new Dimension();
	
	//Method return the decrease button of the scroll bar
	//Button with nil dimensions
    @Override protected JButton createDecreaseButton(int orientation) {
      return new JButton() {
        /**
		 * 
		 */
		private static final long serialVersionUID = -7644693060051451536L;

		@Override public Dimension getPreferredSize() {
        	return d;
        }
      };
    }
    
    //Method returns the increase button of the increase button
    //button with nil dimensions
    @Override protected JButton createIncreaseButton(int orientation) {
      return new JButton() {
        /**
		 * 
		 */
		private static final long serialVersionUID = -6839825064049357744L;

		@Override public Dimension getPreferredSize() {
          return d;
        }
      };
    }
    
    //method which paints the track
    //In this case this is left blank for a transparent look
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {}
    
    //the look of the actual scroll bar is set by this method
    
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
        
    	Graphics2D g2 = (Graphics2D)g.create();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                          RenderingHints.VALUE_ANTIALIAS_ON);
      Color color = null;
      JScrollBar sb = (JScrollBar)c;
      if(!sb.isEnabled() || r.height>r.width) {//check for a horizontal bar
    	    
        return;
      }else if(isDragging) {
    	   color = new Color(255,255,255,150);//When the bar is being dragged it is an off white color
      }else if(isThumbRollover()) {
        color = new Color(200,200,200,200);//When mouse is hovered a different color is used
      }else {
        color = new Color(255,255,255,50);
      }
      g2.setPaint(color);
      g2.fillRoundRect(r.x+5,r.y+10,r.width-5,4,0,0);//Round rectangle for the actual scroll bar
      g2.dispose();//disposing the graphics object
      
    }
    
    
    //Method to set the bounds of the scroll bar--------------------------
    @Override
    protected void setThumbBounds(int x, int y, int width, int height) {
      super.setThumbBounds(x, y, width, height);
      scrollbar.repaint();
    }
    
   
}
