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
	
	public void initPop(int noChromosomes, int noDimensions){
		chromosomes = new Chromosome[noChromosomes];
		for(int i=0; i<noChromosomes; i++){
			Chromosome c = new Chromosome(noDimensions);
			c.initChromosome();
			chromosomes[i] = c;
		}
		
		best = getFitest(chromosomes);
	}
	
	public Chromosome getFitest(Chromosome[] c){
		Chromosome fittest = c[0];
		for(int i=0; i<c.length;i++){
			if(Math.abs(c[i].getFitness()) <= Math.abs(fittest.getFitness())){
				fittest = chromosomes[i];
			}
		}
		return fittest;
	}
	
	public Chromosome getBest(){
		return best;
	}
	
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

        // Crossover population
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
	 * Cross over on the chromosomes
	 */
	public Chromosome crossOver(Chromosome a, Chromosome b){
        Chromosome newSol = new Chromosome(noDimensions);
        if(a.getFitness() == b.getFitness())
        {
        	newSol.initChromosome();
        	return newSol;
        }
        // Loop through genes
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
}
