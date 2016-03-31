package ga;
import java.util.Random;

import Global.C;

public class Chromosome {
	
	private boolean[] bits;
	private double fitness = -1;
	
	public Chromosome(int numDimensions)
	{
		bits = new boolean[numDimensions];
	}
	
	public void setFitness(double fitness){
		this.fitness = fitness;
	}
	
	public double getFitness(){
		return this.fitness;
	}

	public boolean[] getBits()
	{
		return this.bits;
	}
	
	public void initChromosome()
	{
		Random rand = new Random();
		for(int i=0; i<bits.length; i++){
			bits[i] = rand.nextBoolean();
		}
	}
	
	public void mutate()
	{
		for(boolean b : bits){
			if(C.randomDouble() <= C.mutation)
			b = (b) ? false : true;
		}
	}
	
	public void setBits(boolean[] bits){
		this.bits = bits;
	}
	
	
	public String toString()
	{
		String s = "";
		for(Boolean b: bits){
			s += (b) ? 1 : 0;
		}
		return s;
	}
	
	
}
