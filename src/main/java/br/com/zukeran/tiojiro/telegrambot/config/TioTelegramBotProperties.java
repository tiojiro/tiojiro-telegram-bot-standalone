package br.com.zukeran.tiojiro.telegrambot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("br.com.zukeran.tiojiro.telegrambot")
public class TioTelegramBotProperties {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
