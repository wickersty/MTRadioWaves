package me.wickersty.mortuusterra.radiowaves.objects;

public class TextMessage {

	private String senderName;
	private String recipientName;
	private String textMessage;
	private boolean unread;
	
	public TextMessage(String senderName, String recipientName, String textMessage) {
		
		this.senderName = senderName;
		this.recipientName = recipientName;
		this.textMessage = textMessage;
		this.unread = true;
		
	}
	
	public String getSenderName() {
		
		return senderName;
		
	}
	
	public String getRecipientName() {
		
		return recipientName;
		
	}
	
	public String getTextMessage() {
		
		return textMessage;
		
	}
	
	public String getSerializedTextMessage() {
		
		return senderName + "~" + recipientName + "~" + textMessage;
		
	}
	
	public void markAsRead() {
		
		unread = false;
		
	}
	
	public boolean isUnread() {
		
		return unread;
		
	}
	
}
