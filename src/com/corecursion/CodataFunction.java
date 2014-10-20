package com.corecursion;

public abstract class CodataFunction<T> {

	private Codata<T> data;
	
	void setData(final Codata<T> data) {
		this.data = data;
	}
	
	public T lastValue() {
		return data.lastValue();
	}
	
	public int lastIndex() {
		return data.lastIndex();
	}
	
	public T get(final int index) {
		return data.get(index);
	}
	
	public abstract T getNext();
}
