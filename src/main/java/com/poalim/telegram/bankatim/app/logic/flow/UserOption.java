package com.poalim.telegram.bankatim.app.logic.flow;

import java.util.ArrayList;
import java.util.List;

public class UserOption {
	private List<CustomerMessage> messages = new ArrayList<CustomerMessage>();

	public List<CustomerMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<CustomerMessage> messages) {
		this.messages = messages;
	}
	
	public void pringOptions(){
			for (CustomerMessage customerMessage : messages) {
				if(null != customerMessage)
				System.err.print(customerMessage.getMessage(null) + " ");
			}
			System.err.println();
	}
	
}
