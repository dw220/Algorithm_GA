package ga;

import java.util.Random;

import Global.C;


public class Ga {
	
	// Set Solution
	int solution = 160;
	int noChromosomes;
	int noDimensions;
	int solIter;
	boolean found;
	Chromosome currentBest;
	Population population;
	
	public Ga(int noChromosomes, int noDimensions)
	{
		this.noChromosomes = noChromosomes;
		this.noDimensions  = noDimensions;
		 population = new Population();
		 population.initPop();
	}
	
	
	public Chromosome getBest(){
		return this.currentBest;
	}
	public void setSol(int solution){
		this.solution = solution;
	}
	
	public void optimize(int iterations, Goodness goodness, boolean maximize)
	{
		double minimizeSign = maximize ? -1. : 1.;
		currentBest = population.best;
		found = false;
		
		for(int i=0; i<iterations; i++){
			for(int x=0; x<noChromosomes; x++){
				double fitness = goodness.fitness(population.getChromosome(x).getBits());
				System.out.println(goodness.fitness(population.getChromosome(x).getBits()));
				population.getChromosome(x).setFitness(fitness);
				
				if (minimizeSign * fitness < minimizeSign * goodness.fitness(currentBest.getBits())) {
					currentBest = population.getChromosome(x);
					population.setBest(currentBest);
					if ( Math.abs(fitness - solution) < 0.001 ) {
							solIter = i + 1;
							found = true;
							return;
					} 
				}	
			}
			//Genetic operations		
			//Elitisim carry over strongest gene which is the current fitessest
		
			population = population.nextGen();
			System.out.println("Generation no: " + i + " " + this.currentBest + " " + goodness.fitness(currentBest.getBits()));
		}
	}
	

	
	public void setBest(Chromosome newBest){
		this.currentBest = newBest;
	}
	

	
	/**
	 * Tournament for the population
	 */

	
	
	
	public int getSolIter(){
		return this.solIter;
	}
	
	public boolean getFound(){
		return this.found;
	}
}