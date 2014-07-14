/* This class provides the
*pluggable vehicle object
*which is used in the storage and retrieval
*of the entries

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.dataComponents;

import java.sql.SQLException;

//This class basically is an extension of the DataObject class and adds the description of a vehicle
public class Vehicle extends DataObject {

	//Constructor to initialize the vehicle object by using an index

	public Vehicle(int index) throws SQLException {
		super("vehicles", index);//Call to the constructor of the DataObject class which retrieves the vehicle
					//associated with this index
					//may thorw an SQL exception because of the database functions
	}

//Constructor to initialize the vehicle object using a vehicle number
	public Vehicle(String value) throws SQLException {
		super("vehicles", value);//Call to the constructor of the DataObject class which retrieves the id
						//associated with this vehicle number
						//may thorw an SQL exception because of the database functions
	}

}
