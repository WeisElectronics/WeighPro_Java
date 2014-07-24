/*
* This class provides the pluggable 
* document filter which allows
* only numbers
* used for the amount field in the 
* first entry panel

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.documentFilters;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


public class LimitAmountFilter extends DocumentFilter
{

    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException
    {
        StringBuilder sb = new StringBuilder();
        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.insert(offset, text);
        if(!containsOnlyNumbers(sb.toString())) return;
        if(offset>2) return;
        fb.insertString(offset, text, attr);
    }
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attr) throws BadLocationException
    {
        StringBuilder sb = new StringBuilder();
        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.replace(offset, offset + length, text);
        if(!containsOnlyNumbers(sb.toString())) return;
        if(offset>2) return;
        fb.replace(offset, length, text, attr);
    }

    /**
     * This method checks if a String contains only numbers
     */
    public boolean containsOnlyNumbers(String text)
    {
        Pattern pattern = Pattern.compile("\\d*");
        Matcher matcher = pattern.matcher(text);
        boolean isMatch = matcher.matches();
        return isMatch;
    }

}