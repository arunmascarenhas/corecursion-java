package com.corecursion.examples;

import java.util.Iterator;

import com.corecursion.Codata;
import com.corecursion.CodataFunction;

public class CorecursivePrimeGenerator {
	
	private final static Codata<Integer> primes = new Codata<Integer>(
				new CodataFunction<Integer>() {

					@Override
					public Integer getNext() {
						for (int i = lastValue() + 2; ; i += 2) {
							if (isPrime(i)) {
								return i;
							}
						}
					}
					
					private boolean isPrime(Integer n) {
						for (int i = 2; i <= Math.sqrt(n); i++) {
							if (0 == (n % i)) {
								return false;
							}
						}
						return true;
					}
					
				},
				1,
				Integer.valueOf(2), Integer.valueOf(3)
			);

	public static void main(final String... args) {
		printFirstNPrimes(10);
		System.out.println(String.format("The 100th prime is %s", primes.get(100)));
	}
	
	public static void printFirstNPrimes(final int n) {
		Iterator<Integer> primesIterator = primes.iterator();
		int loopCount = 1;
		while (primesIterator.hasNext() && loopCount < n + 1) {
			System.out.println(String.format("Prime #%s is %s", loopCount, primesIterator.next().toString()));
			loopCount++;
		}
	}
	
	public static Integer getNthPrime(final int n) {
		return primes.get(n);
	}
}
