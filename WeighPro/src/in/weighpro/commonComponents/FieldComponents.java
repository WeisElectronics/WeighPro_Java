/*
 * This class provides the pluggable fields components
 *  used in the first entry as well as the second entry pane
 
 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */

package in.weighpro.commonComponents;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.text.AbstractDocument;

import in.weighpro.customComponents.CustomMutedTextField;
import in.weighpro.customComponents.CustomStandardTextField;
import in.weighpro.dataComponents.Entry;
import in.weighpro.documentFilters.LimitAmountFilter;
import in.weighpro.documentFilters.UpperCaseFilter;
import in.weighpro.documentFilters.VehicleNumberFieldFilter;

public class FieldComponents extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1139482326828967916L;
	
	
	public CustomStandardTextField vehicleNumberField, customerField,
			materialField, amountField;
	private int tfXBound = 220, tfYBound =108, tfWidth = 200, tfHeight = 28; //The bounds that will be used by the components

	
	//----Constructor used to initialize fields to be plugged into the first entry pane----------
	
	public FieldComponents() {
		vehicleNumberField = new CustomStandardTextField(20);      // Initializing the first entry field
		((AbstractDocument) vehicleNumberField.getDocument())	//Using the vehicle number filter 																					
				.setDocumentFilter(new VehicleNumberFieldFilter());  //as the document filter 

		customerField = new CustomStandardTextField(20);      // Initializing the customer field
		((AbstractDocument) customerField.getDocument())      //Using Caps only filter for customer
				.setDocumentFilter(new UpperCaseFilter());	  //field

		materialField = new CustomStandardTextField(20);      // Initializing the material field
		((AbstractDocument) materialField.getDocument())       //Using Caps only filter for material
				.setDocumentFilter(new UpperCaseFilter());     //field

		amountField = new CustomStandardTextField(20);         // Initializing the amount field
		((AbstractDocument) amountField.getDocument())			//Using number only filter for amount field
				.setDocumentFilter(new LimitAmountFilter());

		commonInitialization();								//Calling the method which is common to both constructors 
															//and is used to set the look and feel
	}
	
	
	//----Constructor used to initialize fields to be plugged into the second entry pane----------
	
	public FieldComponents(Entry entry){      // Uses entry to update the fields automatically 
		vehicleNumberField = new CustomMutedTextField(entry.getVehicleNumber()); //Initializing the vehicle number field and filling it
		customerField = new CustomMutedTextField(entry.getCustomer());  //Initializing the customer field and filling it
		materialField = new CustomMutedTextField(entry.getMaterial());  //Initializing the material field and filling it
		amountField = new CustomMutedTextField(Integer.toString(entry.getAmount()));//Initializing the amount field and filling it
		commonInitialization();                //Calling the method which is common to both constructors 
											   //and is used to set the look and feel
	}

	
	 //-----------Method to set the common look and feel and is called by both the constructors------------
	
	private void commonInitialization() {     
		setLayout(null);              // Creating a custom layout and placing fields manually
		setOpaque(false);			// Creating a transparent look
		setVisible(true);			//Making the panel visible
		setBounds(0, 0,450, 500);	//setting the bounds
		setTextFieldBounds(vehicleNumberField);  //Setting the bounds for the fields
		add(vehicleNumberField);				// Adding the fields to the layout
		setTextFieldBounds(customerField);
		add(customerField);
		setTextFieldBounds(materialField);
		add(materialField);
		setTextFieldBounds(amountField);
		add(amountField);
	}

	 //-----Method to set the bounds for the text fields takes component as an argument for which the bounds are to be set---
	//-------Basically calculates the vertical position of the next element to be added and assigns bounds to that component---
	
	
	private void setTextFieldBounds(Component comp) {
		comp.setBounds(tfXBound, tfYBound, tfWidth, tfHeight);
		tfYBound += tfHeight + 30;
	}

	
}
