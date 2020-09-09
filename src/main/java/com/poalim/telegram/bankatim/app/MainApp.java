package com.poalim.telegram.bankatim.app;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MainApp {
	
	 public static void main(String[] args) {
         ApiContextInitializer.init();
         TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
         try {
        	 BankatimBot bankat = new BankatimBot();
             telegramBotsApi.registerBot(bankat);
             System.err.println("start Bot");
         } catch (TelegramApiException e) {
             e.printStackTrace();
         }
     }

}
