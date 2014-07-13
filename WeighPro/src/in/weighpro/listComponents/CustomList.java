package in.weighpro.listComponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class CustomList extends JList<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8292150763793513858L;

	public CustomList(Object[] items) {
		super(items);
		setOpaque(false);
		setForeground(new Color(255, 255, 255, 255));
		setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
		this.setCellRenderer(new CustomListLabel());
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

}