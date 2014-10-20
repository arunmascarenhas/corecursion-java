package com.corecursion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Codata<T> {
	
	private final CodataFunction<T> fn;
	private final List<T> data;
	private final int indexOfFirstElement;
	
	@SafeVarargs
	public Codata (final CodataFunction<T> codataFn, final int indexOfFirstInitArg, final T... initArgs) {
		if (null == codataFn) {
			throw new IllegalArgumentException("A CodataFunction must be supplied.");
		}
		
		if (0 > indexOfFirstInitArg) {
			throw new IllegalArgumentException("Index of first init arg cannot be zero.");
		}
		
		if (null == initArgs || 0 == initArgs.length) {
			throw new IllegalArgumentException("At least 1 initial argument must be supplied.");
		}
		
		this.fn = codataFn;
		this.fn.setData(this);	// The CodataFunction must have a reference to the Codata
		
		indexOfFirstElement = indexOfFirstInitArg;
		
		this.data = new ArrayList<T>();
		for (T t : initArgs) {	// Populate the data structure with the init args
			this.data.add(t);
		}
	}
	
	protected T lastValue() {
		return data.get(data.size() - 1);
	}
	
	protected int lastIndex() {
		return data.size() + indexOfFirstElement - 1;
	}
	
	public T get(final int index) {
		if (index < indexOfFirstElement) {
			throw new IllegalArgumentException("Index is less than the index of the first supplied init arg.");
		}
		
		int translatedIndex = index - indexOfFirstElement + 1;
		
		if (translatedIndex > data.size()) {
			Iterator<T> i = this.iterator();
			while (data.size() < translatedIndex + 1) {
				i.next();
			}
		}
		
		return data.get(translatedIndex - 1);
	}
	
	private class CodataIterator<I> implements Iterator<I> {
		private int currentPosition = 0;

		@Override
		public boolean hasNext() {
			// The assumption is that the data structure is infinite
			return true;
		}

		@SuppressWarnings("unchecked")
		@Override
		public I next() {
			if (currentPosition == data.size()) {
				data.add(fn.getNext());
			}
			currentPosition++;
			return (I) data.get(currentPosition - 1);
		}
	}
	
	public Iterator<T> iterator() {
		return new CodataIterator<T>();
	}
}
