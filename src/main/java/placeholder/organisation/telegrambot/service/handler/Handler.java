package placeholder.organisation.telegrambot.service.handler;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public interface Handler<T> {

    void handle(T data, TelegramClient telegramClient) throws TelegramApiException;
}
