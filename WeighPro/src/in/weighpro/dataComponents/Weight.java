/* This class provides the
*pluggable weight object
*which is used in the storage and retrieval
*of the entries

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.dataComponents;


//This class constructs a weight object by storing the tare weight,gross weight,net weight of an entry
//It also tracks the type of weight recorded during the first entry to keep track of tare date and gross date
//Also makes sure that operator mistakes when entering the weight are rectified


public class Weight {

	private boolean incomingType;
	private int tareWeight = -1, grossWeight = -1, netWeight = -1;//All weights initialized as -1
								     //-1 indicates that the weight has not been recorded 
								     //yet

//Constructor to initialize the weight object during the first entry
	public Weight(int weight, boolean tareWeightFlag) {//takes weight and flag as the arguments
							//the flag indicates whether the weight recorded was tare or gross
							//tare if flag is true and false otherwise
		if (tareWeightFlag) {//if the flag is true then the weight is tare weight
			this.tareWeight = weight;//setting the tare weight of the weight object
		} else {
			this.grossWeight = weight;//else setting the gross weight of the object
		}
		incomingType = !tareWeightFlag;//this flag tracks the incoming type of the vehicle
						//if true gross weight is recorded first
						//if false tare weight is recorder first

	}


//Constructor to initialize the weight object when all three weights are known
//Generally used when the weights are retrieved from the database
	public Weight(int tareWeight, int grossWeight, int netWeight,
			boolean incomingType) {
		this.tareWeight = tareWeight;//setting the tare weight of the weight object
		this.grossWeight = grossWeight;//setting the gross weight of the weight object
		this.netWeight = netWeight;//setting the net weight of the weight object
		this.incomingType = incomingType;//setting the incoming type of the vehicle
	}


//method to get the gross weight of the weight objcet
	public int getGrossWeight() {
		return grossWeight;
	}

//method to get the incoming type of the vehicle
	public boolean getIncomingType() {
		return incomingType;
	}


//method to get the net weight of the weight object
	public int getNetWeight() {
		return netWeight;
	}

//method to get the tare weight of the weight object
	public int getTareWeight() {
		return tareWeight;
	}

//method to record the weight of the vehicle during the second entry
	public void secondWeight(int weight) {///takes the weight as an argument
		if (tareWeight == -1) {//if tare weight has not been recorded yet
			tareWeight = weight;//set the tare wweight 
			incomingType = true;//incoming type true - gross weight was recorded first
		} else {
			grossWeight = weight;//else set the gross weight
			incomingType = false;//incoming type false - tare weight was recorded first
		}
		netWeight = grossWeight - tareWeight;//netweight is calculated
		if (netWeight < 0) {//if the operator has made an error recording the type of weight
			tareWeight = tareWeight + grossWeight;//interchange the tare and gross weight
			grossWeight = tareWeight - grossWeight;
			tareWeight = tareWeight - grossWeight;
			netWeight = grossWeight - tareWeight;//re calculate the net weight
			incomingType = !incomingType;//reverse the incoming type
		}

	}
}
