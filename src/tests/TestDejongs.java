package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import Global.SphereFunction;
import Global.SphereFunctionN3;
import Global.SumIntFunctionN5;
import ga.Ga;
import ga.Population;

public class TestDejongs {
	
	
	@Test
	public void testIntN5Minimize() {
		
		long time = 0;
		for(int j=0; j < 10; j++){
			long start = System.currentTimeMillis();
			Ga bpso = new Ga(40, SumIntFunctionN5.NUM_DIMENSIONS);
			SumIntFunctionN5 goodness =  new SumIntFunctionN5();
			bpso.optimize(10000, goodness, false);		
			for (int i=0; i < SumIntFunctionN5.VARIABLES; i++) {
				assertTrue( Math.abs(SumIntFunctionN5.MIN - goodness.getN(0)) < 0.5);
			}
			long end = System.currentTimeMillis();
			time += end-start;
		}
		System.out.println("Time taken for: " + time/10 + " TestIntN5Minimize ---------");
	}
	
	@Test
	public void testSphereMinimize() {
		
		long time = 0;
		for(int j=0; j < 10; j++){
		long start = System.currentTimeMillis();
		Ga bpso = new Ga(40, SphereFunction.NUM_DIMENSIONS);
		SphereFunction goodness =  new SphereFunction();
		bpso.optimize(1000, goodness, false);
		
//		System.out.println("Particle value: " + Particle.getValue(Particle.bestGlobal()));
//		System.out.println("Particle bit string: " + Arrays.toString(Particle.bestGlobal()));
//		System.out.println("Particle goodness: " + goodness.getGoodness(Particle.bestGlobal()));
		assertTrue( Math.abs(SphereFunction.MIN - goodness.getValue(Population.bestGlobal().getBits())) < 0.5);
		
		long end = System.currentTimeMillis();
		time += end-start;
		}
		System.out.println("Time taken for: " + time/10 + " Test Sphere minimize ---------");

	}
	
	@Test
	public void testSphereMaximize() {
		long time = 0;
		for(int j=0; j < 10; j++){
		long start = System.currentTimeMillis();
		Ga bpso = new Ga(40, SphereFunction.NUM_DIMENSIONS);
		SphereFunction goodness =  new SphereFunction();
		bpso.optimize(1000, goodness, true);
		
//		System.out.println("Particle value: " + Particle.getValue(Particle.bestGlobal()));
//		System.out.println("Particle bit string: " + Arrays.toString(Particle.bestGlobal()));
//		System.out.println("Particle goodness: " + goodness.getGoodness(Particle.bestGlobal()));
		assertTrue( Math.abs(SphereFunction.MAX - goodness.getValue(Population.bestGlobal().getBits())) < 0.5
				    || Math.abs(-SphereFunction.MAX - goodness.getValue(Population.bestGlobal().getBits())) < 0.5);
		
		long end = System.currentTimeMillis();
		time += end-start;

	}
		System.out.println("Time taken for: " + time/10 + " Test Sphere Maximize ---------");
	}	
	
	@Test
	public void testSphereMinimizeN3() {
		long time = 0;
		for(int j=0; j < 10; j++){
		long start = System.currentTimeMillis();
		Ga bpso = new Ga(40, SphereFunctionN3.NUM_DIMENSIONS);
		SphereFunctionN3 goodness =  new SphereFunctionN3();

		bpso.optimize(10000, goodness, false);
//		
//		System.out.println("Particle value: " + Particle.getValue(Particle.bestGlobal()));
//		System.out.println("Particle bit string: " + Arrays.toString(Particle.bestGlobal()));
//		System.out.println("Particle goodness: " + goodness.getGoodness(Particle.bestGlobal()));
//		System.out.println("Particle goodness N1: " + goodness.getN1());
//		System.out.println("Particle goodness N2: " + goodness.getN2());
//		System.out.println("Particle goodness N3: " + goodness.getN3());
		
		assertTrue( Math.abs(SphereFunctionN3.MIN - goodness.getN1()) < 0.5);
		assertTrue( Math.abs(SphereFunctionN3.MIN - goodness.getN2()) < 0.5);
		assertTrue( Math.abs(SphereFunctionN3.MIN - goodness.getN3()) < 0.5);
		long end = System.currentTimeMillis();
		time += end-start;
	}
		System.out.println("Time taken for: " +time/10+ " Test Sphere MinimizeN3 ---------");

	}
}
