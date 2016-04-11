package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Global.Card_Sort;
import ga.Ga;

public class Card_Sort_test {

	@Test
	public void test() {
		
		Ga ga 		   = new Ga(10, Card_Sort.BIT_LENGTH);
		Card_Sort sort = new Card_Sort();
		ga.setSol(0);
		ga.optimize(1000, sort, false);
		
	}

}
