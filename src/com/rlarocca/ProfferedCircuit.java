package com.rlarocca;

public class ProfferedCircuit {
	private String circuitName;
	private boolean checked = false;
	
	ProfferedCircuit(String circuitName){
		this.circuitName = circuitName;
	}
	
	public ProfferedCircuit() {
		this.circuitName = null;
	}

	public String getCircuitName() {
		return circuitName;
	}
	public void setCircuitName(String circuitName) {
		this.circuitName = circuitName;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
