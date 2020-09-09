package com.poalim.telegram.bankatim.app.logic.state;

import java.util.LinkedList;
import java.util.Map;

import com.poalim.telegram.bankatim.app.logic.flow.CustomerMessage;
import com.poalim.telegram.bankatim.app.logic.flow.UserChoice;
import com.poalim.telegram.bankatim.app.logic.flow.UserOption;
import com.poalim.telegram.bankatim.app.logic.state.action.StateActionService;

public class UserState {
	
	private String stateId;
	private UserOption userCurentStateOptions;
	private StateActionService action;
	private Map<String,UserState> naxtState;
	private UserState defaultNaxtState;
	private boolean isFinalState;
	private CustomerMessage currentSendUserMessage;
	private LinkedList<UserChoice> userMassges = new LinkedList<UserChoice>();
	
	
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public UserOption getUserCurentStateOptions() {
		return userCurentStateOptions;
	}
	public void setUserCurentStateOptions(UserOption userCurentStateOptions) {
		this.userCurentStateOptions = userCurentStateOptions;
	}
	public Map<String, UserState> getNaxtState() {
		return naxtState;
	}
	public void setNaxtState(Map<String, UserState> naxtState) {
		this.naxtState = naxtState;
	}
	public boolean isFinalState() {
		return isFinalState;
	}
	public void setFinalState(boolean isFinalState) {
		this.isFinalState = isFinalState;
	}
	public StateActionService getAction() {
		return action;
	}
	public void setAction(StateActionService action) {
		this.action = action;
	}
	public CustomerMessage getCurrentSendUserMessage() {
		return currentSendUserMessage;
	}
	public void setCurrentSendUserMessage(CustomerMessage currentSendUserMessage) {
		this.currentSendUserMessage = currentSendUserMessage;
	}
	public void addPreviuosCustomerChoicesMessage(LinkedList<UserChoice> userMassges){
		for(int i = 0; i < userMassges.size() ; i++)
			getUserMassges().addLast(userMassges.get(i));
	}
	
	public void addCustomerChoicesMessage(UserChoice currentSendUserMessage){
		getUserMassges().addLast(currentSendUserMessage);
	}
	public UserState getDefaultNaxtState() {
		return defaultNaxtState;
	}
	public void setDefaultNaxtState(UserState defaultNaxtState) {
		this.defaultNaxtState = defaultNaxtState;
	}
	public LinkedList<UserChoice> getUserMassges() {
		return userMassges;
	}
	public void setUserMassges(LinkedList<UserChoice> userMassges) {
		this.userMassges = userMassges;
	}

}
