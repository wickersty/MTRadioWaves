package me.wickersty.mortuusterra.radiowaves.managers;

import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;
import me.wickersty.mortuusterra.radiowaves.objects.Frequency;
import me.wickersty.mortuusterra.radiowaves.objects.FrequencyRange;

public class FrequencyManager {

	private final MTRadioWaves instance;
	
	private FrequencyRange familyRadioService;
	private FrequencyRange citizensBandRadio;
	
	public FrequencyManager(MTRadioWaves instance) {
		
		this.instance = instance;
		
		initializeFrequencyRanges();
		
	}
	
	public void initializeFrequencyRanges() {
				
		familyRadioService = new FrequencyRange(instance);
		
		familyRadioService.setFrequencyName("Family Radio Service");
		familyRadioService.getFrequencyRange().add(new Frequency(1, "462.5625"));
		familyRadioService.getFrequencyRange().add(new Frequency(2, "462.5875"));
		familyRadioService.getFrequencyRange().add(new Frequency(3, "462.6125"));
		familyRadioService.getFrequencyRange().add(new Frequency(4, "462.6375"));
		familyRadioService.getFrequencyRange().add(new Frequency(5, "462.6625"));
		familyRadioService.getFrequencyRange().add(new Frequency(6, "462.6875"));
		familyRadioService.getFrequencyRange().add(new Frequency(7, "462.7125"));
		familyRadioService.getFrequencyRange().add(new Frequency(8, "467.5625"));
		familyRadioService.getFrequencyRange().add(new Frequency(9, "467.5875"));
		familyRadioService.getFrequencyRange().add(new Frequency(10, "467.6125"));
		familyRadioService.getFrequencyRange().add(new Frequency(11, "467.6375"));
		familyRadioService.getFrequencyRange().add(new Frequency(12, "467.6625"));
		familyRadioService.getFrequencyRange().add(new Frequency(13, "467.6875"));
		familyRadioService.getFrequencyRange().add(new Frequency(14, "467.7125"));
		

		citizensBandRadio = new FrequencyRange(instance);
		
		citizensBandRadio.setFrequencyName("Ham Radio");
		citizensBandRadio.getFrequencyRange().add(new Frequency(1, "26.965"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(2, "26.975"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(3, "26.985"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(4, "27.005"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(5, "27.015"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(6, "27.025"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(7, "27.035"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(8, "27.055"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(9, "27.065"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(10, "27.075"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(11, "27.085"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(12, "27.105"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(13, "27.115"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(14, "27.125"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(15, "27.135"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(16, "27.155"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(17, "27.165"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(18, "27.175"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(19, "27.185"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(20, "27.205"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(21, "27.215"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(22, "27.225"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(23, "27.255"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(24, "27.235"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(25, "27.245"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(26, "27.265"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(27, "27.275"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(28, "27.285"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(29, "27.295"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(30, "27.305"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(31, "27.315"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(32, "27.325"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(33, "27.335"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(34, "27.345"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(35, "27.355"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(36, "27.365"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(37, "27.375"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(38, "27.385"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(39, "27.395"));
		citizensBandRadio.getFrequencyRange().add(new Frequency(40, "27.405"));
		
	}
	
	public FrequencyRange getFamilyRadioService() {
		
		return familyRadioService;
		
	}
	
	public FrequencyRange getCitizensBandRadio() {
		
		return citizensBandRadio;
		
	}
	
}
