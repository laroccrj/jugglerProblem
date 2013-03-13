package com.rlarocca;

public class CircuitScore implements Comparable<CircuitScore>{
	
	/*
	 * This class is to store a jugglers score with a certain circuit
	 */
	
	private String name;
	private int score;
	
	CircuitScore(Circuit circuit, Juggler juggler){
		
		this.name = circuit.getName();
		this.score = juggler.getScore(circuit);
		
	}

	public String getName() {
		
		return name;
		
	}

	public void setName(String name) {
		
		this.name = name;
		
	}

	public int getScore() {
		
		return score;
		
	}

	public void setScore(int score) {
		
		this.score = score;
		
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
		
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj == null) return false;
		
		if(obj.getClass() != getClass()) return false;
		
		CircuitScore circuitscore = (CircuitScore) obj;
		
		return this.name.equals(circuitscore.getName());
		
	}
	
	/*
	 * I want the lowest circuit to be a the lowest index.
	 * That is why it returns less than if this.score is larger.
	 */
	@Override
	public int compareTo(CircuitScore o) {
		
		if(this.score > o.score) return -1;
		if(this.score == o.score) return 0;
		return 1;
		
	}
}
