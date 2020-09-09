package com.poalim.telegram.bankatim.app.logic.builder;

import java.util.HashMap;
import java.util.Map;

import com.poalim.telegram.bankatim.app.logic.flow.CustomerMessage;
import com.poalim.telegram.bankatim.app.logic.flow.CustomerMessageAllChoicesImpl;
import com.poalim.telegram.bankatim.app.logic.flow.CustomerMessageSimplImpl;
import com.poalim.telegram.bankatim.app.logic.flow.UserOption;
import com.poalim.telegram.bankatim.app.logic.state.UserState;
import com.poalim.telegram.bankatim.app.logic.state.action.StateActionServiceSendMailImpl;

public class CashDepositBuilderStateFlow implements UserStateBuilderService{

	public UserState buildState() {
		
		return creditEnqueryState();
	}
	public UserState creditEnqueryState(){
		UserState userState = new UserState();
		userState.setCurrentSendUserMessage(new CustomerMessageSimplImpl("האם בוצע זיכוי","creditEnquery"));
		UserOption userCurentStateOptions = new UserOption();
		userCurentStateOptions.getMessages().add(createNoCustomerMessage());
		userCurentStateOptions.getMessages().add(createYesCustomerMessage());
		userCurentStateOptions.getMessages().add(createPartlyCustomerMessage());
		userState.setUserCurentStateOptions(userCurentStateOptions );
		Map<String, UserState> mapNextState = new HashMap<String, UserState>();
		UserState declarationAmountCaptureState = declarationAmountCaptureState();
		mapNextState.put("no", declarationAmountCaptureState);
		mapNextState.put("partly", declarationAmountCaptureState);
		mapNextState.put("yes", cardCaptureComplainState());
		userState.setNaxtState(mapNextState);
		userState.setStateId("creditEnqueryState");
		return userState;
		

	}
		
	public UserState declarationAmountCaptureState(){
		UserState userState = new UserState();
		userState.setCurrentSendUserMessage(new CustomerMessageSimplImpl("הקלד סכום שחסר","amountDeclarationMwssageId"));
		UserOption userCurentStateOptions = new UserOption();
		userState.setUserCurentStateOptions(userCurentStateOptions);
		userState.setDefaultNaxtState(cardCaptureComplainState());
		userState.setStateId("declarationAmountCaptureState");
		return userState;
	}
	
	public UserState cardCaptureComplainState(){
		UserState userState = new UserState();
		userState.setCurrentSendUserMessage(new CustomerMessageSimplImpl("האם הכרטיס נבלע","cardCaptureMessageId"));
		UserOption userCurentStateOptions = new UserOption();
		userCurentStateOptions.getMessages().add(createNoCustomerMessage());
		userCurentStateOptions.getMessages().add(createYesCustomerMessage());
		userState.setUserCurentStateOptions(userCurentStateOptions );
		Map<String, UserState> mapNextState = new HashMap<String, UserState>();
		mapNextState.put("no", summaryComplainState());
		mapNextState.put("yes", summaryComplainState());
		userState.setNaxtState(mapNextState);
		userState.setStateId("cardCaptureComplainState");
		return userState;
		
	}
	
	public UserState summaryComplainState(){
		UserState userState = new UserState();
//		String sumurry="";
//		for(int i = 0; i < )
		userState.setCurrentSendUserMessage(new CustomerMessageAllChoicesImpl("האם ברצונך להגיש תלונה ? להלן סיכום הפניה ","summaryComplainMessageId", userState.getUserMassges()));
		UserOption userCurentStateOptions = new UserOption();
		userCurentStateOptions.getMessages().add(createNoCustomerMessage());
		userCurentStateOptions.getMessages().add(createYesCustomerMessage());
		userState.setUserCurentStateOptions(userCurentStateOptions );
		Map<String, UserState> mapNextState = new HashMap<String, UserState>();
		mapNextState.put("no", cancelComplaintState());
		mapNextState.put("yes", finalState());
		userState.setNaxtState(mapNextState );
		userState.setStateId("summaryComplain");
		return userState;
		
	}
	private CustomerMessage createPartlyCustomerMessage(){
		return new CustomerMessageSimplImpl("חלקי", "partly");
	}
	
	private CustomerMessage createNoCustomerMessage(){
		return new CustomerMessageSimplImpl("לא", "no");
	}
	
	private CustomerMessage createYesCustomerMessage(){
		return new CustomerMessageSimplImpl("כן", "yes");
	}
	
	private UserState cancelComplaintState(){
		UserState userState = new UserState();
		userState.setCurrentSendUserMessage(new CustomerMessageSimplImpl("complain cancel","complainComplainMessageId"));
		userState.setFinalState(true);
		userState.setStateId("cancel compalin");
		return userState;
	}
	
	private UserState finalState(){
		UserState userState = new UserState();
		userState.setAction(new StateActionServiceSendMailImpl());
		userState.setCurrentSendUserMessage(new CustomerMessageSimplImpl("send complain success","complainSuccessedId"));
		userState.setFinalState(true);
		userState.setStateId("end compalin successed");
		return userState;
	}
	
	
	
	

}
