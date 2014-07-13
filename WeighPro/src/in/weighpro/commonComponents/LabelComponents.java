/*
 * This class provides the 
 * common labels used in both
 * first screen and second screen
 * 
 * Author - Weis Electronics Pvt. Ltd.
 * Contribution by emdroidery
 * */



package in.weighpro.commonComponents;

import java.awt.Component;

import in.weighpro.customComponents.CustomStandardLabel;
import javax.swing.JPanel;

public class LabelComponents extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7686012897752871815L;

	//------Creating all the label variables----------------------------------
	
	private CustomStandardLabel IDLabel;
	private CustomStandardLabel vehicleNumberLabel;
	private CustomStandardLabel customerLabel;
	private CustomStandardLabel materialLabel;
	private CustomStandardLabel amountLabel;
	private CustomStandardLabel weightTypeLabel;
	private CustomStandardLabel weightLabel;

	private int lXBound = 50, lYBound = 50, lWidth = 200, lHeight = 28;

	//------Constructor which initializes all the labels ---------------------
	
	public LabelComponents() {

		setLayout(null);                                  //Custom Layout
		setOpaque(false);								  //transparent background
		setBounds(0, 0, 200, 500);						  //setting the bounds	
		IDLabel = new CustomStandardLabel("ID");		  //Initializing the ID Label
		setLabelBounds(IDLabel);						  //Setting bounds for the ID Label
		add(IDLabel);									//adding the ID Label to the panel
		vehicleNumberLabel = new CustomStandardLabel("Vehicle Number");//Initializing the vehicle number Label
		setLabelBounds(vehicleNumberLabel); //Setting bounds for the vehicle number Label
		add(vehicleNumberLabel);//adding the vehicle number Label to the panel
		customerLabel = new CustomStandardLabel("Customer");//Initializing the vehicle number Label
		setLabelBounds(customerLabel);//Setting bounds for the customer Label
		add(customerLabel);//adding the customer Label to the panel
		materialLabel = new CustomStandardLabel("Material");//Initializing the material Label
		setLabelBounds(materialLabel);//Setting bounds for the material Label
		add(materialLabel);//adding the material Label to the panel

		amountLabel = new CustomStandardLabel("Amount");//Initializing the amount Label
		setLabelBounds(amountLabel);//Setting bounds for the amount Label
		add(amountLabel);//adding the amount Label to the panel

		weightTypeLabel = new CustomStandardLabel("Weight Type");//Initializing the weight type Label
		setLabelBounds(weightTypeLabel);//Setting bounds for the weight type Label
		add(weightTypeLabel);//adding the weight type Label to the panel

		weightLabel = new CustomStandardLabel("Weight");//Initializing the weight Label
		weightLabel.setBounds(lXBound, 400, lWidth, lHeight);//Setting bounds for the weight type Label
		add(weightLabel);//adding the weight Label to the panel
	}

	
	//------Function to set bounds for the labels by calculating the exact y coordinate of the label
	void setLabelBounds(Component comp) {
		comp.setBounds(lXBound, lYBound, lWidth, lHeight);
		lYBound += lHeight + 30;
	}

}
