package in.weighpro.documentFilters;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


public class UpperCaseFilter extends DocumentFilter
{

	 @Override
	    public void insertString(FilterBypass fb, int off, String str, AttributeSet attr) 
	        throws BadLocationException 
	    {
	        fb.insertString(off, str.toUpperCase(), attr);  
	    } 
	    @Override
	    public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr) 
	        throws BadLocationException 
	    {
	    	 fb.replace(off, len, str.toUpperCase(), attr);  
	    }

}
