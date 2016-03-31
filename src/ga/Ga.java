package ga;

import java.util.Random;

import Global.C;

public class Ga {
	
	// Set Solution
	int solution = 160;
	Chromosome[] chromosomes;
	int noChromosomes;
	int noDimensions;
	int solIter;
	boolean found;
	Chromosome currentBest;
	
	public Ga(int noChromosomes, int noDimensions){
		this.noChromosomes = noChromosomes;
		this.noDimensions  = noDimensions;
		initPop();
	}
	
	public void initPop(){
		chromosomes = new Chromosome[noChromosomes];
		for(int i=0; i<noChromosomes; i++){
			Chromosome c = new Chromosome(noDimensions);
			c.initChromosome();
			chromosomes[i] = c;
		}
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
		currentBest = chromosomes[0];
		found = false;
		
		for(int i=0; i<iterations; i++){
			for(int x=0; x<noChromosomes; x++){
				double fitness = goodness.fitness(chromosomes[x].getBits());
				System.out.println(fitness);
				chromosomes[x].setFitness(fitness);
				if (minimizeSign * fitness < minimizeSign * goodness.fitness(currentBest.getBits())) {
					currentBest = chromosomes[x];
					if ( Math.abs(fitness - solution) < 0.001 ) {
							solIter = i + 1;
							found = true;
							return;
					} 
				}	
			}
			//Genetic operations
			
			
			//Elitisim carry over strongest gene which is the current fitessest
			double operations = C.randomDouble();
			Chromosome[] newGen = new Chromosome[chromosomes.length];
			newGen[0] = this.currentBest;
			
			
			
			for(int j=1; j<newGen.length; j++){
				Chromosome a = getRandom(); 
				Chromosome b = getRandom();
				Chromosome c = getRandom();
				Chromosome d = getRandom();
				
				Chromosome winner    = tournament(a,b);
				Chromosome winnerTwo = tournament(c,d);
				if(operations < C.mutation)
				{
					Chromosome newWinner = tournament(winner,winnerTwo);
					newWinner.mutate();
					newGen[j] = newWinner;
				} 
				else if( operations < C.crossOver)
				{
					crossOver(winner, winnerTwo);
					Chromosome newWinner = crossOver(winner, winnerTwo);
					newGen[j] = newWinner; 
				} else
				{
					newGen[j] = currentBest;
				}
		    }
			chromosomes = newGen;
			System.out.println("Generation no: " + i + " " + this.currentBest + " " + goodness.fitness(currentBest.getBits()));
		}
	}
	
	public void setBest(Chromosome newBest){
		this.currentBest = newBest;
	}
	
	/**
	 * Tournament for the population
	 */
	public Chromosome tournament(Chromosome a, Chromosome b){
		Chromosome c = (Math.abs(a.getFitness()) < Math.abs(b.getFitness())) ? a : b;
		return c;
	}
	
	/**
	 * Cross over on the chromosomes
	 */
	public Chromosome crossOver(Chromosome a, Chromosome b){
		boolean[] temp = new boolean[a.getBits().length];
		Chromosome c = new Chromosome(noDimensions);
		for(int i=0;i<noDimensions;i++){
			double point = 0.4;
			if(point*10 < i){
				temp[i] = a.getBits()[i];
			} else
				temp[i] = b.getBits()[i];
		}
		c.setBits(temp);
		return c;
	}	
	
	public Chromosome getRandom() {
	    int rnd = new Random().nextInt(chromosomes.length);
	    return chromosomes[rnd];
	}
	
	public int getSolIter(){
		return this.solIter;
	}
	
	public boolean getFound(){
		return this.found;
	}
}