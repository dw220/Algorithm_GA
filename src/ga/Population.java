package ga;

import Global.C;

public class Population {
	
	private Chromosome[] chromosomes;
	Chromosome best;
	
	public Population(){
		initPop();
	}
	
	public void initPop(){
		chromosomes = new Chromosome[10];
		for(int i=0; i<10; i++){
			Chromosome c = new Chromosome(5);
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
	
	
	public Chromosome getChromosome(int pos){
		return chromosomes[pos];
	}
	
	public void setChromosome(int pos, Chromosome c){
		chromosomes[pos] = c;
	}
	
	public void setBest(Chromosome best){
		this.best = best;
	}
	
	public Population nextGen(){
        Population newPopulation = new Population();
        boolean elitism = false;
        // Keep our best individual
        
        if (elitism) {
            newPopulation.setChromosome(0, best);
            newPopulation.setBest(best);
        }

        // Crossover population
        int elitismOffset = (elitism) ? 1 : 0;

        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < this.chromosomes.length; i++) 
        {
        	Chromosome newIndiv = new Chromosome(5);
            Chromosome indiv1 = this.best;
            Chromosome indiv2 = tournament();
            
            newIndiv = crossOver(indiv1, indiv2);
            newPopulation.setChromosome(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.getSize(); i++) {
            newPopulation.getChromosome(i).mutate();
        }
        return newPopulation;
	}
	
	public int getSize(){
		return chromosomes.length;
	}
	
	/**
	 * Cross over on the chromosomes
	 */
	public Chromosome crossOver(Chromosome a, Chromosome b){
        Chromosome newSol = new Chromosome(5);
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
        return newSol;
	}	
	
	
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
}
