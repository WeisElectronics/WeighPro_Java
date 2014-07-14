/* This class provides the
*pluggable material object
*which is used in the storage and retrieval
*of the entries

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/


package in.weighpro.dataComponents;

import java.sql.SQLException;


//This class basically is an extension of the DataObject class and adds the description of a material
public class Material extends DataObject {

	//Constructor to initialize the material object by using an index

	public Material(int index) throws SQLException {
		super("materials", index);//Call to the constructor of the DataObject class which retrieves the material
					//associated with this index
					//may throw an SQL exception because of the database functions

	}

	//Constructor to initialize the material object using a material name
	public Material(String value) throws SQLException {
		super("materials", value);//Call to the constructor of the DataObject class which retrieves the id
					// associated with this material name
					//may throw an SQL exception because of the database functions

	}
}
