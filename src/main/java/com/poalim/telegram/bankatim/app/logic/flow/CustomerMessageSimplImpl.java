package com.poalim.telegram.bankatim.app.logic.flow;

public class CustomerMessageSimplImpl implements CustomerMessage {

	private String msg;
	private String id;
	
	
	
	public CustomerMessageSimplImpl(String msg, String id) {
		this.msg = msg;
		this.id = id;
	}

	public String getMessage(Language language) {
		return msg;
	}

	public String getMessageId() {
		return id;
	}

}
