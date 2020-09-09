package com.poalim.telegram.bankatim.app.logic.state.action;

import java.util.List;

import com.poalim.telegram.bankatim.app.logic.flow.CustomerMessage;

public interface StateActionService {
	
	public boolean stateAction(List <CustomerMessage> customerOptions);

}
