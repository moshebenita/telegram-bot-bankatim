package com.poalim.telegram.bankatim.app.logic.flow;

public class UserChoice {
	
	private String userMessage;
	private String userChoice;
	
	public UserChoice(String userMessage, String userChoice) {
		this.userMessage = userMessage;
		this.userChoice = userChoice;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getUserChoice() {
		return userChoice;
	}

	public void setUserChoice(String userChoice) {
		this.userChoice = userChoice;
	}
	
	

}
