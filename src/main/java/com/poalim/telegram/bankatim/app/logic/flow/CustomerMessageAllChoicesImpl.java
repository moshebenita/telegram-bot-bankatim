package com.poalim.telegram.bankatim.app.logic.flow;

import java.util.List;

public class CustomerMessageAllChoicesImpl implements CustomerMessage {

	private String msg;
	private String id;
	private List<UserChoice> userMassges ;
	
	public CustomerMessageAllChoicesImpl(String msg, String id,
			List<UserChoice> userMassges) {
		this.msg = msg;
		this.id = id;
		this.userMassges = userMassges;
	}

	public String getMessage(Language language) {
		String newMesge = msg + "\n";
		for(int i = 0; i < userMassges.size(); i++){
			newMesge += userMassges.get(i).getUserMessage() + " " + userMassges.get(i).getUserChoice() + "\n";
		}
		return newMesge;
	}

	public String getMessageId() {
		return id;
	}
}
