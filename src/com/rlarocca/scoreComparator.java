package com.rlarocca;

import java.util.Comparator;

public class scoreComparator implements Comparator<CircuitScore>{

	@Override
	public int compare(CircuitScore o1, CircuitScore o2) {
		return o1.compareTo(o2);
	}

}
