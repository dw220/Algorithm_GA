package ga;



public class Ga {
	
	// Set Solution
	int solution = 160;
	int noChromosomes;
	int noDimensions;
	int solIter;
	boolean found;
	Population population;
	
	public Ga(int noChromosomes, int noDimensions)
	{
		this.noChromosomes = noChromosomes;
		this.noDimensions  = noDimensions;
		population = new Population(noChromosomes, noDimensions);
		 
	}
	
	public void setSol(int solution){
		this.solution = solution;
	}
	
	public void optimize(int iterations, Goodness goodness, boolean maximize)
	{
		double minimizeSign = maximize ? -1. : 1.;
		found = false;
		solIter = iterations;
		
		for(int i=0; i<iterations; i++){
			System.out.println("Generation no: " + i);
			for(int x=0; x<noChromosomes; x++){
				double fitness = goodness.fitness(population.getChromosome(x).getBits());
				population.getChromosome(x).setFitness(fitness);
				if (minimizeSign * fitness <= minimizeSign * goodness.fitness(population.getBest().getBits())) {
					population.setBest(population.getChromosome(x));
					if ( Math.abs(fitness - solution) < 0.001 ) {
							solIter = i + 1;
							found = true;
							//System.out.println("Optimal found");
							return;
					} 
				}
				
			}
			//Genetic operations		
			//Elitisim carry over strongest gene which is the current fitessest
			population.nextGen();
			//System.out.println("Generation no: " + i + " " + this.currentBest + " " + goodness.fitness(currentBest.getBits()));
		}
	}
	
	
	public int getSolIter(){
		return this.solIter;
	}
	
	public boolean getFound(){
		return this.found;
	}
}