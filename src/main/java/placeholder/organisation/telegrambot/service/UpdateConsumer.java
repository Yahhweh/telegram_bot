package placeholder.organisation.telegrambot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import placeholder.organisation.telegrambot.service.handler.Handler;
import placeholder.organisation.telegrambot.service.handler.PhotoHandler;
import placeholder.organisation.telegrambot.service.handler.TextHandler;
import placeholder.organisation.telegrambot.service.parser.Parser;
import placeholder.organisation.telegrambot.service.parser.PhotoParser;
import placeholder.organisation.telegrambot.service.parser.TextParser;

@Component
@Slf4j
public class UpdateConsumer implements LongPollingSingleThreadUpdateConsumer, SpringLongPollingBot {

    private final TelegramClient telegramClient;
    private final Parser<SendPhoto> photoParser;
    private final Parser<SendMessage> textParser;
    private final Handler<SendMessage> textHandler;
    private final Handler<SendPhoto> photoHandler;
    private final ActivityService activityService;
    private final String botToken;

    private static final Long GROUP_ID = -5113645153L;

    public UpdateConsumer(PhotoParser photoParser,
                          TextParser textParser,
                          TextHandler textHandler,
                          PhotoHandler photoHandler,
                          ActivityService activityService,
                          @Value("${telegram}") String botToken) {
        this.telegramClient = new OkHttpTelegramClient(botToken);
        this.photoParser = photoParser;
        this.textParser = textParser;
        this.botToken = botToken;
        this.textHandler = textHandler;
        this.photoHandler = photoHandler;
        this.activityService = activityService;
    }

    @Override
    public void consume(Update update) {
        if (!update.hasMessage()) {
            return;
        }

        final Long chatId = update.getMessage().getChatId();
        final User user = update.getMessage().getFrom();

        try {
            if (update.getMessage().hasText()) {
                processTextMessage(update, chatId);
            } else if (update.getMessage().hasPhoto()) {
                processPhotoMessage(update, user);
            }
        } catch (ServiceException e) {
            log.error("Service error processing update: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error: ", e);
        }
    }

    private void processTextMessage(Update update, Long chatId) {
        SendMessage data = textParser.parse(update, chatId);
        try {
            textHandler.handle(data, telegramClient);
        } catch (TelegramApiException e) {
            log.error("Failed to send text message", e);
        }
    }

    private void processPhotoMessage(Update update, User user) {
        SendPhoto data = photoParser.parse(update, GROUP_ID);
        try {
            photoHandler.handle(data, telegramClient);


            activityService.registerActivity(user);

        } catch (TelegramApiException e) {
            log.error("Failed to forward photo", e);
        }
    }


    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }
}