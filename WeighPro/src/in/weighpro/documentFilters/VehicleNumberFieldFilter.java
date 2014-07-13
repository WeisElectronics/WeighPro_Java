package in.weighpro.documentFilters;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class VehicleNumberFieldFilter extends DocumentFilter {
	boolean specialCodeFlag = false;
	boolean doneFlag = false;
	boolean numberStartedFlag = false;
	int numberStartPosition = -1;

	@Override
	public void insertString(FilterBypass fb, int off, String str,
			AttributeSet attr) throws BadLocationException {
		fb.insertString(off, str, attr);
	}

	@Override
	public void replace(FilterBypass fb, int off, int len, String str,
			AttributeSet attr) throws BadLocationException {

		if (len > 1) {
			return;
		}

		str = str.toUpperCase();
		if (off == 0) {
			fb.replace(off, len, str.replaceAll("[^A-Z]", ""), attr);
		}
		if (off == 1) {
			str = str.replaceAll("[^A-Z]", "");
			if (str.matches("[A-Z]$")) {
				str = str.replaceAll("$", " ");
			}
			fb.replace(off, len, str, attr);
		}
		if (off == 3) {
			str = str.replaceAll("\\D", "");
			fb.replace(off, len, str, attr);
		}
		if (off == 4) {
			if (str.matches("[A-Z]")) {
				fb.insertString(3, "0", attr);
				fb.insertString(5, " ", attr);
				fb.insertString(6, str, attr);
				specialCodeFlag = true;
			} else if (str.matches("\\d")) {
				str += " ";
				fb.replace(off, len, str, attr);

			}
		}

		if (off == 6) {
			if (str.matches("[A-Z]")) {
				specialCodeFlag = true;
				fb.replace(off, len, str, attr);
			} else if (str.matches("\\d")) {
				if (numberStartPosition == -1) {
					numberStartPosition = off - 1;
				}
				fb.replace(off, len, str, attr);
			}

		}

		if (off > 6) {
			if (str.matches("[A-Z]")) {
				if (!numberStartedFlag)
					if (specialCodeFlag) {
						fb.replace(off, len, str, attr);
					}
			} else if (str.matches("\\d")) {

				if (specialCodeFlag) {

					if (!doneFlag) {
						str = " " + str;
						doneFlag = true;
						numberStartedFlag = true;
						numberStartPosition = off;
					}

					if (off <= numberStartPosition + 4)
						fb.replace(off, len, str, attr);

				} else {

					if (off <= numberStartPosition + 4)
						fb.replace(off, len, str, attr);
				}

			}
		}

	}

	@Override
	public void remove(FilterBypass fb, int offset, int length)
			throws BadLocationException {
		if (offset == 2) {
			super.remove(fb, 1, length + 1);
		}
		if (offset == 5) {
			super.remove(fb, 4, length + 1);
		}

		if (offset > 5 && offset <= numberStartPosition) {
			doneFlag = false;
			specialCodeFlag = false;
		}

		if (offset == numberStartPosition) {
			super.remove(fb, numberStartPosition - 1, length + 1);
			numberStartedFlag = false;
			numberStartPosition = -1;
		}
		super.remove(fb, offset, length);

	}

}