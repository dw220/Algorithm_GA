package ga;

public class ServiceSelectionEx3 implements Goodness {

	public static final int NUM_DIMENSIONS = 5;
	public static final int DATA = 0;
	public static final int WLAN = 1;
	public static final int HEALTH_APP = 2;
	public static final int VIDEO = 3;
	public static final int FRIENDS = 4;
	
	public static final int UT_HEALTH_APP = 0;
	public static final int UT_VIDEO = 1;
	public static final int UT_FRIENDS = 2; 
	
	//bit 0 Data
	//bit 1 WLAN
	//bit 2 Health app 70, 50
	//bit 3 VideoStreaming 60, 60
    //bit 4 friends service 70, 50
	
	private double cost;
	private double[] costData; 
	private double[] costWlan;
	private double[] utilities;
	
	public ServiceSelectionEx3(double cost, double[] costData, double[] costWlan, double[] utilities) {
		if (utilities == null || utilities.length != 4 || costData.length !=4 || costWlan.length != 4) {
			throw new RuntimeException("Utilities array should have 4 elements");
		}
		this.cost = cost;
		this.costData = costData; 
		this.costWlan = costWlan;
		this.utilities = utilities;
	}
	
	@Override
	public double fitness(boolean[] bits) {	
		double utility = 0.0;
		double rcost = 0.0;
		double[] resourceCost = null;
		
		if (!bits[HEALTH_APP]) {
			return -2000.0;
		}
		if (bits[DATA] && bits[WLAN]) {
			return -500;
		}
		if (!(bits[DATA] || bits[WLAN])) {
			return -1000;
		}
		if (bits[DATA]) {
			resourceCost = costData;
		} else if (bits[WLAN]) {
			resourceCost = costWlan;
		} 
		
		for (int i = HEALTH_APP; i <= FRIENDS; i++) {
			if (bits[i]) {
				utility += utilities[i - HEALTH_APP];
				rcost += resourceCost[i - HEALTH_APP];
			}
		}
		
		if (rcost < cost) {
			return utility;
		}
		
		return utility * 0.50;
	}
}