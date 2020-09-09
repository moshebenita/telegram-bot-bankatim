package com.poalim.telegram.bankatim.app.logic.builder;

import java.util.HashMap;
import java.util.Map;

import com.poalim.telegram.bankatim.app.logic.flow.CustomerMessage;
import com.poalim.telegram.bankatim.app.logic.flow.CustomerMessageSimplImpl;
import com.poalim.telegram.bankatim.app.logic.flow.UserOption;
import com.poalim.telegram.bankatim.app.logic.state.UserState;

public class GenericeStateBuilder implements UserStateBuilderService{
	CashDepositBuilderStateFlow cahDepositFlow = new CashDepositBuilderStateFlow();
	
	public UserState buildState() {
		
		return startState();
	}
	
	
	public UserState startState(){
		UserState userState = new UserState();
		userState.setCurrentSendUserMessage(new CustomerMessageSimplImpl("נא הקש קוד פעולה שהתקבל במסרון","startStateMessageId"));
		UserOption userCurentStateOptions = new UserOption();
		userCurentStateOptions.getMessages().add(createNoCustomerMessage());
		userState.setUserCurentStateOptions(userCurentStateOptions);
		userState.setStateId("declarationAmountCaptureState");
		Map<String, UserState> mapNextState = new HashMap<String, UserState>();
		UserState declarationAmountCaptureState = cahDepositFlow.buildState();
		mapNextState.put("000", declarationAmountCaptureState);
		userState.setDefaultNaxtState(finalState());
		userState.setNaxtState(mapNextState);
		return userState;
	}
	
	private CustomerMessage createNoCustomerMessage() {
		// TODO Auto-generated method stub
		return null;
	}


	private UserState finalState(){
		UserState userState = new UserState();
		userState.setCurrentSendUserMessage(new CustomerMessageSimplImpl("מספר פעולה לא קיים","startComplaintFailed"));
		userState.setFinalState(true);
		userState.setStateId("finalState");
		return userState;
	}

}
