package com.poalim.telegram.bankatim.app.logic.state;

import java.util.LinkedList;

import com.poalim.telegram.bankatim.app.logic.flow.CustomerMessage;
import com.poalim.telegram.bankatim.app.logic.flow.CustomerMessageSimplImpl;
import com.poalim.telegram.bankatim.app.logic.flow.UserChoice;

public class StateMachineLogicService {


	public UserState getNextStateOption(UserState currentState, String userMessage){
		CustomerMessage currentCorrectUserChoice= null;
		UserState nextState = null;
		for(CustomerMessage customerMessage : currentState.getUserCurentStateOptions().getMessages()){
			if(customerMessage != null && customerMessage.getMessage(null).equals(userMessage)){
				currentCorrectUserChoice = customerMessage;
				nextState = currentState.getNaxtState().get(currentCorrectUserChoice.getMessageId());
				break;
			}
		}
		if(null == currentCorrectUserChoice){
			if(null != currentState.getNaxtState())
				nextState = currentState.getNaxtState().get(userMessage);
			if(null == nextState && currentState.getDefaultNaxtState() != null)
				nextState = currentState.getDefaultNaxtState();
		}

		else if(null == currentCorrectUserChoice)
			return null;

		if(null != currentState.getUserMassges()){
			nextState.addPreviuosCustomerChoicesMessage(currentState.getUserMassges());
		}
		nextState.addCustomerChoicesMessage(new UserChoice(currentState.getCurrentSendUserMessage().getMessage(null), userMessage));

		if(null != nextState)
			return nextState;

		throw new RuntimeException("not found next state for Message: " + userMessage);
	}
}
