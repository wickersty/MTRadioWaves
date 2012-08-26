package me.wickersty.mortuusterra.radiowaves.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;
import me.wickersty.mortuusterra.radiowaves.objects.TextMessage;

public class TextMessageManager {

	private final MTRadioWaves instance;
	
	private List<TextMessage> textMessages;
	
	public TextMessageManager(MTRadioWaves instance) {
		
		this.instance = instance;
		
		textMessages = new ArrayList<TextMessage>();
		
	}
	
	public List<TextMessage> getTextMessages() {
		
		return textMessages;
		
	}
	
	public List<TextMessage> getTextMessagesByRecipientName(String recipientName) {

		List<TextMessage> recipientMessages;
		recipientMessages = new ArrayList<TextMessage>();
		
		for (TextMessage textMessage : textMessages) {
			
			if (textMessage.getRecipientName().equalsIgnoreCase(recipientName)) {
			
				recipientMessages.add(textMessage);
				
			}
			
		}
	
		return recipientMessages;
		
	}
	
	public void addTextMessage(String senderName, String recipientName, String textMessage) {
		
		textMessages.add(new TextMessage(senderName, recipientName, textMessage));
			
	}
	
	public void markTextMessageRead(TextMessage textMessage) {
		
		for (TextMessage tempTextMessage : getTextMessagesByRecipientName(textMessage.getRecipientName())) {
			
			if (tempTextMessage.equals(textMessage)) {
				
				tempTextMessage.markAsRead();
				
			}
			
		}
		
	}

	public void removeTextMessage(String senderName, String recipientName, String message) {
		
		TextMessage textMessageToDelete = new TextMessage(senderName, recipientName, message);
		
		for (TextMessage textMessage : textMessages) {
			
			if (textMessage.getRecipientName().equalsIgnoreCase(recipientName)) {
			
				textMessageToDelete = textMessage;
				
			}
			
		}
		
		textMessages.remove(textMessageToDelete);
		
	}
	
	public void savetextMessagesToDisk() {
		
		try {

			File textMessagesFile = new File(instance.getDataFolder() + File.separator + "textMessages.txt");
			textMessagesFile.delete();
			
			textMessagesFile = new File(instance.getDataFolder() + File.separator + "textMessages.txt");
			textMessagesFile.createNewFile();
		
		} catch (IOException e) {
		
			e.printStackTrace();
			
		}
		
		instance.getLogger().info("Saving textMessages To Disk");

		try {
			
			PrintWriter textMessagesFile = new PrintWriter(instance.getDataFolder() + File.separator + "textMessages.txt");
				
			if (textMessages.size() > 0) {

				for (TextMessage textMessage : textMessages) {
					
					String textMessageString = textMessage.getSerializedTextMessage();
	
					textMessagesFile.println(textMessageString);
					
				}

			}
			
			textMessagesFile.close();          
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void loadtextMessagesFromDisk() {
		
		ensuretextMessagesFileExists();
		
		instance.getLogger().info("Loading textMessages From Disk");

		try {

			File textMessagesFile = new File(instance.getDataFolder() + File.separator + "textMessages.txt");
			Scanner inputFile = new Scanner(textMessagesFile);
			
			while (inputFile.hasNextLine()) {
	
				String textMessageString = inputFile.nextLine();
				String[] textMessageArray = textMessageString.split("~");

				
				textMessages.add(new TextMessage(textMessageArray[0], textMessageArray[1], textMessageArray[2]));

			}
			
			inputFile.close();
		
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}

	public void ensuretextMessagesFileExists() {
		
		instance.getLogger().info("Creating textMessages File");

		// create file if not exists
		try {
			
			File textMessagesFile = new File(instance.getDataFolder() + File.separator + "textMessages.txt");
	
			if (textMessagesFile.exists() == false) {
				
				textMessagesFile = new File(instance.getDataFolder() + File.separator + "textMessages.txt");
				textMessagesFile.createNewFile();
				
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}	
		
	}
	
	
}
