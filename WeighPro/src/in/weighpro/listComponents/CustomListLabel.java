package in.weighpro.listComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

public class CustomListLabel extends JLabel implements ListCellRenderer<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7559320301298288702L;

	CustomListLabel() {
		setOpaque(true);
		setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
		this.setBorder(new EmptyBorder(5, 10, 5, 0));
		setForeground(new Color(255, 255, 255));
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		setText(value.toString().trim());
		if (isSelected) {
			setBackground(new Color(255, 255, 255));
			setForeground(new Color(100, 100, 100));
		} else {
			setBackground(new Color(255, 255, 255, 0));
			setForeground(new Color(255, 255, 255));
		}
		if (!cellHasFocus) {
			setBackground(new Color(255, 255, 255, 0));
			setForeground(new Color(255, 255, 255));
		}

		return this;
	}

}