package Global;

import ga.Goodness;



public class Card_Sort implements Goodness {

	// Length of the problem
	public static int BIT_LENGTH = 10;
	
	private double SUMTARG = 36;
	private double PRODTARG = 360;
	 
	@Override
	public double fitness(boolean[] c) {
		int sum = 0;
		int prod= 1;
		
		for(int i=0; i<c.length; i++){
			if(c[i])
			{
				sum += (1 + i);
			}
			else 
			{
				prod *= (1+1);
			}
		}
		
		double sum_error  = (sum - SUMTARG) / SUMTARG;
		double prod_error = (prod - PRODTARG) / PRODTARG;
		
		double combinedError = Math.abs(sum_error) + Math.abs(prod_error);
		return combinedError;
	}

}
