package in.weighpro.dataComponents;

import in.weighpro.databaseQueryHandler.InsertEntryQuery;
import in.weighpro.databaseQueryHandler.RetrieveEntryQuery;
import in.weighpro.databaseQueryHandler.UpdateEntryQuery;
import in.weighpro.formatters.DateFormatter;
import in.weighpro.formatters.UserDateFormatter;

import java.sql.SQLException;
import java.text.ParseException;

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
	
	
	public Entry(int index) throws SQLException {
		
		RetrieveEntryQuery retEntry = new RetrieveEntryQuery(index);
		this.index = retEntry.getIndex();
		this.vehicle = new Vehicle(retEntry.getVehicleIndex());
		this.customer = new Customer(retEntry.getCustomerIndex());
		this.material = new Material(retEntry.getMaterialIndex());
		this.weight = new Weight(retEntry.getTareWeight(),
				retEntry.getGrossWeight(), retEntry.getNetWeight(),
				retEntry.getIncomingType());
		this.amount = retEntry.getAmount();
		this.time = new TimeDetails(retEntry.getEntryTimeStamp(),
				retEntry.getExitTimeStamp());
		this.status = retEntry.getStatus();
		this.incomingType = retEntry.getIncomingType();
		
	}

	public Entry(String vehicleNumber, String customer, String material,
			int weight, boolean tareWeightFlag, int amount) throws SQLException {
		this.vehicle = new Vehicle(vehicleNumber);
		this.customer = new Customer(customer);
		this.material = new Material(material);
		this.weight = new Weight(weight, tareWeightFlag);
		this.time = new TimeDetails();
		status = false;
		incomingType = !tareWeightFlag;
		this.amount = amount;
		InsertEntryQuery insQuery = new InsertEntryQuery(vehicle,
				this.customer, this.material, amount, this.weight, time);
		index = insQuery.getIndex();
	}

	public int getAmount() {
		return this.amount;
	}

	public String getCustomer() {
		return this.customer.getValue();
	}

	public String getEntryTimeStamp() {
		return this.time.getEntryTimeStamp();
	}

	public String getExitTimeStamp() {
		return this.time.getExitTimeStamp();
	}

	public String getGrossWeight() {
		if(this.weight.getGrossWeight()==-1){
			return "";
		}else{
			return Integer.toString(this.weight.getGrossWeight());
		}
	}

	public boolean getIncomingType() {
		return this.incomingType;
	}

	public int getIndex() {
		return this.index;
	}

	public String getMaterial() {
		return this.material.getValue();
	}

	public String getNetWeight() {
		if(this.weight.getNetWeight()==-1){
			return "";
		}else{
			return Integer.toString(this.weight.getNetWeight());
		}
	}

	public boolean getStatus() {
		return this.status;
	}

	public String getTareWeight() {
		if(this.weight.getTareWeight()==-1){
			return "";
		}else{
			return Integer.toString(this.weight.getTareWeight());
		}
	}

	public String getVehicleNumber() {
		return this.vehicle.getValue();
	}

	public void updateEntry(int weight) throws SQLException {
		this.weight.secondWeight(weight);
		this.incomingType = this.getIncomingType();
		time.setExitTimeStamp();
		new UpdateEntryQuery(this.index, this.weight, this.time);
		this.status = true;
	}

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
