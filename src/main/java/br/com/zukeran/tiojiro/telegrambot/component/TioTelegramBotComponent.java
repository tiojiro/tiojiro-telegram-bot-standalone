package br.com.zukeran.tiojiro.telegrambot.component;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

@Component
public class TioTelegramBotComponent {
	
	private static final String VAZIO = "";
	
	@Autowired
	private TelegramBot bot;

	public void runBot() throws InterruptedException {
		GetUpdatesResponse updatesResponse;
		SendResponse sendResponse;
		BaseResponse baseResponse;
		
		int m=0;
		
		while (true){
		
			updatesResponse =  bot.execute(new GetUpdates().limit(100).offset(m));
			
			List<Update> updates = updatesResponse.updates();

			for (Update update : updates) {
				
				m = update.updateId()+1;
				
				System.out.println("Mensagem recebida: ["+ update.message().text() + "]");
				
				baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
				System.out.println("Chat Action enviada? [" + baseResponse.isOk() + "]");
				
				TimeUnit.SECONDS.sleep(1);
				
				if(!VAZIO.equals(update.message().from().username()))
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Olá, " + update.message().from().username() + "!"));
				else if (!VAZIO.equals(update.message().from().firstName()))
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Olá, " + update.message().from().firstName() + "!"));
				else
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Olá!"));
				System.out.println("Mensagem enviada? [" +sendResponse.isOk() + "]");
				
			}

		}
	}
}
