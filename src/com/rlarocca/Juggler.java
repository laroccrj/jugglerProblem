package com.rlarocca;

public class Juggler extends Scoreable{
	
	private ProfferedCircuit[] profferedCircuits;
	
	public ProfferedCircuit[] getProfferedCircuits() {
		
		return profferedCircuits;
		
	}

	public void setProfferedCircuits(ProfferedCircuit[] profferedCircuits) {
		
		this.profferedCircuits = profferedCircuits;
		
	}

	Juggler(String name, String coordination, String endurance, String pizzazz,
			String profferedCircuits){
		
		super.setName(name);
		super.setCoordination(Integer.parseInt(coordination.split(":")[1]));
		super.setPizzazz(Integer.parseInt(pizzazz.split(":")[1]));
		super.setEndurance(Integer.parseInt(endurance.split(":")[1]));
		
		String[] splitProfferedCircuits = profferedCircuits.split(",");
		ProfferedCircuit[] profferedCircuitsTemp =
				new ProfferedCircuit[splitProfferedCircuits.length];
		
		for(int i = 0; i < splitProfferedCircuits.length; i++){
			
			ProfferedCircuit profferedCircuit =
					new ProfferedCircuit(splitProfferedCircuits[i]);
			profferedCircuitsTemp[i] = profferedCircuit;
			
		}
		
		this.profferedCircuits = profferedCircuitsTemp;
		
	}
	
	/*
	 * This returns which proffered circuit is next on the list and has not been
	 * checked
	 */
	public String getNextProffered() {
		
		for(int i = 0; i < this.profferedCircuits.length; i++) {
			
			if(!this.profferedCircuits[i].isChecked()){
				
				return this.profferedCircuits[i].getCircuitName();
				
			}
			
		}
		
		return null;
		
	}
	
	public void setProfferedChecked(String circuit) {
		
		for(int i = 0; i < this.profferedCircuits.length; i++) {
			
			if(this.profferedCircuits[i].getCircuitName().equals(circuit)){
				
				this.profferedCircuits[i].setChecked(true);
				
			}
			
		}
		
	}
	
	/*
	 * This returns how many proffered circuits have yet to be checked
	 */
	public int getProfferedOpen(){
		
		int open = 0;
		
		for(int i = 0; i < this.profferedCircuits.length; i++)
			if(!this.profferedCircuits[i].isChecked()) open++;
		
		return open;
		
	}
}
