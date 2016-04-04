package ga;

public class SphereFunction implements Goodness {

	public static final double MIN = 0.;
	public static final double MAX = 5.12; //-5.12 or 5.12
	public static final int NUM_DIMENSIONS = 10;
	private double value;
	
	@Override
	public double fitness(boolean[] position) {	
		value = getValue(position);
		return value * value;
	}
	
	public double getValue(boolean[] position) {
		return Population.getValue(position) / 100 - 5.12;
	}

}