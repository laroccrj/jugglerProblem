package com.rlarocca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.rlarocca.Circuit.Circuits;

public class Jugglers {
	
	public static void main(String[] args) {
		List<Juggler> jugglers = new ArrayList<Juggler>();
		HashMap<String, Circuit> circuits = new HashMap<String, Circuit>();
		/*
		* rejects is to hold jugglers that get kicked out of all their 
		* proffered circuits
		*/
		List<Juggler> rejects = new ArrayList<Juggler>();
		
		/*
		 * First I read the file
		 * I create the jugglers and circuits from the information
		 * I then find how many jugglers will fit in a circuit
		 */
		try {
			BufferedReader reader = 
					new BufferedReader(new FileReader("input.txt"));
			String line;
			
			while ((line = reader.readLine()) != null) {
				
				String[] parts = line.split(" ");
				
				if(parts[0].equals("C")){
					
					Circuit circuit = 
							new Circuit(parts[1], parts[2], parts[3], parts[4]);
					circuits.put(circuit.getName(), circuit);
					
				} else if(parts[0].equals("J")){
					
					Juggler juggler = 
							new Juggler(parts[1], parts[2], parts[3], parts[4],
									parts[5]);
					jugglers.add(juggler);
					
				}
				
			}
			Circuits.JugglersPerCircuit = jugglers.size() / circuits.size();
			
			/*
			 * Next I loop through the jugglers
			 * I check if I can add the juggler to its next proffered circuit
			 * If I can't I try the next proffered, until I have gone through
			 * them all
			 * If that happens I add it to the rejects
			 * If the juggler gets added to a circuit but another juggler
			 * has to be kicked out, I then go through the same process with
			 * that new juggler
			 */
			for(int i = 0; i < jugglers.size(); i++){
				
				Juggler juggler = jugglers.get(i);
				
				while(juggler != null){
					
					if(juggler.getNextProffered() == null) {
						
						rejects.add(juggler);
						juggler = null;
						
					}else{
						
						String nextProffered = juggler.getNextProffered();
						juggler = 
							circuits.get(nextProffered).addJuggler(juggler);
						
					}
					
				}
				
			}
			
			/*
			 * Next I loop through the rejects
			 * Since they have no proffered circuits, I try to add them to the 
			 * circuits they match with most.
			 * I first create a array list of circuit scores for the juggler
			 * I then sort that array so the best matches are at the start
			 * of the list.
			 * I then loop through the circuit scores and try to add the juggler
			 * to them.
			 * If another juggler gets kicked out, he just gets added to the
			 * rejects.
			 * After this, all jugglers should be in their proper circuit
			 */
			for(int i = 0; i < rejects.size(); i++){
				
				List<CircuitScore> circuitScores = 
						new ArrayList<CircuitScore>();
				
				HashMap<String, Circuit> tempMap = 
						new HashMap<String, Circuit>();
				tempMap.putAll(circuits);
				Iterator<Entry<String, Circuit>> iterator = 
						tempMap.entrySet().iterator();
				Juggler juggler = rejects.get(i);
				
				while (iterator.hasNext()) {
					
				       Map.Entry<String, Circuit> entry = 
				    		   (Map.Entry<String, Circuit>)iterator.next();
				       Circuit circuit = entry.getValue();
				        
				       CircuitScore circuitScore = 
				    		   new CircuitScore(circuit, juggler);
				    
				       circuitScores.add(circuitScore);
				       
				       iterator.remove();
				       
				}
				
				Collections.sort(circuitScores, new scoreComparator());
				Juggler tempJuggler = juggler;
				
				while(juggler.equals(tempJuggler)){
					
					CircuitScore circuitScore = circuitScores.get(0);
					tempJuggler = circuits.get(circuitScore.getName()).
							addJuggler(tempJuggler);
					circuitScores.remove(circuitScore);
					
				}
				
				while(tempJuggler != null){
					
					if(tempJuggler.getNextProffered() == null) {
						
						rejects.add(tempJuggler);
						tempJuggler = null;
						
					}else
						tempJuggler = circuits.get(
								tempJuggler.getNextProffered()).
								addJuggler(tempJuggler);
					
				}
				
			}
			
			outPutCircuits(circuits);
			
			reader.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	static void outPutCircuits(HashMap<String, Circuit> circuits) 
			throws IOException{
		HashMap<String, Circuit> tempMap = new HashMap<String, Circuit>();
		tempMap.putAll(circuits);
		Iterator<Entry<String, Circuit>> iterator = 
				tempMap.entrySet().iterator();
		
		FileWriter fstream = new FileWriter("output.txt");
		BufferedWriter out = new BufferedWriter(fstream);
		
		 while (iterator.hasNext()) {
			 
		        Map.Entry<String, Circuit> entry = 
		        		(Map.Entry<String, Circuit>)iterator.next();
		        Circuit circuit = entry.getValue();
		        out.write(circuit.getName() + " ");
		        
		        ArrayList<Juggler> jugglers = circuit.getJugglers();
		        
		        for(int i = 0; i < jugglers.size(); i++) {
		        	
		        	Juggler juggler = jugglers.get(i);
		        	
		        	if(circuit.getName().equals("C1970")){
		        		
		        		//This is to find the answer to the problem
		        		System.out.println(juggler.getName());
		        		
		        	}
		        	
		        	out.write(juggler.getName() + " ");
		        	
		        	ProfferedCircuit[] profferedCircuits =
		        			juggler.getProfferedCircuits();
		        	
		        	for(int j = 0; j < profferedCircuits.length; j++) {
		        		
			        	ProfferedCircuit profferedCircuit = 
			        			profferedCircuits[j];
			        	String name = profferedCircuit.getCircuitName();
			        	
			        	out.write(name + ":");
			        	int score = juggler.getScore(circuits.get(name));
			        	out.write(score + " ");
			        	
		        	}
		        	
		        	out.write(", ");
		        	
		        }
		        
		        out.newLine();
		        iterator.remove();
		        
		 }
		 
		 out.close();
		 
	}

}
