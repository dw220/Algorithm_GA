package ga;

import Global.C;

public class Population {
	
	Chromosome[] chromosomes;
	Chromosome best;
	
	public void initPop(){
		chromosomes = new Chromosome[10];
		for(int i=0; i<10; i++){
			Chromosome c = new Chromosome(5);
			c.initChromosome();
			chromosomes[i] = c;
		}
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
	
	
	public Chromosome getChromosome(int pos){
		return chromosomes[pos];
	}
	
	public void setBest(Chromosome best){
		this.best = best;
	}
	
	public Population nextGen(){
		double operations   = C.randomDouble();
		Chromosome[] newGen = new Chromosome[chromosomes.length];
		newGen[0] 			= best;
		
		for(int j=0; j<newGen.length; j++){
			Chromosome winner    = tournament();
			Chromosome winnerTwo = tournament();
			
			if(C.randomDouble() < C.mutation)
			{
				Chromosome newWinner = tournament();
				newWinner.mutate();
				newGen[j] = newWinner;
			} 
			else if( C.randomDouble() < C.crossOver)
			{
				crossOver(winner, winnerTwo);
				Chromosome newWinner = crossOver(winner, winnerTwo);
				newGen[j] = newWinner; 
			} else
			{
				newGen[j] = best;
			}
	    }
		chromosomes = newGen;
		return this;
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
                newSol.setBit(i, a.getBits()[i]);
            } else {
            	newSol.setBit(i, b.getBits()[i]);
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
