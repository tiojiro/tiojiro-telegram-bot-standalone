package br.com.zukeran.tiojiro.telegrambot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.zukeran.tiojiro.telegrambot.component.TioTelegramBotComponent;
import br.com.zukeran.tiojiro.telegrambot.service.TioTelegramBotService;

@SpringBootApplication
public class TiojiroTelegramBotApplication implements CommandLineRunner {

	@Autowired
	TioTelegramBotService tioTelegramBotService;
	
	@Autowired
	TioTelegramBotComponent botComponent;
	
	public static void main(String[] args) {
		SpringApplication.run(TiojiroTelegramBotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		botComponent.runBot();
	}
}
