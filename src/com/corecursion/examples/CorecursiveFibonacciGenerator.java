package com.corecursion.examples;

import java.util.Iterator;

import com.corecursion.Codata;
import com.corecursion.CodataFunction;

public class CorecursiveFibonacciGenerator {
	private final static Codata<Integer> fibonacci = new Codata<Integer>(
			new CodataFunction<Integer>() {

				@Override
				public Integer getNext() {
					return getData().get(getData().lastIndex() + 1) + getData().get(getData().lastIndex());
				}
			},
			0, 1
		);

	public static void main(final String... args) {
		printFirstNFibonacci(40);
		System.out.println(String.format("The 40th prime is %s", fibonacci.get(40)));
	}
	
	public static void printFirstNFibonacci(final int n) {
		Iterator<Integer> fibIterator = fibonacci.iterator();
		int loopCount = 0;
		while (fibIterator.hasNext() && loopCount < n) {
			System.out.println(String.format("Fibonacci #%s is %s", loopCount, fibIterator.next().toString()));
			loopCount++;
		}
	}
	
	public static Integer getNthFibonacci(final int n) {
		return fibonacci.get(n + 1);
	}
}
