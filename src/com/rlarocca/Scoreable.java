package com.rlarocca;

public class Scoreable {
	
	/*
	 * This class is for jugglers and circuits to extend of of.
	 * Both have coordination, endurance, and pizzazz values.
	 * This gives them both the getters and setters.
	 * It also gives them the ability to see how well they match with another
	 * Scorable with the function getScore()
	 */
	
	private int coordination;
	private int endurance;
	private int pizzazz;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCoordination() {
		return coordination;
	}
	public void setCoordination(int coordination) {
		this.coordination = coordination;
	}
	public int getEndurance() {
		return endurance;
	}
	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}
	public int getPizzazz() {
		return pizzazz;
	}
	public void setPizzazz(int pizzazz) {
		this.pizzazz = pizzazz;
	}
	
	public int getScore(Scoreable other) {
		
		int score = 0;
		
		score += this.coordination * other.coordination;
		score += this.endurance * other.endurance;
		score += this.pizzazz * other.pizzazz;
		
		return score;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj == null) return false;
		
		if(obj.getClass() != getClass()) return false;
		
		Scoreable scoreable = (Scoreable) obj;
		
		return this.name.equals(scoreable.getName());
		
	}
	
}
