/* This class provides the
*pluggable entry object
*which is used in the storage and retrieval
*of the entries

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

package in.weighpro.dataComponents;

import in.weighpro.databaseQueryHandler.InsertEntryQuery;
import in.weighpro.databaseQueryHandler.RetrieveEntryQuery;
import in.weighpro.databaseQueryHandler.UpdateEntryQuery;
import in.weighpro.formatters.DateFormatter;
import in.weighpro.formatters.UserDateFormatter;

import java.sql.SQLException;
import java.text.ParseException;


//This class packages all the fields into their respective wrapper classes and creates entries
//It also retrieves an entry given the index of the entry
public class Entry {
	private int amount;
	private Customer customer;
	private boolean incomingType = false;
	private int index = -1;
	private Material material;
	private boolean status = false;
	private TimeDetails time;
	private Vehicle vehicle;
	private Weight weight;
	DateFormatter formatter = new DateFormatter();
	UserDateFormatter userFormatter = new UserDateFormatter();
	
	//This constructor retrieves an Entry based on the index of the entry
	//it then cretes wrappers of the retrieved data
	public Entry(int index) throws SQLException {//can throw an Exception because of the database operations
		
		RetrieveEntryQuery retEntry = new RetrieveEntryQuery(index);//This class retrieves an entry given the index from the database
		this.index = retEntry.getIndex();//setting the index using the retrieved index
		this.vehicle = new Vehicle(retEntry.getVehicleIndex());//creating a vehicle object using the retrieved vehicle index
		this.customer = new Customer(retEntry.getCustomerIndex());//creating a customer object using the retrieved customer index
		this.material = new Material(retEntry.getMaterialIndex());//create a material object using the retrieved material index
		this.weight = new Weight(retEntry.getTareWeight(),
				retEntry.getGrossWeight(), retEntry.getNetWeight(),
				retEntry.getIncomingType());//creating a weight object using the retrieved weight
		this.amount = retEntry.getAmount();//setting the amount using the retrieved amount
		this.time = new TimeDetails(retEntry.getEntryTimeStamp(),
				retEntry.getExitTimeStamp());//creating a time details object using the retrieved time details
		this.status = retEntry.getStatus();//setting status using the retrieved status 
		this.incomingType = retEntry.getIncomingType();//setting the incoming type using the retrieved incoming type
		
	}


	//this constructor creates all the wrapper objects from the user entered data
	//after that it creates an entry in the database using that data

	public Entry(String vehicleNumber, String customer, String material,
			int weight, boolean tareWeightFlag, int amount) throws SQLException {
		this.vehicle = new Vehicle(vehicleNumber);//creating a vehicle object using the user entered vehicle number
		this.customer = new Customer(customer);//creating a customer object using the user entered customer name
		this.material = new Material(material);//creating a material object using the user entered material
		this.weight = new Weight(weight, tareWeightFlag);//creating a weight object using the weight and user entered weight type 
		this.time = new TimeDetails();//creating a time details object using the current time of the system
		status = false;//status is set to incomplete
		incomingType = !tareWeightFlag;//incoming type is true for gross and false for tare
		this.amount = amount;//setting the amount using the user entered amount
		InsertEntryQuery insQuery = new InsertEntryQuery(vehicle,
				this.customer, this.material, amount, this.weight, time);//Inserting this data into the database using this class
		index = insQuery.getIndex();//setting the index using the index returned by the insert query
	}


//method to get the amount of the entry
	public int getAmount() {
		return this.amount;
	}

//method to get the customer of the entry
	public String getCustomer() {
		return this.customer.getValue();
	}

//method to get the entry timestamp of the entry
	public String getEntryTimeStamp() {
		return this.time.getEntryTimeStamp();
	}

//method to get the exit timestamp of the entry
	public String getExitTimeStamp() {
		return this.time.getExitTimeStamp();
	}

//method to get the gross weight of the entry
	public String getGrossWeight() {
		if(this.weight.getGrossWeight()==-1){
			return "";
		}else{
			return Integer.toString(this.weight.getGrossWeight());
		}
	}


//method to get the incoming type of the entry
	public boolean getIncomingType() {
		return this.incomingType;
	}


//method to get the index of the entry
	public int getIndex() {
		return this.index;
	}


//method to get material of the entry
	public String getMaterial() {
		return this.material.getValue();
	}


//method to get the net weight of the entry
	public String getNetWeight() {
		if(this.weight.getNetWeight()==-1){
			return "";
		}else{
			return Integer.toString(this.weight.getNetWeight());
		}
	}


//method to get the status of the entry
	public boolean getStatus() {
		return this.status;
	}


//method to get the tare weight of the entry
	public String getTareWeight() {
		if(this.weight.getTareWeight()==-1){
			return "";
		}else{
			return Integer.toString(this.weight.getTareWeight());
		}
	}


//method to get the vehicle number of the entry
	public String getVehicleNumber() {
		return this.vehicle.getValue();
	}

//method to update entry after weight is captured for the second entry
	public void updateEntry(int weight) throws SQLException {//takes the weight as an argument
		this.weight.secondWeight(weight);//set the second weight
		this.incomingType = this.getIncomingType();//set the updated incoming type
		time.setExitTimeStamp();//set the exit time stamp
		new UpdateEntryQuery(this.index, this.weight, this.time);//update entry using all the updated values
		this.status = true;//set the status to complete
	}

//the to String method is overriden to return the vehicle number since that is shown in the incomplete list
	@Override
	public String toString() {
		return this.vehicle.getValue();
	}

	public String getTareDate() throws ParseException{
		if(incomingType){
			return userFormatter.valueToString(formatter.stringToValue(getExitTimeStamp()));
		}else{
			return userFormatter.valueToString(formatter.stringToValue(getEntryTimeStamp()));
		}
	}
	
	
	public String getGrossDate() throws ParseException{
		if(incomingType){
			return userFormatter.valueToString(formatter.stringToValue(getEntryTimeStamp()));
		}else{
			return userFormatter.valueToString(formatter.stringToValue(getExitTimeStamp()));
		}
	}
	
	public String getNetDate() throws ParseException{
		return userFormatter.valueToString(formatter.stringToValue(getExitTimeStamp()));
	}
	
}
