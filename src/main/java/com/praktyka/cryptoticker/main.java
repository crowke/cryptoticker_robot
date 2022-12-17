package com.praktyka.cryptoticker;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class main extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                String text = message.getText().toUpperCase();
                SendMessage sm = new SendMessage();
                float price;
                try {
                    price = Float.parseFloat(new JSONObject(IOUtils.toString(
                            new URL("https://api.binance.com/api/v3/ticker/price?symbol=" + text + "USDT"), StandardCharsets.UTF_8))
                            .getString("price"));
                } catch (IOException e) {
                    price = 0;
                }
                sm.setChatId(message.getChatId());
                sm.setText(price == 0 ? "ви ввели неправильний тікер! спробуйте ще раз" : "ціна " + text + ": " + price + "$");
                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Value("${telegram.bot.username}")
    private String username;
    @Value("${telegram.bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
