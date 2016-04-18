package ga;
import java.util.Random;

import Global.C;

public class Chromosome {
	
	private boolean[] bits;
	private double fitness = -1;
	private double prob = 0.0;
	
	public Chromosome(int numDimensions)
	{
		bits = new boolean[numDimensions];
	}
	
	/**
	 * Set the fitness of the Chromosome, used in the main Ga class
	 * when evaluating the population
	 * @param fitness
	 */
	public void setFitness(double fitness){
		this.fitness = fitness;
	}
	
	/**
	 * Get the fitness of the Chromosome based on assesment
	 * @return double value of fitness
	 */
	public double getFitness(){
		return this.fitness;
	}
	
	/**
	 * Return bits associsated with the Chromosome
	 * @return boolean array
	 */
	public boolean[] getBits()
	{
		return this.bits;
	}
	
	/**
	 * Initiate Chromosome with random boolean values, used at the start
	 * of the algorithm
	 */
	public void initChromosome()
	{
		Random rand = new Random();
		for(int i=0; i<bits.length; i++){
			bits[i] = rand.nextBoolean();
		}
	}
	
	/**
	 * Set the bit at the current position, this can be used for crossover methods
	 * and the mutation.
	 * @param i position in array
	 * @param b boolean value you wish to be set at pos i
	 */
	public void setBit(int i, boolean b){
		this.bits[i] = b;
	}
	
	/**
	 * Mutate the current chromosome, the method generates a random double
	 * between 0 - 1 and checks to the current mutation rate stored in the 
	 * global variable class.
	 */
	public void mutate()
	{
		for(int i=0;i<bits.length;i++){
			if(randomDouble() < C.mutation){
			bits[i] = (bits[i]) ? false : true;
			}
		}
	}
	
	/**
	 * Set the boolean array as a whole
	 * @param bits boolean array to set
	 */
	public void setBits(boolean[] bits){
		this.bits = bits;
	}
	
	/**
	 * Generate random double between 0-1
	 * @return
	 */
	public double randomDouble(){
		Random rand = new Random();
		return rand.nextDouble();
	}
	

	/**
	 * Set the probablity of the chromosome, this method is used within the 
	 * roulette selection method
	 * @param prob probaillity of being selected
	 */
	public void setProb(double prob){
		this.prob = prob;
	}
	
	/**
	 * Return the probabillity of the chromosome being selected.
	 * @return double value of being selected
	 */
	public double getProb(){
		return this.prob;
	}
	
	/**
	 * Return string when the object is printed, saves adding other methods else where
	 * that print out the objects bit values
	 */
	public String toString()
	{
		String s = "";
		for(Boolean b: bits){
			s += (b) ? 1 : 0;
		}
		return s;
	}
	
}
