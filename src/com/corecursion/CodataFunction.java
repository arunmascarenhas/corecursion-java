package com.corecursion;

public abstract class CodataFunction<T> {

	private Codata<T> data;
	
	void setData(final Codata<T> data) {
		this.data = data;
	}
	
	protected Codata<T> getData() {
		return data;
	}
	
	public abstract T getNext();
}
