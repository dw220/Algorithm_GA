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
//				chromosomes[x].setFitness(fitness);
//				if (minimizeSign * fitness < minimizeSign * goodness.fitness(currentBest.getBits())) {
//					currentBest = chromosomes[x];
//					if ( Math.abs(fitness - solution) < 0.001 ) {
//							solIter = i + 1;
//							found = true;
//							return;
//					} 
//				}	
			}
			//Genetic operations		
			//Elitisim carry over strongest gene which is the current fitessest
		
			nextGen(this.chromosomes);
			System.out.println("Generation no: " + i + " " + this.currentBest + " " + goodness.fitness(currentBest.getBits()));
		}
	}
	
	public void setBest(Chromosome newBest){
		this.currentBest = newBest;
	}
	
	public void nextGen(Chromosome[] gen){
		double operations   = C.randomDouble();
		Chromosome[] newGen = new Chromosome[chromosomes.length];
		newGen[0] 			= this.currentBest;
		
		for(int j=1; j<newGen.length; j++){
			Chromosome winner    = tournament();
			Chromosome winnerTwo = tournament();
			
			if(operations < C.mutation)
			{
				Chromosome newWinner = tournament();
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
	}
	
	/**
	 * Tournament for the population
	 */
	public Chromosome tournament(){
		Chromosome c;
		Chromosome[] tournament = new Chromosome[4];
		
		for(int i=0;i<tournament.length; i++){
			 int randomId = (int) (Math.random() * chromosomes.length);
			 tournament[i] = chromosomes[randomId];
			 
		}
		c = getFitest(tournament);
		return c;
	}
	
	public Chromosome getFitest(Chromosome[] c){
		Chromosome fittest = c[0];
		for(int i=0; i<c.length;i++){
			if(Math.abs(c[i].getFitness()) <= Math.abs(fittest.getFitness())){
				fittest =chromosomes[i];
			}
		}
		return fittest;
	}
	
	/**
	 * Cross over on the chromosomes
	 */
	public Chromosome crossOver(Chromosome a, Chromosome b){
        Chromosome newSol = new Chromosome(this.noDimensions);
        // Loop through genes
        for (int i = 0; i < a.getBits().length; i++) {
            // Crossover
            if (Math.random() <= 0.5) {
                newSol.setBit(i, a.getBits()[i]);
            } else {
            	newSol.setBit(i, b.getBits()[i]);
            }
        }
        return newSol;
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