package com.corecursion.examples;

import java.math.BigInteger;
import java.util.Iterator;

import com.corecursion.Codata;
import com.corecursion.CodataFunction;

public class CorecursiveFactorialGenerator {
	
	private final static Codata<BigInteger> factorials = new Codata<BigInteger>(
				new CodataFunction<BigInteger>() {

					@Override
					public BigInteger getNext() {
						return lastValue().multiply(BigInteger.valueOf(lastIndex() + 1));
					}
					
				},
				1,
				BigInteger.valueOf(1), BigInteger.valueOf(2)
			);

	public static void main(final String... args) {
		printFirstNFactorials(11);
		System.out.println("-----------------------");
		System.out.println(String.format("The 100th factorial is %s", getNthFactorial(100)));
	}
	
	public static void printFirstNFactorials(final int n) {
		Iterator<BigInteger> primesIterator = factorials.iterator();
		int loopCount = 1;
		while (primesIterator.hasNext() && loopCount < n) {
			System.out.println(String.format("Factorial #%s is %s", loopCount, primesIterator.next().toString()));
			loopCount++;
		}
	}
	
	public static BigInteger getNthFactorial(final int n) {
		return factorials.get(n);
	}
}
