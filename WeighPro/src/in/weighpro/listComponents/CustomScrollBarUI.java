/*
* This class provides the
* pluggable scroll bar UI
* which is used in various lists

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.listComponents;

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

//this class provides the scroll bar which has a custom look and feel
public class CustomScrollBarUI extends BasicScrollBarUI{
	private final Dimension d = new Dimension();//null dimensions
	
	//this method is called when the decrease button is being rendered
    @Override protected JButton createDecreaseButton(int orientation) {
      return new JButton() {
        /**
		 * 
		 */
		private static final long serialVersionUID = -6945039777229646047L;

		//set dimension of the button to  empty dimensions
		@Override public Dimension getPreferredSize() {
          return d;
        }
      };
    }
    
    //this method is called when the increase button of the scroll bar is being rendered
    @Override protected JButton createIncreaseButton(int orientation) {
      return new JButton() {
        /**
		 * 
		 */
		private static final long serialVersionUID = -3824588883198246594L;
		//set dimension of the button to empty dimension
		@Override public Dimension getPreferredSize() {
          return d;
        }
      };
    }
    
    
    //this method is called when the track of the scroll bar is rendered
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {}//making the track invisible
    
    
    //this method is called when the bar is being rendered
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
      Graphics2D g2 = (Graphics2D)g.create();//casting the graphics object to a graphics 2D object
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                          RenderingHints.VALUE_ANTIALIAS_ON);//edges are rendered more smoothly
      Color color = null;//initializing a common color object
      JScrollBar sb = (JScrollBar)c;//casting the component to a JScrollBar Object
      if(!sb.isEnabled()) {// when the scroll bar is not being displayed or rendered
        return;
      }else if(isDragging) {//when the scroll bar is being dragged
    	color = new Color(255,255,255,150);//set color to an off white color
      }else if(isThumbRollover()) {// when the mouse is hovered over the scroll bar
        color = new Color(200,200,200,200);//set the color of the scroll bar to a grey shade
      }else {
        color = new Color(255,255,255,50);//standard color is an off white color
      }
      g2.setPaint(color);//set color of the graphics object
      if(r.width<r.height)//rendering a vertical scroll bar
    	  g2.fillRoundRect(r.x+10,r.y+5,4,r.height-5,0,0);//the scroll bar is a rounded rectangle with the passed dimensions
      else
    	  g2.fillRoundRect(r.x+5,r.y+10,r.width-5,4,0,0);//render a horizontal scroll bar
      g2.dispose();//dispose the graphics object
    }
    
    
    // this method is called when rendering the bounds of the scroll bar 
    @Override
    protected void setThumbBounds(int x, int y, int width, int height) {
      super.setThumbBounds(x, y, width, height);//the dimensions are passed automatically
      scrollbar.repaint();//repaint the scroll bar
    }
    
   
}
