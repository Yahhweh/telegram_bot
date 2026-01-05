package placeholder.organisation.telegrambot.service.handler;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service
public class PhotoHandler implements Handler<SendPhoto> {
    @Override
    public void handle(SendPhoto data, TelegramClient telegramClient) throws TelegramApiException {
        telegramClient.execute(data);
    }
}
