/* This class provides the
*pluggable customer object 
*which is used in the storage and retrieval
*of the entries

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.dataComponents;

import java.sql.SQLException;

//This class basically is an extension of the DataObject class and adds the description of a customer
public class Customer extends DataObject {

	//Constructor to initialize the customer object by using an index
	public Customer(int index) throws SQLException {
		super("customers", index);//Call to the constructor of the DataObject class which retrieves the customer 
					//associated with this index
					//may thorw an SQL exception because of the database functions
	}


//Constructor to initialize the customer object using a customer name
	public Customer(String value) throws SQLException {
		super("customers", value);//Call to the constructor of the DataObject class which retrieves the id 
					//associated with this customer name
					//may thorw an SQL exception because of the database functions
	}
}
