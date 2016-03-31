package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import ga.Chromosome;
import ga.Ga;
import ga.ServiceSelectionEx3;

public class ServiceSelectionEx3_Test {

	private double[] costData = new double[] {70, 60, 70, 50}; 
	private double[] costWlan = new double[] {50, 60, 50, 30};  
	private double[] utilities = new double[] {100, 50, 10, 10};
	
	@Test
	public void testCase1() {
		boolean[] bits = new boolean[]{false, false, false, false, false};
		
		ServiceSelectionEx3 serviceSelectionEx3 = new ServiceSelectionEx3(200, costData, costWlan, utilities);
		System.out.println(Arrays.toString(bits) +  ": " + serviceSelectionEx3.fitness(bits));
	}
	
	@Test
	public void testCase2() {
		boolean[] bits = new boolean[]{true, false, false, false, false};
		
		ServiceSelectionEx3 serviceSelectionEx3 = new ServiceSelectionEx3(200, costData, costWlan, utilities);
		System.out.println(Arrays.toString(bits) + ": " + serviceSelectionEx3.fitness(bits));
	}
	
	@Test
	public void testCase3() {
		boolean[] bits = new boolean[]{true, false, true, false, false};
		
		ServiceSelectionEx3 serviceSelectionEx3 = new ServiceSelectionEx3(200, costData, costWlan, utilities);
		System.out.println(Arrays.toString(bits) + ": " + serviceSelectionEx3.fitness(bits));
	}
	
	@Test
	public void testCase4() {
		boolean[] bits = new boolean[]{true, false, true, true, false};
		
		ServiceSelectionEx3 serviceSelectionEx3 = new ServiceSelectionEx3(60, costData, costWlan, utilities);
		System.out.println(Arrays.toString(bits) + ": " + serviceSelectionEx3.fitness(bits));
	}
	
	@Test
	public void testCase5() {
		boolean[] bits = new boolean[]{true, false, true, true, true};
		
		ServiceSelectionEx3 serviceSelectionEx3 = new ServiceSelectionEx3(200, costData, costWlan, utilities);
		System.out.println(Arrays.toString(bits) + ": " + serviceSelectionEx3.fitness(bits));
	}
	
	@Test
	public void testCase3BPSO() {
		double sumTimes = 0;
		double iterations = 0;
		double found = 0;
		long max = 10000;
		Chromosome best = null;
		
		for (int i = 1; i <= max; i++) {
			Ga bpso = new Ga(5, ServiceSelectionEx3.NUM_DIMENSIONS);
			
			ServiceSelectionEx3 serviceSelectionEx3 = new ServiceSelectionEx3(
					200, costData, costWlan, utilities);

			long start = System.currentTimeMillis();

			//Change to double, 160 is an int not a double
			bpso.setSol(160);
			bpso.optimize(1000, serviceSelectionEx3, true);

			
			found += (bpso.getFound() ? 1 : 0);
			iterations += bpso.getSolIter();
			long end = System.currentTimeMillis() - start;
			
			sumTimes += end;
			best = bpso.getBest();
//			System.out.println("Particle value: " + Particle.getValue(Particle.bestGlobal()));
//			System.out.println("Particle bit string: " + Arrays.toString(Particle.bestGlobal()));
//			System.out.println("Particle goodness: " + serviceSelectionEx3.getGoodness(Particle.bestGlobal()));
			
		}
		
		System.out.println(best);
		System.out.println("Time spend: " + sumTimes/max);
		System.out.println("Iterations: " + iterations/max);
		System.out.println("Success: " + found);
	}

}
