package placeholder.organisation.telegrambot.service.handler;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service
public class TextHandler implements Handler<SendMessage> {
    @Override
    public void handle(SendMessage data, TelegramClient telegramClient) throws TelegramApiException {

        telegramClient.execute(data);
    }
}
