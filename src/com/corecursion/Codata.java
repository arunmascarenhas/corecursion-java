package com.corecursion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Codata<T> {
	
	private final CodataFunction<T> fn;
	private final List<T> data;
	
	@SafeVarargs
	public Codata (final CodataFunction<T> codataFn, final T... initArgs) {
		if (null == codataFn) {
			throw new IllegalArgumentException("A CodataFunction must be supplied.");
		}
		
		if (null == initArgs || 0 == initArgs.length) {
			throw new IllegalArgumentException("At least 1 initial argument must be supplied.");
		}
		
		this.fn = codataFn;
		this.fn.setData(this);	// The CodataFunction must have a reference to the Codata
		
		this.data = new ArrayList<T>();
		for (T t : initArgs) {	// Populate the data structure with the init args
			this.data.add(t);
		}
	}
	
	public T lastValue() {
		return data.get(data.size() - 1);
	}
	
	public int lastIndex() {
		return data.size() - 1;
	}
	
	public T get(final int index) {
		if (index - 1 > data.size()) {
			Iterator<T> i = this.iterator();
			while (data.size() < index) {
				i.next();
			}
		}
		
		return data.get(index - 1);
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
