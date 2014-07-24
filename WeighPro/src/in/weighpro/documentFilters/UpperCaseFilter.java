/*
* This class provides the pluggable
* document filter which does not allow
* lower case alphabets in a text field

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.documentFilters;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


public class UpperCaseFilter extends DocumentFilter
{

	//This method is invoked only when it is explicitly called by the code
	 @Override
	    public void insertString(FilterBypass fb, int off, String str, AttributeSet attr) 
	        throws BadLocationException 
	    {
	        fb.insertString(off, str.toUpperCase(), attr);//converting the string to upper case and calling the insert method  
	    }
	 
	 //This method is called whenever any changes take place in the text of the text field
	    @Override
	    public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr) 
	        throws BadLocationException 
	    {
	    	 fb.replace(off, len, str.toUpperCase(), attr);  //converting the string to upper case and calling the replace function
	    }

}
