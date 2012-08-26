package me.wickersty.mortuusterra.radiowaves.objects;

import java.util.ArrayList;
import java.util.List;

import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;

public class FrequencyRange {

	@SuppressWarnings("unused")
	private final MTRadioWaves instance;
	
	private String frequencyName;
	private List<Frequency> frequencies;
	
	public FrequencyRange(MTRadioWaves instance) {
		
		this.instance = instance;
		
		frequencies = new ArrayList<Frequency>();
		
	}
	
	public String getFrequencyName() {
		
		return frequencyName;
		
	}
	
	public void setFrequencyName(String frequencyName) {
		
		this.frequencyName = frequencyName;
		
	}
	
	public List<Frequency> getFrequencyRange() {
		
		return frequencies;
		
	}
	
	public Frequency getNextFrequency(Frequency currentFrequency) {
		
		Frequency nextFrequency = new Frequency(null, null);
		
		int frequencyIndex = 0;
		int nextFrequencyIndex = 0;
		
		for (Frequency frequency : frequencies) {
			
			if (frequency.equals(currentFrequency)) {
				
				if ((frequencyIndex + 1) == frequencies.size()) {
					
					nextFrequencyIndex = 0;
					
				} else {
					
					nextFrequencyIndex = frequencyIndex + 1;
					
				}
				
				break;
				
			}
			
			frequencyIndex++;
			
		}
		
		nextFrequency = frequencies.get(nextFrequencyIndex);
		
		return nextFrequency;
		
	}
	
	public Frequency getPreviousFrequency(Frequency currentFrequency) {
		
		Frequency nextFrequency = new Frequency(null, null);
		
		int frequencyIndex = 0;
		int nextFrequencyIndex = 0;
		
		for (Frequency frequency : frequencies) {
			
			if (frequency.equals(currentFrequency)) {
				
				if (frequencyIndex == 0) {
					
					nextFrequencyIndex = frequencies.size() - 1;
					
				} else {
					
					nextFrequencyIndex = frequencyIndex - 1;
					
				}
				
				break;
				
			}
			
			frequencyIndex++;
			
		}
		
		nextFrequency = frequencies.get(nextFrequencyIndex);
		
		return nextFrequency;
		
	}
	
}
