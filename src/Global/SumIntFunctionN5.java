package Global;

import ga.Goodness;
import ga.Population;

public class SumIntFunctionN5 implements Goodness {

	public static final int NUM_DIMENSIONS = 50;
	public static final int VARIABLE_SPACE = 10;
	public static final int VARIABLES = 5;
    public static final double MIN = 0.;
	public static final double MAX = 5.12 * 5.12; //-5.12 or 5.12
	
	private boolean[][] n = new boolean[VARIABLES][];
	
	public SumIntFunctionN5() {
		for (int i = 0; i < VARIABLES; i++) {
			n[i] = new boolean[VARIABLE_SPACE];
		}
	}
	
	@Override
	public double fitness(boolean[] bits) {	
		int start = 0;
		double value = 0;
		
		for (int i = 0; i < VARIABLES; i++) {
			System.arraycopy(bits, start, n[i], 0, VARIABLE_SPACE);
			start += VARIABLE_SPACE;
			value += getValue(n[i]);
		}
		return value;
	}
	
	private double getValue(boolean[] bits) {
		double value = Population.getValue(bits) / 100 - 5.12;
		return value * value;
	}
	
	public double getN(int i) {
		return Population.getValue(n[i]) / 100 - 5.12;
	}
}