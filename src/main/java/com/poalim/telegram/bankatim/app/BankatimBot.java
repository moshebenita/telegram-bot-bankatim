package com.poalim.telegram.bankatim.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.poalim.telegram.bankatim.app.logic.builder.GenericeStateBuilder;
import com.poalim.telegram.bankatim.app.logic.flow.CustomerMessage;
import com.poalim.telegram.bankatim.app.logic.flow.Language;
import com.poalim.telegram.bankatim.app.logic.state.StateMachineLogicService;
import com.poalim.telegram.bankatim.app.logic.state.UserState;

public class BankatimBot extends TelegramLongPollingBot{

	private String name;
	private GenericeStateBuilder genericeStateBuilder = new GenericeStateBuilder();
	Map<Integer, UserState> usersState = new HashMap<Integer, UserState>();
	StateMachineLogicService logic = new StateMachineLogicService();
	public String getBotUsername() {
		return "Bankatim_bot";
	}

	public void onUpdateReceived(Update update){
		try {
			onCheckFirst(update);
			UserState state = getState(update.getMessage().getFrom().getId(),update.getMessage().getText());
			ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
			if(null != state ){
				if(null != state.getUserCurentStateOptions()){

					List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
					keyboardMarkup.setKeyboard(keyboard);
					for(CustomerMessage message : state.getUserCurentStateOptions().getMessages()){
						if(null != message){
							KeyboardRow row = new KeyboardRow();
							row.add(message.getMessage(Language.getLanguageByCode(update.getMessage().getFrom().getLanguageCode())));
							keyboard.add(row);
						}
					}
				}
				SendMessage message = new SendMessage(update.getMessage().getChatId(),state.getCurrentSendUserMessage().getMessage(null));
				if(keyboardMarkup.getKeyboard().isEmpty()){
					keyboardMarkup.setResizeKeyboard(false);
					keyboardMarkup.setSelective(false);
				}
				keyboardMarkup.setOneTimeKeyboard(true);

				message.setReplyMarkup(keyboardMarkup);
				sendUpdateMessage(update, message);
			}
		} catch (Exception e) {
			usersState.put(update.getMessage().getFrom().getId(), null);
		}
	}

	private void sendUpdateMessage(Update update, SendMessage message){
		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	private void sendStringMessage(Update update, String msg){
		SendMessage message = new SendMessage(update.getMessage().getChatId(),  msg);
		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	public UserState getState(Integer userId, String userMessage){
		UserState userState = usersState.get(userId);
		if(null == userState){
			userState = genericeStateBuilder.startState();
		}
		else
			userState = logic.getNextStateOption(userState, userMessage);
		usersState.put(userId, userState);
		return userState;
	}
	public void onCheckFirst(Update update) {
		if(update.getMessage().getText().equals("/start")){

			usersState.put(update.getMessage().getFrom().getId(), null);
			sendStringMessage(update, "welcome to bankatim bot");
		}
	}

	@Override
	public String getBotToken() {
		String encriptPassword = "sxv9mGXZvdpEU79CipZ9pSVhbEFWJzgG9A/+6uuA4Np63lJUkFVhTFN/JmQp+qHP5io347B/Fak=";
		Properties var = System.getProperties();
		String pass = (String) var.get("password");
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(pass);
		encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
		return  encryptor.decrypt(encriptPassword);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
