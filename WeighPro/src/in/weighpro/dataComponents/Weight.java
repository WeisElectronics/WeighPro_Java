package in.weighpro.dataComponents;

public class Weight {

	private boolean incomingType;
	private int tareWeight = -1, grossWeight = -1, netWeight = -1;

	public Weight(int weight, boolean tareWeightFlag) {
		if (tareWeightFlag) {
			this.tareWeight = weight;
		} else {
			this.grossWeight = weight;
		}
		incomingType = !tareWeightFlag;

	}

	public Weight(int tareWeight, int grossWeight, int netWeight,
			boolean incomingType) {
		this.tareWeight = tareWeight;
		this.grossWeight = grossWeight;
		this.netWeight = netWeight;
		this.incomingType = incomingType;
	}

	public int getGrossWeight() {
		return grossWeight;
	}

	public boolean getIncomingType() {
		return incomingType;
	}

	public int getNetWeight() {
		return netWeight;
	}

	public int getTareWeight() {
		return tareWeight;
	}

	public void secondWeight(int weight) {
		if (tareWeight == -1) {
			tareWeight = weight;
			incomingType = true;
		} else {
			grossWeight = weight;
			incomingType = false;
		}
		netWeight = grossWeight - tareWeight;
		if (netWeight < 0) {
			tareWeight = tareWeight + grossWeight;
			grossWeight = tareWeight - grossWeight;
			tareWeight = tareWeight - grossWeight;
			netWeight = grossWeight - tareWeight;
			incomingType = !incomingType;
		}

	}
}
