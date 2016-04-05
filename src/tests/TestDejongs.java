package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import ga.Ga;
import ga.Population;
import ga.SphereFunction;

public class TestDejongs {

	@Test
	public void testSphereMinimize() {
		Ga bpso = new Ga(40, SphereFunction.NUM_DIMENSIONS);
		SphereFunction goodness =  new SphereFunction();
		bpso.optimize(1000, goodness, false);
		
		System.out.println("Particle value: " + Population.getValue(Population.bestGlobal().getBits()));
		System.out.println("Particle bit string: " + Arrays.toString(Population.bestGlobal().getBits()));
		System.out.println("Particle goodness: " + goodness.fitness(Population.bestGlobal().getBits()));
		System.out.println("----" + Math.abs(SphereFunction.MIN - goodness.getValue(Population.bestGlobal().getBits())));

		assertTrue( Math.abs(SphereFunction.MIN - goodness.getValue(Population.bestGlobal().getBits())) < 0.5);
	}
	
	@Test
	public void testSphereMaximize() {
		Ga bpso = new Ga(40, SphereFunction.NUM_DIMENSIONS);
		SphereFunction goodness =  new SphereFunction();

		bpso.optimize(1000, goodness, true);
		
		System.out.println("Particle value: " + Population.getValue(Population.bestGlobal().getBits()));
		System.out.println("Particle bit string: " + Arrays.toString(Population.bestGlobal().getBits()));
		System.out.println("Particle goodness: " + goodness.fitness(Population.bestGlobal().getBits()));
		System.out.println("----" + Math.abs(SphereFunction.MAX - goodness.fitness(Population.bestGlobal().getBits())));
		assertTrue( Math.abs(SphereFunction.MAX - goodness.fitness(Population.bestGlobal().getBits())) < 0.5
				    || Math.abs(-SphereFunction.MAX - goodness.getValue(Population.bestGlobal().getBits())) < 0.5);
	}
}
