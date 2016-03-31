package Global;

import java.util.Random;

public class C 
{
	
	// Genetic operations rates
	static public final double crossOver = 0.7;
	static public final double mutation  = 0.015;
    static public final boolean elitism  = true;
	static public final double    uniformRate = 0.5;
	
	//Random number used for operators
	static public double randomDouble()
	{
		Random rand = new Random();
		return rand.nextDouble();
	}
	
	
	
}
