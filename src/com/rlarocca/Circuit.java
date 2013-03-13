package com.rlarocca;

import java.util.ArrayList;

public class Circuit extends Scoreable{
	
	private boolean full = false;
	private ArrayList<Juggler> jugglers = new ArrayList<Juggler>();
	
	public ArrayList<Juggler> getJugglers() {
		
		return jugglers;
		
	}

	public void setJugglers(ArrayList<Juggler> jugglers) {
		
		this.jugglers = jugglers;
		
	}

	Circuit(String name, String coordination, String endurance, String pizzazz){
		
		super();
		super.setName(name);
		super.setCoordination(Integer.parseInt(coordination.split(":")[1]));
		super.setPizzazz(Integer.parseInt(pizzazz.split(":")[1]));
		super.setEndurance(Integer.parseInt(endurance.split(":")[1]));
		
	}
	
	/*
	 * When a juggler tries to join the circuit, I make sure to first check if
	 * this is one of their proffered circuits. If it is, I make sure it is
	 * set as checked
	 * 
	 * If the circuit does not have the max amount of jugglers, the juggler just
	 * gets added and I return null
	 * If the circuit does not have room, I see if the new juggler is a better 
	 * match than the last juggler in the circuit.
	 * If it is a better match, I add it to the circuit, I remove the lowest 
	 * juggler and return him or her.
	 */
	public Juggler addJuggler(Juggler juggler){
		
		juggler.setProfferedChecked(super.getName());
		
		if(full){
			
			Juggler lastJuggler = this.jugglers.get(this.jugglers.size() - 1);
			
			if(lastJuggler.getScore(this) >= juggler.getScore(this))
				return juggler;
			
			else {
				
				this.jugglers.set(this.jugglers.size() - 1, juggler);
				sortJugglers();
				return lastJuggler;
				
			}
			
		} else {
			
			this.jugglers.add(juggler);
			
			if(this.jugglers.size() >= Circuits.JugglersPerCircuit){
				
				this.full = true;
				
			}
			
			sortJugglers();
			
		}
		
		return null;
		
	}
	
	/*
	 * This is a simple sort of the jugglers to make sure the worst matched
	 * juggler is in the lowest index
	 */
	private void sortJugglers(){
		boolean sorted = true;
		
		while(sorted){
			
			sorted = false;
			
			for(int i = 0; i < this.jugglers.size() - 1; i++){
				
				Juggler jug1 = this.jugglers.get(i);
				Juggler jug2 = this.jugglers.get(i + 1);
				
				if(jug1.getScore(this) < jug2.getScore(this)){
					
					Juggler holder = this.jugglers.get(i);
					this.jugglers.set(i, this.jugglers.get(i + 1));
					this.jugglers.set(i + 1, holder);
					sorted = true;
					
				} else if(jug1.getScore(this) == jug2.getScore(this)){
					
					if(jug1.getProfferedOpen() > jug2.getProfferedOpen()){
						
						Juggler holder = this.jugglers.get(i);
						this.jugglers.set(i, this.jugglers.get(i + 1));
						this.jugglers.set(i + 1, holder);
						sorted = true;
						
					}
					
				}
				
			}
			
		}
		
	}
	
	/*
	 * This is how many jugglers can fit in a circuit
	 */
	public static class Circuits{
		
		public static int JugglersPerCircuit;
		
	}
	
}
