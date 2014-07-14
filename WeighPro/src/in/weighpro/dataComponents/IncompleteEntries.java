/* This class provides the
*entries for the incomplete entries list
*which is present in the first entry and the second entry panel

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.dataComponents;

import java.sql.SQLException;

import in.weighpro.databaseQueryHandler.IncompleteEntriesRetriever;


//This class gets all the entries from the database whose status is incomplete and provides an array of type Entry
public class IncompleteEntries {
	private Entry[] incompleteEntries;//array of type Entry which holds all the incomplete entries
	
	//Constructor that generates the incomplete list from the database
	public IncompleteEntries() throws SQLException{//may throw an SQLException because it performs database actions
		IncompleteEntriesRetriever incompRet = new IncompleteEntriesRetriever();//This retrieves all the incomplete entries 
											//present in the database
		int[] inRet = incompRet.getIncompleteEntries();//An array which contains indices of the incomplete entries
		incompleteEntries = new Entry[inRet.length];//Creating an array of type Entry based on the size of the incomplete indices list
		for(int i=0;i<inRet.length;i++)//loop to create Entry objects using each of the retrieved indices
			incompleteEntries[i] = new Entry(inRet[i]);//creating an Entry object using the index
		
	}
	
	//Method to get the array of Incomplete Entries
	public Entry[] getIncompleteEntries(){
		return this.incompleteEntries;
	}
}
