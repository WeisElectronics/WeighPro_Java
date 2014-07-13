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


public class CustomScrollBarUI extends BasicScrollBarUI{
	private final Dimension d = new Dimension();
    @Override protected JButton createDecreaseButton(int orientation) {
      return new JButton() {
        /**
		 * 
		 */
		private static final long serialVersionUID = -6945039777229646047L;

		@Override public Dimension getPreferredSize() {
          return d;
        }
      };
    }
    @Override protected JButton createIncreaseButton(int orientation) {
      return new JButton() {
        /**
		 * 
		 */
		private static final long serialVersionUID = -3824588883198246594L;

		@Override public Dimension getPreferredSize() {
          return d;
        }
      };
    }
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {}
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
      Graphics2D g2 = (Graphics2D)g.create();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                          RenderingHints.VALUE_ANTIALIAS_ON);
      Color color = null;
      JScrollBar sb = (JScrollBar)c;
      if(!sb.isEnabled()) {
        return;
      }else if(isDragging) {
    	color = new Color(255,255,255,150);
      }else if(isThumbRollover()) {
        color = new Color(200,200,200,200);
      }else {
        color = new Color(255,255,255,50);
      }
      g2.setPaint(color);
      if(r.width<r.height)
    	  g2.fillRoundRect(r.x+10,r.y+5,4,r.height-5,0,0);
      else
    	  g2.fillRoundRect(r.x+5,r.y+10,r.width-5,4,0,0);
      g2.dispose();
    }
    @Override
    protected void setThumbBounds(int x, int y, int width, int height) {
      super.setThumbBounds(x, y, width, height);
      scrollbar.repaint();
    }
    
   
}
