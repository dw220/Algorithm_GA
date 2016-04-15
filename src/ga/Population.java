package ga;

import java.util.Random;

public class Population {
	
	private Chromosome[] chromosomes;
	static Chromosome best;
	int noDimensions;
	int noChromosomes;
	
	public Population(int noChromosomes, int noDimensions){
		initPop( noChromosomes, noDimensions );
		this.noDimensions = noDimensions;
		this.noChromosomes = noChromosomes;
	}
	
	/**
	 * Create initial population to use within the algorithm
	 * @param noChromosomes number of population members to use within the algorithm
	 * @param noDimensions number of dimensions used for the bit pattern
	 */
	public void initPop(int noChromosomes, int noDimensions){
		chromosomes = new Chromosome[noChromosomes];
		for(int i=0; i<noChromosomes; i++){
			Chromosome c = new Chromosome(noDimensions);
			c.initChromosome();
			chromosomes[i] = c;
		}
		
		best = getFitest(chromosomes);
	}
	
	/**
	 * Selects the current fitest valued chromosome out of the population used for the elitism method
	 * @param c array of chromosomes to search
	 * @return the strongest chromosome for the current solution
	 */
	public Chromosome getFitest(Chromosome[] c){
		Chromosome fittest = c[0];
		for(int i=0; i<c.length;i++){
			if(Math.abs(c[i].getFitness()) <= Math.abs(fittest.getFitness())){
				fittest = chromosomes[i];
			}
		}
		return fittest;
	}
	
	/**
	 * return the current best Chromosome found
	 * @return
	 */
	public Chromosome getBest(){
		return best;
	}
	
	/**
	 * Get a particular chromosome in the population
	 * @param pos
	 * @return
	 */
	public Chromosome getChromosome(int pos){
		return chromosomes[pos];
	}
	
	public void setChromosome(int pos, Chromosome c){
		chromosomes[pos] = c;
	}
	
	public void setBest(Chromosome best){
		Population.best = best;
	}
	
	
	
	public void nextGen(){
        Chromosome[] newPopulation = new Chromosome[noChromosomes];
        boolean elitism = true;
        // Keep our best individual
        
        if (elitism) 
        {
        	Chromosome c = new Chromosome(noDimensions);
        	c.setBits(best.getBits());
            newPopulation[0] = c;
            //newPopulation.setBest(newPopulation.getChromosome(0));
        }

        //Set the offset so the current best doesnt get overridden
        int elitismOffset = (elitism) ? 1 : 0;

        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < this.chromosomes.length; i++) 
        {
        	Chromosome newIndiv = new Chromosome(noDimensions);
        	
            Chromosome indiv1 = tournament();
            Chromosome indiv2 = tournament();
            
            newIndiv = crossOver(indiv1, indiv2);
            newPopulation[i] = newIndiv;
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.length; i++) {
            newPopulation[i].mutate();
        }
        this.chromosomes = newPopulation;
        getFitest(chromosomes);
	}
	
	public int getSize(){
		return chromosomes.length;
	}
	
	/**
	 * Return true if chromosomes have the same bit pattern, this is to stop premature convergence
	 * 
	 */
	public boolean checkChromosomes(Chromosome a, Chromosome b){
		boolean check = true;
		for(int i=0; i < a.getBits().length; i++){
			if(a.getBits()[i] != b.getBits()[i]){
				check = false;
			}
		}
		return check;
	}
	
	/**
	 * Cross over on the chromosomes
	 */
	public Chromosome crossOver(Chromosome a, Chromosome b){
        Chromosome newSol = new Chromosome(noDimensions);
        if(checkChromosomes(a, b))
        {
        	newSol.initChromosome();
        	return newSol;
        }
//        // Loop through genes
        for (int i = 0; i < a.getBits().length; i++) {
            // Crossover
            if (Math.random() <= 0.5) {
            	boolean[] bit = a.getBits();
                newSol.setBit(i, bit[i]);
            } else {
            	boolean[] bit = b.getBits();
            	newSol.setBit(i, bit[i]);
            }
        }
        newSol.mutate();
        return newSol;
	}	
	
	public double randomDouble(){
		Random rand = new Random();
		return rand.nextDouble();
	}
	
	public static double getValue(boolean[] bits) {
		int n = 0;
		for (int i = 0; i < bits.length; i++) {
			n = (n << 1) + (bits[i] ? 1 : 0);
		}
		return n;
	}
	
	public static Chromosome bestGlobal(){
		return best;
	}
	
	public Chromosome tournament(){
		Chromosome c;
		Chromosome[] tournament = new Chromosome[5];
		
		for(int i=0;i<tournament.length; i++)
		{
			 int randomId = (int) (Math.random() * chromosomes.length);
			 tournament[i] = chromosomes[randomId];
		}
		c = getFitest(tournament);
		return c;
	}
	
	
	/**
	 * Roulette selection, had to work out a way in which to deal with negative values.
	 */
	public Chromosome rouletteSelection(){
		  	float totalScore = 0;
		    float runningScore = 0;

		    for (Chromosome c : chromosomes){
		        totalScore += (c.getFitness() + 2000);
		    }
		    float rnd = (float) (Math.random() * totalScore);
		    for (Chromosome c : chromosomes){   
		        if (    rnd>=runningScore &&
		                rnd<=runningScore+(c.getFitness() + 2000)){
		            return c;
		        }
		        runningScore+=(c.getFitness() + 2000);
		    }

		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
}
