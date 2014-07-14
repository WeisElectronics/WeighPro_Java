/* This class provides the
*pluggable data object
*which is used as the parent class
*of other data components

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.dataComponents;

import java.sql.SQLException;

import in.weighpro.databaseQueryHandler.DataIndexQuery;
import in.weighpro.databaseQueryHandler.DataValueQuery;

@SuppressWarnings("rawtypes")
//This class acts as a superclass for other data classes 
//It implements Comparable for sorting which is used in the reports panel
public class DataObject implements Comparable{
	private int index;
	private String value;

//Constructor to initialize the data object given an index
	public DataObject(String type, int index) throws SQLException {//may throw an SQLException because it uses database
								       //actions
		this.index = index;//setting the index value of the data object
		DataIndexQuery dataIndxQuery = new DataIndexQuery(type, index);//Query to retrieve data using the index
		value = dataIndxQuery.getValue();//using the retrieved value to set the value of the data object
	}

//Constructor to initialize the data object given a value
	public DataObject(String type, String value) throws SQLException {//may throw an SQLException because it uses database
									//actions
		this.value = value;//setting the value of the data object
		DataValueQuery dataInsQuery = new DataValueQuery(type, value);//Querying the database to retrieve the index
									     //corresponding to the value
		index = dataInsQuery.getIndex();//setting index using the index retrieved from the database
	}


//Method to get index of the data object
	public int getIndex() {
		return this.index;
	}

//Method to get the value of the data object
	public String getValue() {
		return this.value;
	}


//Method to get the value which is used in the compare method
	@Override
	public String toString() {
		return this.value;
	}

//Method to compare two objects which is used in sorting the list of objects of this type
//in the reports panel
	@Override
	public int compareTo(Object o) {
		return this.toString().compareTo(o.toString());
	}
	
	
	

}
