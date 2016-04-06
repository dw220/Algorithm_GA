package Global;

import ga.Goodness;
import ga.Population;

public class SphereFunctionN3 implements Goodness {

	public static final int NUM_DIMENSIONS = 30;
	public static final double MIN = 0.;
	public static final double MAX = 5.12 * 5.12; //-5.12 or 5.12
	
	private boolean[] n1 = new boolean[10];
	private boolean[] n2 = new boolean[10];
	private boolean[] n3 = new boolean[10];
	
	@Override
	public double fitness(boolean[] bits) {	
		System.arraycopy(bits, 0, n1, 0, 10);
		System.arraycopy(bits, 10, n2, 0, 10);
		System.arraycopy(bits, 20, n3, 0, 10);
		return getValue(n1) + getValue(n2) + getValue(n3);
	}
	
	private double getValue(boolean[] bits) {
		double value = Population.getValue(bits) / 100 - 5.12;
		return value * value;
	}
	
	public double getN1() {
		return Population.getValue(n1) / 100 - 5.12;
	}
	
	public double getN2() {
		return Population.getValue(n2) / 100 - 5.12;
	}
	
	public double getN3() {
		return Population.getValue(n3) / 100 - 5.12;
	}
}