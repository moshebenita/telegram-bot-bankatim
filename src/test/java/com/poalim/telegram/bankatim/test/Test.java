package com.poalim.telegram.bankatim.test;

import org.junit.Assert;
import org.junit.Before;

import com.poalim.telegram.bankatim.app.logic.builder.GenericeStateBuilder;
import com.poalim.telegram.bankatim.app.logic.state.StateMachineLogicService;
import com.poalim.telegram.bankatim.app.logic.state.UserState;

public class Test {


	GenericeStateBuilder flow ;
	StateMachineLogicService logic;
	UserState state ;

	@Before
	public void before(){
		flow = new GenericeStateBuilder();
		logic = new StateMachineLogicService();
		state = flow.buildState();
	}

	@org.junit.Test
	public void testFirstCode(){
		printAll(state);
		Assert.assertEquals("startStateMessageId", state.getCurrentSendUserMessage().getMessageId());
	}

	@org.junit.Test
	public void testCreditEnquery(){
		testFirstCode();
		state = logic.getNextStateOption(state, "000");
		Assert.assertEquals("creditEnquery", state.getCurrentSendUserMessage().getMessageId());


	}

	@org.junit.Test
	public void testAmountDeclaration(){
		testCreditEnquery();
		state = logic.getNextStateOption(state, "ма");
		Assert.assertEquals("amountDeclarationMwssageId", state.getCurrentSendUserMessage().getMessageId());

	}

	@org.junit.Test
	public void testCardCapture(){
		testAmountDeclaration();
		state = logic.getNextStateOption(state, "500");
		Assert.assertEquals("cardCaptureMessageId", state.getCurrentSendUserMessage().getMessageId());
		System.err.println(state.getCurrentSendUserMessage().getMessageId());

	}

	@org.junit.Test
	public void testSummaryComplainMessageId(){
		testCardCapture();
		state = logic.getNextStateOption(state, "ма");
		Assert.assertEquals("summaryComplainMessageId", state.getCurrentSendUserMessage().getMessageId());
		System.err.println(state.getCurrentSendUserMessage().getMessageId());

	}



	private static void printAll(UserState state){
		System.err.println(state.getCurrentSendUserMessage().getMessage(null));
		if(null != state.getUserCurentStateOptions())
			state.getUserCurentStateOptions().pringOptions();

	}

}
