package me.wickersty.mortuusterra.radiowaves.objects;

public class Frequency {

	private String frequency;
	private Integer channel;
	
	public Frequency(Integer channel, String frequency) {
		
		this.channel = channel;
		this.frequency = frequency;
		
	}
	
	public String getFrequency() {
		
		return frequency;
		
	}
	
	public Integer getChannel() {
		
		return channel;
		
	}
	
	public void setFrequency(String frequency) {
		
		this.frequency = frequency;
		
	}
	
	public void setChannel(Integer channel) {
		
		this.channel = channel;
		
	}
	
}
