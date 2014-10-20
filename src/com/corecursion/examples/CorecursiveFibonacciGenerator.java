package com.corecursion.examples;

import java.math.BigInteger;
import java.util.Iterator;

import com.corecursion.Codata;
import com.corecursion.CodataFunction;

public class CorecursiveFibonacciGenerator {
	private final static Codata<BigInteger> fibonacci = new Codata<BigInteger>(
			new CodataFunction<BigInteger>() {

				@Override
				public BigInteger getNext() {
					return get(lastIndex()).add(get(lastIndex() - 1));
				}
			},
			0,
			BigInteger.valueOf(0), BigInteger.valueOf(1)
		);

	public static void main(final String... args) {
		printFirstNFibonacci(10);
		System.out.println("-----------------------");
		System.out.println(String.format("The 10th Fibonacci Number is %s", fibonacci.get(10)));
	}
	
	public static void printFirstNFibonacci(final int n) {
		Iterator<BigInteger> fibIterator = fibonacci.iterator();
		int loopCount = 0;
		while (fibIterator.hasNext() && loopCount < n) {
			System.out.println(String.format("Fibonacci #%s is %s", loopCount, fibIterator.next().toString()));
			loopCount++;
		}
	}
	
	public static BigInteger getNthFibonacci(final int n) {
		return fibonacci.get(n);
	}
}
